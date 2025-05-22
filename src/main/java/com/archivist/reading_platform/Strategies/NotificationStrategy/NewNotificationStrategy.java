package com.archivist.reading_platform.Strategies.NotificationStrategy;

import com.archivist.reading_platform.Models.Comment;
import com.archivist.reading_platform.Models.Notification;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Models.Review;

import java.util.List;

public interface NewNotificationStrategy {
    public Notification postNotification(Reader publisher, Review review, Comment comment, List<Reader> subscribers);
}
