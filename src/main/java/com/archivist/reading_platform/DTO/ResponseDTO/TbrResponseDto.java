package com.archivist.reading_platform.DTO.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class TbrResponseDto {
    private String book_name, series_name;
    private List<String> author_names;


    public TbrResponseDto() {
        author_names=new ArrayList<>();
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public List<String> getAuthor_names() {
        return author_names;
    }

    public void setAuthor_names(List<String> author_names) {
        this.author_names = author_names;
    }
}
