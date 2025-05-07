package com.archivist.reading_platform.Threads;

import com.archivist.reading_platform.Strategies.OtpGeneration.OtpGenerationStrategy;

import java.util.concurrent.Callable;

public class OtpGenerationThread implements Callable<Integer> {

    private String email;
    private OtpGenerationStrategy strategy;


    public OtpGenerationThread(String email, OtpGenerationStrategy strategy) {
        this.email = email;
        this.strategy=strategy;
    }

    @Override
    public Integer call() throws Exception {
        int otp= strategy.generateOtp(email);
        return otp;
    }
}
