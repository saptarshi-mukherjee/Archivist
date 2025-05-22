package com.archivist.reading_platform.Services.NotificationService;

import com.archivist.reading_platform.DTO.ResponseDTO.NotificationResponseDto;
import com.archivist.reading_platform.DTO.ResponseDTO.SingleNotificationResponseDto;
import com.archivist.reading_platform.Models.NotificationType;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Projections.NotificationProjection;
import com.archivist.reading_platform.Repositories.CommentRepository;
import com.archivist.reading_platform.Repositories.NotificationRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Repositories.ReviewRepository;
import com.archivist.reading_platform.Strategies.NotificationStrategy.BasicNotificationStrategy;
import com.archivist.reading_platform.Strategies.NotificationStrategy.NewNotificationStrategy;
import com.archivist.reading_platform.Threads.NotificationThread;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private ReaderRepository reader_repo;
    private ReviewRepository review_repo;
    private CommentRepository comment_repo;
    private static final Object lock=new Object();
    ExecutorService exs;
    private NotificationRepository notification_repo;
    private RedisTemplate<String,Object> redis_template;

    public NotificationServiceImpl(ReaderRepository reader_repo, ReviewRepository review_repo, CommentRepository comment_repo,
                                   NotificationRepository notification_repo, RedisTemplate<String,Object> redis_template) {
        this.reader_repo = reader_repo;
        this.review_repo = review_repo;
        this.comment_repo = comment_repo;
        exs=Executors.newFixedThreadPool(20);
        this.notification_repo=notification_repo;
        this.redis_template=redis_template;
    }

    @Override
    public void postNotification(Long publisher_id, Long review_id, Long comment_id, List<Reader> subscribers) {
        NewNotificationStrategy strategy= new BasicNotificationStrategy();
        NotificationThread thread=new NotificationThread(lock,strategy,reader_repo,review_repo,comment_repo,publisher_id,review_id,comment_id,subscribers,notification_repo);
        exs.submit(thread);
    }

    @Override
    public NotificationResponseDto getNotifications(String sub_name) {
        NotificationResponseDto cached_response=(NotificationResponseDto) redis_template.opsForValue().get("NOTIFICATION::"+sub_name);
        if(cached_response!=null)
            return cached_response;
        long subscriber_id=reader_repo.fetchByReaderName(sub_name).getId();
        List<NotificationProjection> notification_proj=notification_repo.fetchNotificationBySubscriberId(subscriber_id);
        List<SingleNotificationResponseDto> single_notification_list=new ArrayList<>();
        for(NotificationProjection np : notification_proj) {
            SingleNotificationResponseDto single_notification=new SingleNotificationResponseDto();
            single_notification.setGeneration_time(np.getGeneration_time().toString());
            single_notification.setPublisher_id(np.getPublisher_id());
            single_notification.setReview_id(np.getReview_id());
            single_notification.setComment_id(np.getComment_id());
            single_notification.setNotification_type(NotificationType.values()[np.getNotification_type()]);
            single_notification_list.add(single_notification);
        }
        NotificationResponseDto response=new NotificationResponseDto();
        response.setSubscriber_name(sub_name);
        response.setNotifications(single_notification_list);
        redis_template.opsForValue().set("NOTIFICATION::"+sub_name, response, Duration.ofMinutes(15));
        return response;
    }


    @PreDestroy
    public void shutdownThreadPool() {
        exs.shutdown();
    }
}
