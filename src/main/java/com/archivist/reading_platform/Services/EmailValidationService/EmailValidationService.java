package com.archivist.reading_platform.Services.EmailValidationService;

public interface EmailValidationService {
    public void generateOtp(String email) throws Exception;
    public void deleteOtp();
    public boolean validateOtp(String email, int otp);
    public void testMail(String to, String subject, String body);
}
