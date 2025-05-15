package com.archivist.reading_platform.DTO.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class SmallBookResponseDto {
    private String book_name;
    private List<String> authors;
    private String series_name;


    public SmallBookResponseDto() {
        authors=new ArrayList<>();
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }
}
