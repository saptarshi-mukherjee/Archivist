package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.EmailValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailValidatorRepository extends JpaRepository<EmailValidator,Long> {


    @Query(
            value = "select * from email_validator",
            nativeQuery = true
    )
    public List<EmailValidator> fetchAllOtps();
}
