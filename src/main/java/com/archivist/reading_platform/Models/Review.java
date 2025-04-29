package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String review_text;
    private Integer like_count;
    @ManyToOne
    @JsonBackReference
    private Reader reader;
    @ManyToOne
    @JsonBackReference
    private Book book;
    @OneToMany(mappedBy = "review")
    @JsonManagedReference
    private List<Comment> comments;
    private LocalDateTime review_time;


    public Review() {
        like_count=0;
        comments=new ArrayList<>();
        this.setReview_time(LocalDateTime.now());
    }


    public LocalDateTime getReview_time() {
        return review_time;
    }

    public void setReview_time(LocalDateTime review_time) {
        this.review_time = review_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
