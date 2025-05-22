package com.archivist.reading_platform.Projections;

import com.archivist.reading_platform.Models.NotificationType;

import java.time.LocalDateTime;

public interface NotificationProjection {
    public LocalDateTime getGeneration_time();
    public Long getComment_id();
    public Long getPublisher_id();
    public Long getReview_id();
    public Integer getNotification_type();
}
