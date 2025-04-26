package com.archivist.reading_platform.DTO.ResponseDTO;

public class BookListResponseDto {
    private String book_name;
    private Double book_number;
    //private Integer page_count;


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Double getBook_number() {
        return book_number;
    }

    public void setBook_number(Double book_number) {
        this.book_number = book_number;
    }

//    public Integer getPage_count() {
//        return page_count;
//    }
//
//    public void setPage_count(Integer page_count) {
//        this.page_count = page_count;
//    }
}
