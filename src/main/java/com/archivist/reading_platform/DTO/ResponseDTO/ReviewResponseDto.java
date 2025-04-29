package com.archivist.reading_platform.DTO.ResponseDTO;

import java.time.LocalDateTime;

public class ReviewResponseDto {
    private String reader_name;
    private LocalDateTime review_time;
    private String review_text;
    private Integer like_count;


    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public LocalDateTime getReview_time() {
        return review_time;
    }

    public void setReview_time(LocalDateTime review_time) {
        this.review_time = review_time;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }
}
