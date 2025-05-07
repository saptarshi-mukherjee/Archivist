package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.EmailValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailValidatorRepository extends JpaRepository<EmailValidator,Long> {


    @Query(
            value = "select * from email_validator",
            nativeQuery = true
    )
    public List<EmailValidator> fetchAllOtps();


    @Query(
            value = "select * from email_validator where\n" +
                    "email = :email and otp = :otp and expiry_time > now()",
            nativeQuery = true
    )
    public EmailValidator fetchOtpFromEmail(@Param("email") String email, @Param("otp") int otp);
}
