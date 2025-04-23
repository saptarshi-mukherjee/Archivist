package com.archivist.reading_platform.Services.ReaderService;

import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Strategies.DateNormalisation.BasicStrategy;
import com.archivist.reading_platform.Strategies.DateNormalisation.IndianFormatStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class ReaderServiceImpl implements ReaderService {


    @Autowired
    ReaderRepository reader_repo;


    @Override
    public Reader registerReader(String name, String email, String address, Long phone, String birthday) {
        IndianFormatStrategy strategy=new BasicStrategy();
        LocalDate birth_date=strategy.normaliseDate(birthday);
        Reader reader=new Reader();
        reader.setName(name);
        reader.setEmail(email);
        reader.setAddress(address);
        reader.setPhone(phone);
        reader.setBirthday(birth_date);
        reader=reader_repo.save(reader);
        return reader;
    }
}
