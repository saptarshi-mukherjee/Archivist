package com.archivist.reading_platform.Threads;

import com.archivist.reading_platform.Helpers.EmailOtpHelper;
import com.archivist.reading_platform.Strategies.OtpGeneration.OtpGenerationStrategy;
import org.springframework.mail.javamail.JavaMailSender;

public class OtpGenerationThread implements Runnable {

    private JavaMailSender mail_sender;
    private OtpGenerationStrategy strategy;
    String to;
    Object lock;

    public OtpGenerationThread(JavaMailSender mail_sender, OtpGenerationStrategy strategy, String to, Object lock) {
        this.mail_sender = mail_sender;
        this.strategy = strategy;
        this.to = to;
        this.lock=lock;
    }

    @Override
    public void run() {

        int otp=-1;

        synchronized (lock) {
            otp= strategy.generateOtp(to);
        }

        synchronized (lock) {
            EmailOtpHelper.sendOtp(mail_sender,otp,to);
        }

    }
}
