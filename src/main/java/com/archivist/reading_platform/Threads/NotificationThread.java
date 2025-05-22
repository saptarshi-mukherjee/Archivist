package com.archivist.reading_platform.Threads;

import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Repositories.CommentRepository;
import com.archivist.reading_platform.Repositories.NotificationRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Repositories.ReviewRepository;
import com.archivist.reading_platform.Services.PersistanceService.PersistenceService;
import com.archivist.reading_platform.Strategies.NotificationStrategy.NewNotificationStrategy;

import java.util.List;



public class NotificationThread implements Runnable {

    private NewNotificationStrategy strategy;
    private final Object lock;
    private ReaderRepository reader_repo;
    private ReviewRepository review_repo;
    private CommentRepository comment_repo;
    private Long publisher_id;
    private Long review_id, comment_id;
    private List<Reader> subscribers;
    private NotificationRepository notification_repo;


    public NotificationThread(Object lock, NewNotificationStrategy strategy, ReaderRepository reader_repo,
                              ReviewRepository review_repo, CommentRepository comment_repo, Long publisher_id, Long review_id,
                              Long comment_id, List<Reader> subscribers, NotificationRepository notification_repo) {
        this.lock = lock;
        this.strategy = strategy;
        this.reader_repo = reader_repo;
        this.review_repo = review_repo;
        this.comment_repo = comment_repo;
        this.publisher_id = publisher_id;
        this.review_id = review_id;
        this.comment_id = comment_id;
        this.subscribers = subscribers;
        this.notification_repo=notification_repo;
    }

    @Override
    public void run() {
        System.out.println("THREAD SUBMITTED");
        Notification notification=null;
        Review review=null;
        Comment comment=null;
        Reader publisher=null;
        try {
            synchronized (lock) {
                if(review_id!=null)
                    review=review_repo.fetchByReviewId(review_id);
                if(comment_id!=null)
                    comment=comment_repo.fetchByCommentId(comment_id);
                publisher=reader_repo.fetchByReaderId(publisher_id);
                notification=strategy.postNotification(publisher,review,comment,subscribers);
            }
            synchronized (lock) {
                if (notification != null)
                    notification_repo.save(notification);
                else
                    System.out.println("NOTIFICATION NULL!!!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
