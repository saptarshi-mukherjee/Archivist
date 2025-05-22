package com.archivist.reading_platform.Strategies.NotificationStrategy;

import com.archivist.reading_platform.Models.*;

import java.util.List;

public class BasicNotificationStrategy implements NewNotificationStrategy {
    @Override
    public Notification postNotification(Reader publisher, Review review, Comment comment, List<Reader> subscribers) {
        Notification notification=new Notification();
        notification.setPublisher(publisher);
        notification.setReview(review);
        notification.setComment(comment);
        notification.setSubscribers(subscribers);
        if(review!=null && comment==null)
            notification.setNotification_type(NotificationType.REVIEW);
        else if(review != null && comment!=null)
            notification.setNotification_type(NotificationType.COMMENT);
        else
            notification.setNotification_type(NotificationType.FOLLOW);
        return notification;
    }
}
