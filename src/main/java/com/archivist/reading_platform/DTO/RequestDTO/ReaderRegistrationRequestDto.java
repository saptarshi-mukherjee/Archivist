package com.archivist.reading_platform.DTO.RequestDTO;

public class ReaderRegistrationRequestDto {
    private String reader_name, reader_email, reader_address;
    private Long reader_phone;
    private String reader_birthday;

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getReader_email() {
        return reader_email;
    }

    public void setReader_email(String reader_email) {
        this.reader_email = reader_email;
    }

    public String getReader_address() {
        return reader_address;
    }

    public void setReader_address(String reader_address) {
        this.reader_address = reader_address;
    }

    public Long getReader_phone() {
        return reader_phone;
    }

    public void setReader_phone(Long reader_phone) {
        this.reader_phone = reader_phone;
    }

    public String getReader_birthday() {
        return reader_birthday;
    }

    public void setReader_birthday(String reader_birthday) {
        this.reader_birthday = reader_birthday;
    }
}
