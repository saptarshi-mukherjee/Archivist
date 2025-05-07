package com.archivist.reading_platform.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class EmailValidator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Integer otp;
    private LocalDateTime generation_time, expiry_time;


    public EmailValidator() {
        this.generation_time=LocalDateTime.now();
        this.expiry_time=this.generation_time.plusMinutes(15);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public LocalDateTime getGeneration_time() {
        return generation_time;
    }

    public void setGeneration_time(LocalDateTime generation_time) {
        this.generation_time = generation_time;
    }

    public LocalDateTime getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(LocalDateTime expiry_time) {
        this.expiry_time = expiry_time;
    }
}
