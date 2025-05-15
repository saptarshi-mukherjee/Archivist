package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author_name;
    private Double avg_rating;
    @ManyToMany(mappedBy = "authors")
    //@JsonBackReference("book-author")
    @JsonIgnore
    private List<Book> books;
    @ManyToMany(mappedBy = "authors")
    //@JsonManagedReference("author-rating")
    private List<Rating> ratings;


    public Author() {
        avg_rating=0.0;
        books=new ArrayList<>();
        ratings=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
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
        Author author=null;
        if(o==null)
            return false;
        if(o instanceof Author)
            author=(Author) o;
        else
            return false;
        return author_name.equals(author.getAuthor_name());
    }


    @Override
    public int hashCode() {
        if(author_name!=null)
            return author_name.hashCode();
        else
            return 0;
    }
}
