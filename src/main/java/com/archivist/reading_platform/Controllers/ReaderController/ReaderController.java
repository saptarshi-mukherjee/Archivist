package com.archivist.reading_platform.Controllers.ReaderController;


import com.archivist.reading_platform.DTO.RequestDTO.ReaderRegistrationRequestDto;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Services.ReaderService.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService reader_service;


    @PostMapping("/register")
    public String registerReader(@RequestBody ReaderRegistrationRequestDto request) {
        Reader reader=reader_service.registerReader(request.getReader_name(), request.getReader_email(),
                request.getReader_address(),request.getReader_phone(),request.getReader_birthday());
        if(reader.getId()==null)
            return "registration failed";
        else
            return "new reader registered";
    }
}
