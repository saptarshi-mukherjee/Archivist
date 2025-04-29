package com.archivist.reading_platform.DTO.ResponseDTO;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private String commenter_name;
    private LocalDateTime comment_time;
    private int like_count;
    private String comment_text;


    public String getCommenter_name() {
        return commenter_name;
    }

    public void setCommenter_name(String commenter_name) {
        this.commenter_name = commenter_name;
    }

    public LocalDateTime getComment_time() {
        return comment_time;
    }

    public void setComment_time(LocalDateTime comment_time) {
        this.comment_time = comment_time;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }
}
