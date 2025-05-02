package com.archivist.reading_platform.Models;


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
    @JsonManagedReference
    private List<ToRead> tbr;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference
    private List<CurrentlyReading> current_reads;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference
    private List<Rating> ratings;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference
    private List<Review> reviews;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference
    private List<Comment> comments;
    @OneToMany(mappedBy = "reader")
    @JsonManagedReference
    private List<Read> reads;


    public Reader() {
        avg_rating=0.0;
        tbr=new ArrayList<>();
        current_reads=new ArrayList<>();
        ratings=new ArrayList<>();
        reviews=new ArrayList<>();
        comments=new ArrayList<>();
        reads=new ArrayList<>();
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
