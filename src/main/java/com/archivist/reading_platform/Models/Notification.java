package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference("notification-reader")
    private Reader publisher;
    @ManyToOne
    @JsonBackReference("notification-review")
    private Review review;
    @ManyToOne
    @JsonBackReference("notification-comment")
    private Comment comment;
    @ManyToMany
    private List<Reader> subscribers;
    private LocalDateTime generation_time;
    private NotificationType notification_type;



    public Notification() {
        subscribers=new ArrayList<>();
        this.setGeneration_time(LocalDateTime.now());
    }


    public NotificationType getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(NotificationType notification_type) {
        this.notification_type = notification_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getPublisher() {
        return publisher;
    }

    public void setPublisher(Reader publisher) {
        this.publisher = publisher;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Reader> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Reader> subscribers) {
        this.subscribers = subscribers;
    }

    public LocalDateTime getGeneration_time() {
        return generation_time;
    }

    public void setGeneration_time(LocalDateTime generation_time) {
        this.generation_time = generation_time;
    }
}
