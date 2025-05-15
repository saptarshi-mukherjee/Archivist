package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String book_name;
    private Double avg_rating;
    @ManyToMany
   // @JsonManagedReference("book-author")
    private List<Author> authors;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-format")
    private List<Format> formats;
    @ManyToOne
    @JsonBackReference("book-series")
    private Series series;
    @OneToOne(mappedBy = "book")
    @JsonBackReference("book-tbr")
    private ToRead tbr_entry;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-read")
    private List<Read> reads;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-current")
    private List<CurrentlyReading> current_reads;
    @ManyToMany
    //@JsonManagedReference("book-genre")
    private List<Genre> genres;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-rating")
    private List<Rating> ratings;
    @OneToMany(mappedBy = "book")
    @JsonManagedReference("book-review")
    private List<Review> reviews;
    @OneToOne(mappedBy = "book")
    @JsonManagedReference("book-entry")
    private BookEntry book_entry;


    public Book() {
        avg_rating=0.0;
        authors=new ArrayList<>();
        formats=new ArrayList<>();
        reads=new ArrayList<>();
        current_reads=new ArrayList<>();
        genres=new ArrayList<>();
        ratings=new ArrayList<>();
        reviews=new ArrayList<>();
    }


    public BookEntry getBook_entry() {
        return book_entry;
    }

    public void setBook_entry(BookEntry book_entry) {
        this.book_entry = book_entry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public ToRead getTbr_entry() {
        return tbr_entry;
    }

    public void setTbr_entry(ToRead tbr_entry) {
        this.tbr_entry = tbr_entry;
    }

    public List<Read> getReads() {
        return reads;
    }

    public void setReads(List<Read> reads) {
        this.reads = reads;
    }

    public List<CurrentlyReading> getCurrent_reads() {
        return current_reads;
    }

    public void setCurrent_reads(List<CurrentlyReading> current_reads) {
        this.current_reads = current_reads;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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

    @Override
    public boolean equals(Object o) {
        Book book=null;
        if(o==null)
            return false;
        if(o instanceof Book)
            book=(Book) o;
        else
            return false;
        return book_name.equals(book.getBook_name());
    }

    @Override
    public int hashCode() {
        if(book_name != null)
            return book_name.hashCode();
        else
            return 0;
    }
}
