package com.archivist.reading_platform.Services.EmailValidationService;


import com.archivist.reading_platform.Repositories.EmailValidatorRepository;
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
    private ExecutorService exs1;
    private ExecutorService exs2;
    private static final Object lock1 =new Object();
    private static final Object lock2=new Object();

    public EmailValidationServiceImpl(EmailValidatorRepository email_validator_repo) {
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


    @PreDestroy
    public void threadShutDown() {
        exs1.shutdown();
        exs2.shutdown();
    }
}
