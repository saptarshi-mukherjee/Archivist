package com.archivist.reading_platform.Threads;

import com.archivist.reading_platform.Models.EmailValidator;
import com.archivist.reading_platform.Repositories.EmailValidatorRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OtpDeletionThread implements Runnable {

    private EmailValidatorRepository email_validator_repo;
    Object lock;

    public OtpDeletionThread(EmailValidatorRepository email_validator_repo, Object lock) {
        this.email_validator_repo = email_validator_repo;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            List<EmailValidator> otp_list = email_validator_repo.fetchAllOtps();
            for (EmailValidator otp : otp_list) {
                if (otp.getExpiry_time().isBefore(LocalDateTime.now())) {

                    email_validator_repo.delete(otp);

                }
            }
        }
    }
}
