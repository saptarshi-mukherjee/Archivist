package com.archivist.reading_platform.Services.NotificationService;

import com.archivist.reading_platform.DTO.ResponseDTO.NotificationResponseDto;
import com.archivist.reading_platform.Models.Reader;

import java.util.List;

public interface NotificationService {
    public void postNotification(Long publisher_id, Long review_id, Long comment_id, List<Reader> subscribers);
    public NotificationResponseDto getNotifications(String sub_name);
}
