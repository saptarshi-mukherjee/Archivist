package com.archivist.reading_platform.Services.EmailValidationService;


import com.archivist.reading_platform.Models.EmailStatus;
import com.archivist.reading_platform.Models.EmailValidator;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Repositories.EmailValidatorRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Strategies.OtpGeneration.OtpGenerationStrategy;
import com.archivist.reading_platform.Strategies.OtpGeneration.SecureRandomStrategy;
import com.archivist.reading_platform.Threads.OtpDeletionThread;
import com.archivist.reading_platform.Threads.OtpGenerationThread;
import jakarta.annotation.PreDestroy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {


    private EmailValidatorRepository email_validator_repo;
    private ReaderRepository reader_repo;
    private ExecutorService exs1;
    private ExecutorService exs2;
    private static final Object lock1 =new Object();
    private static final Object lock2=new Object();
    private JavaMailSender mail_sender;

    public EmailValidationServiceImpl(EmailValidatorRepository email_validator_repo, ReaderRepository reader_repo,
                                      JavaMailSender mail_sender) {
        this.reader_repo=reader_repo;
        this.email_validator_repo = email_validator_repo;
        this.mail_sender=mail_sender;
        exs1 = Executors.newFixedThreadPool(20);
        exs2=Executors.newFixedThreadPool(5);
    }

    @Override
    public void generateOtp(String email) throws Exception {
        OtpGenerationStrategy strategy=new SecureRandomStrategy(email_validator_repo);
        OtpGenerationThread thread=new OtpGenerationThread(mail_sender,strategy,email,lock1);
        exs1.submit(thread);
    }

    @Override
    @Scheduled(cron = "0 */10 * * * *")
    public void deleteOtp() {
        OtpDeletionThread otp_thread=new OtpDeletionThread(email_validator_repo,lock2);
        exs2.submit(otp_thread);
    }

    @Override
    public boolean validateOtp(String email, int otp) {
        EmailValidator email_validator=email_validator_repo.fetchOtpFromEmail(email,otp);
        if(email_validator==null)
            return false;
        String reader_email=email_validator.getEmail();
        Reader reader=reader_repo.fetchByReaderEmail(reader_email);
        if(reader==null)
            return false;
        reader.setEmail_status(EmailStatus.VERIFIED);
        reader_repo.save(reader);
        return true;
    }

    @Override
    public void testMail(String to, String subject, String body) {
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("sapbum1234@gmail.com");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mail_sender.send(msg);
    }


    @PreDestroy
    public void threadShutDown() {
        exs1.shutdown();
        exs2.shutdown();
    }
}
