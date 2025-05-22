package com.archivist.reading_platform.DTO.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class NotificationResponseDto {
    private String subscriber_name;
    private List<SingleNotificationResponseDto> notifications;

    public NotificationResponseDto() {
        notifications=new ArrayList<>();
    }


    public String getSubscriber_name() {
        return subscriber_name;
    }

    public void setSubscriber_name(String subscriber_name) {
        this.subscriber_name = subscriber_name;
    }

    public List<SingleNotificationResponseDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<SingleNotificationResponseDto> notifications) {
        this.notifications = notifications;
    }
}
