package com.archivist.reading_platform.DTO.ResponseDTO;

import com.archivist.reading_platform.Models.NotificationType;

import java.time.LocalDateTime;

public class SingleNotificationResponseDto {
    private String generation_time;
    private Long review_id, comment_id;
    private NotificationType notification_type;
    private Long publisher_id;


    public String getGeneration_time() {
        return generation_time;
    }

    public void setGeneration_time(String generation_time) {
        this.generation_time = generation_time;
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public NotificationType getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(NotificationType notification_type) {
        this.notification_type = notification_type;
    }

    public Long getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Long publisher_id) {
        this.publisher_id = publisher_id;
    }
}
