package com.archivist.reading_platform.Helpers;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailOtpHelper {

    public static void sendOtp(JavaMailSender mail_sender, int otp, String to) {
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("sapbum1234@gmail.com");
        msg.setTo(to);
        msg.setSubject("OTP for validation");
        msg.setText("OTP is "+otp+". Valid for 15 minutes");
        mail_sender.send(msg);
    }
}
