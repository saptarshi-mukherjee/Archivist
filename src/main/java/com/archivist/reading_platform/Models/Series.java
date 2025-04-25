package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String series_name;
    private Double avg_rating;
    @OneToMany(mappedBy = "series")
    @JsonManagedReference
    private List<Book> books;
    @OneToMany(mappedBy = "series")
    @JsonManagedReference
    private List<Rating> ratings;
    @OneToMany(mappedBy = "series")
    @JsonManagedReference
    private List<BookEntry> book_entries;


    public Series() {
        avg_rating=0.0;
        books=new ArrayList<>();
        ratings=new ArrayList<>();
        book_entries=new ArrayList<>();
    }


    public List<BookEntry> getBook_entries() {
        return book_entries;
    }

    public void setBook_entries(List<BookEntry> book_entries) {
        this.book_entries = book_entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public Double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }


    @Override
    public boolean equals(Object o) {
        Series series=null;
        if(o==null)
            return false;
        if(o instanceof Series)
            series=(Series) o;
        else
            return false;
        return series_name.equals(series.getSeries_name());
    }


    @Override
    public int hashCode() {
        if(series_name!=null)
            return series_name.hashCode();
        else
            return 0;
    }
}
