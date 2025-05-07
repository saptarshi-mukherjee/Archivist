package com.archivist.reading_platform.Strategies.OtpGeneration;

import com.archivist.reading_platform.Models.EmailValidator;
import com.archivist.reading_platform.Repositories.EmailValidatorRepository;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;

public class SecureRandomStrategy implements OtpGenerationStrategy {


    private EmailValidatorRepository email_validator_repo;
    Object lock;


    public SecureRandomStrategy(EmailValidatorRepository email_validator_repo, Object lock) {
        this.email_validator_repo = email_validator_repo;
        this.lock=lock;
    }

    @Override
    public int generateOtp(String email) {
        SecureRandom random=new SecureRandom();
        EmailValidator email_validator=null;
        synchronized (lock) {
            List<EmailValidator> otp_list = email_validator_repo.fetchAllOtps();
            HashSet<Integer> set = new HashSet<>();
            for (EmailValidator otp : otp_list) {
                set.add(otp.getOtp());
            }
            while (true) {
                int num = 100000 + random.nextInt(900000);
                if (!set.contains(num)) {
                    email_validator = new EmailValidator();
                    email_validator.setEmail(email);
                    email_validator.setOtp(num);
                    email_validator = email_validator_repo.save(email_validator);

                    break;
                }
            }
        }
        return email_validator.getOtp();
    }
}
