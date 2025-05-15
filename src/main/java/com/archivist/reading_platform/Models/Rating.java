package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating_value;
    @ManyToOne
    @JsonBackReference("book-rating")
    private Book book;
    @ManyToMany
    //@JsonBackReference("author-rating")
    @JsonIgnore
    private List<Author> authors;
    @ManyToOne
    @JsonBackReference("rating-series")
    private Series series;
    @ManyToOne
    @JsonBackReference("rating-reader")
    private Reader reader;


    public Rating() {
        authors=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating_value() {
        return rating_value;
    }

    public void setRating_value(Double rating_value) {
        this.rating_value = rating_value;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
