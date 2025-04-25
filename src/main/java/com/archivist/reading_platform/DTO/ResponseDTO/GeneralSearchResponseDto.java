package com.archivist.reading_platform.DTO.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearchResponseDto {
    private String book_name;
    private List<String> author_name;
    private String series_name;


    public GeneralSearchResponseDto() {
        author_name=new ArrayList<>();
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }
}
