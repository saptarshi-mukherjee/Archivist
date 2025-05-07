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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {


    private EmailValidatorRepository email_validator_repo;
    private ReaderRepository reader_repo;
    private ExecutorService exs1;
    private ExecutorService exs2;
    private static final Object lock1 =new Object();
    private static final Object lock2=new Object();

    public EmailValidationServiceImpl(EmailValidatorRepository email_validator_repo, ReaderRepository reader_repo) {
        this.reader_repo=reader_repo;
        this.email_validator_repo = email_validator_repo;
        exs1 = Executors.newFixedThreadPool(20);
        exs2=Executors.newFixedThreadPool(5);
    }

    @Override
    public int generateOtp(String email) throws Exception {
        OtpGenerationStrategy strategy=new SecureRandomStrategy(email_validator_repo, lock1);
        OtpGenerationThread otp_thread=new OtpGenerationThread(email,strategy);
        Future<Integer> otp_future= exs1.submit(otp_thread);
        int otp=otp_future.get();
        return otp;
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


    @PreDestroy
    public void threadShutDown() {
        exs1.shutdown();
        exs2.shutdown();
    }
}
