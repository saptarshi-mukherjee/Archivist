package com.archivist.reading_platform.DTO.RequestDTO;

public class RateBookRequestDto {
    private String reader_name, book_name;
    private double rating_value;


    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public double getRating_value() {
        return rating_value;
    }

    public void setRating_value(double rating_value) {
        this.rating_value = rating_value;
    }
}
