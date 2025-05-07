package com.archivist.reading_platform.Services.EmailValidationService;

public interface EmailValidationService {
    public int generateOtp(String email) throws Exception;
    public void deleteOtp();
}
