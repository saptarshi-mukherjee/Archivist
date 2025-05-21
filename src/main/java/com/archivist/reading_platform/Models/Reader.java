package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader extends User {
    private LocalDate birthday;
    private Double avg_rating;
    @ManyToMany
    //@JsonManagedReference("reader-tbr")
    @JsonIgnore
    private List<ToRead> tbr;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference("current-reader")
    private List<CurrentlyReading> current_reads;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference("rating-reader")
    private List<Rating> ratings;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference("reader-review")
    private List<Review> reviews;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference("comment-reader")
    private List<Comment> comments;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference("read-reader")
    private List<Read> reads;
    @ManyToMany
    private List<Reader> following;
    @ManyToMany(mappedBy = "following")
    @JsonIgnore
    private List<Reader> followers;
    @OneToMany(mappedBy = "publisher")
    @JsonManagedReference("notification-reader")
    private List<Notification> notifications;


    public Reader() {
        super();
        avg_rating=0.0;
        tbr=new ArrayList<>();
        current_reads=new ArrayList<>();
        ratings=new ArrayList<>();
        reviews=new ArrayList<>();
        comments=new ArrayList<>();
        reads=new ArrayList<>();
        following=new ArrayList<>();
        followers=new ArrayList<>();
        notifications=new ArrayList<>();
    }


    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Reader> getFollowing() {
        return following;
    }

    public void setFollowing(List<Reader> following) {
        this.following = following;
    }

    public List<Reader> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Reader> followers) {
        this.followers = followers;
    }

    public List<Read> getReads() {
        return reads;
    }

    public void setReads(List<Read> reads) {
        this.reads = reads;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public List<ToRead> getTbr() {
        return tbr;
    }

    public void setTbr(List<ToRead> tbr) {
        this.tbr = tbr;
    }

    public List<CurrentlyReading> getCurrent_reads() {
        return current_reads;
    }

    public void setCurrent_reads(List<CurrentlyReading> current_reads) {
        this.current_reads = current_reads;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public boolean equals(Object obj) {
        Reader reader=null;
        if(this==obj)
            return true;
        if(obj instanceof Reader)
            reader=(Reader) obj;
        else
            return false;
        return name.equals(reader.getName());
    }


    @Override
    public int hashCode() {
        if(name!=null)
            return name.hashCode();
        else
            return 0;
    }
}
