package com.archivist.reading_platform.DTO.ResponseDTO;

import java.util.List;

public class AllBookReviewResponseDto {
    private String book_name;
    private List<ReviewResponseDto> reviews;


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public List<ReviewResponseDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponseDto> reviews) {
        this.reviews = reviews;
    }
}
