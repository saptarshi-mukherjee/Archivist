package com.archivist.reading_platform.Services.ReaderService;

import com.archivist.reading_platform.Models.Reader;

public interface ReaderService {
    public Reader registerReader(String name, String email, String address, Long phone, String birthday);
}
