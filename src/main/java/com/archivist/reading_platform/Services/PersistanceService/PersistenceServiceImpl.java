package com.archivist.reading_platform.Services.PersistanceService;

import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Repositories.CommentRepository;
import com.archivist.reading_platform.Repositories.NotificationRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PersistenceServiceImpl implements PersistenceService {

    @Autowired
    private NotificationRepository notification_repo;
    @Autowired
    private ReaderRepository reader_repo;
    @Autowired
    private ReviewRepository review_repo;
    @Autowired
    private CommentRepository comment_repo;

    @Override
    public void saveNotification(Notification notification, List<Reader> subscribers) {
        Reader publisher=null;
        Review review=null;
        Comment comment=null;
        NotificationType type=null;
        List<Reader> sub_list=new ArrayList<>();
        publisher=reader_repo.fetchByReaderId(notification.getPublisher().getId());
        if(notification.getReview()!=null)
            review=review_repo.fetchByReviewId(notification.getReview().getId());
        if(notification.getComment()!=null)
            comment=comment_repo.fetchByCommentId(notification.getComment().getId());
        type=notification.getNotification_type();
        notification.setReview(review);
        notification.setComment(comment);
        for(Reader sub : subscribers) {
            Reader subscriber=reader_repo.fetchByReaderId(sub.getId());
            sub_list.add(subscriber);
        }
        notification.setSubscribers(sub_list);
        notification.setNotification_type(type);
        notification.setPublisher(publisher);
        notification_repo.save(notification);
        System.out.println("NOTIFICATION SAVED!!!");
    }
}
