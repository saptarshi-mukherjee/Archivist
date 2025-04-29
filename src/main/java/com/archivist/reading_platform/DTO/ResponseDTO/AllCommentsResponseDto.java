package com.archivist.reading_platform.DTO.ResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AllCommentsResponseDto {
    private String reviewer_name;
    private LocalDateTime review_time;
    private String review_text;
    private int like_count;
    private List<CommentResponseDto> comments_list;


    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public List<CommentResponseDto> getComments_list() {
        return comments_list;
    }

    public void setComments_list(List<CommentResponseDto> comments_list) {
        this.comments_list = comments_list;
    }
}
