package com.archivist.reading_platform.DTO.ResponseDTO;

public class CurrentlyReadingResponseDto {
    private String book_name;
    private Double progress;


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}
