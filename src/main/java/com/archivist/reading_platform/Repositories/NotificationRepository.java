package com.archivist.reading_platform.Repositories;

import com.archivist.reading_platform.Models.Notification;
import com.archivist.reading_platform.Projections.NotificationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {



    @Query(
            value = "select \n" +
                    "\t n.generation_time as generation_time,\n" +
                    "    n.comment_id as comment_id,\n" +
                    "    n.publisher_id as publisher_id,\n" +
                    "    n.review_id as review_id,\n" +
                    "    n.notification_type as notification_type\n" +
                    "from notification as n\n" +
                    "join notification_subscribers as ns\n" +
                    "on n.id=ns.notification_id and ns.subscribers_id= :subscriber_id\n" +
                    "order by n.generation_time desc",
            nativeQuery = true
    )
    public List<NotificationProjection> fetchNotificationBySubscriberId(@Param("subscriber_id") long subscriber_id);
}
