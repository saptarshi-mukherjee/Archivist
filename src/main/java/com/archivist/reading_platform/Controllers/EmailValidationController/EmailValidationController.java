package com.archivist.reading_platform.Controllers.EmailValidationController;


import com.archivist.reading_platform.Services.EmailValidationService.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class EmailValidationController {


    @Autowired
    private EmailValidationService email_validation_service;

    @PostMapping("/generate/{email}")
    public Integer generateOtp(@PathVariable("email") String email) throws Exception {
        int otp= email_validation_service.generateOtp(email);
        return otp;
    }
}
