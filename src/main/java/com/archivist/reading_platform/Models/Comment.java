package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String comment_text;
    private Integer like_count;
    @ManyToOne
    @JsonBackReference("comment-review")
    private Review review;
    @ManyToOne
    @JsonBackReference("comment-reader")
    private Reader reader;
    private LocalDateTime comment_time;
    @OneToMany(mappedBy = "comment")
    @JsonManagedReference("notification-comment")
    private List<Notification> notifications;


    public Comment() {
        like_count=0;
        this.setComment_time(LocalDateTime.now());
        notifications=new ArrayList<>();
    }


    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public LocalDateTime getComment_time() {
        return comment_time;
    }

    public void setComment_time(LocalDateTime comment_time) {
        this.comment_time = comment_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
