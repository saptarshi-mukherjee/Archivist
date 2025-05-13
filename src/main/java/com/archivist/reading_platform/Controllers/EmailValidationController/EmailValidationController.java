package com.archivist.reading_platform.Controllers.EmailValidationController;


import com.archivist.reading_platform.DTO.RequestDTO.TestMailRequestDto;
import com.archivist.reading_platform.Services.EmailValidationService.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/validate")
    public String validateOtp(@RequestParam("email") String email, @RequestParam("otp") int otp) throws Exception {
        if(email_validation_service.validateOtp(email, otp))
            return "validation successful";
        else
            throw new Exception("Email validation has failed");
    }


    @PostMapping("/test/mail")
    public void testMail(@RequestBody TestMailRequestDto request) {
        email_validation_service.testMail(request.getTo(), request.getSubject(), request.getBody());
    }
}
