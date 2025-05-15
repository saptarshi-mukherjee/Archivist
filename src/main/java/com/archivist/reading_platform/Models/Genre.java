package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre_name;
    @ManyToMany(mappedBy = "genres")
    //@JsonBackReference("book-genre")
    @JsonIgnore
    private List<Book> books;


    public Genre() {
        books=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    @Override
    public boolean equals(Object o) {
        Genre genre=null;
        if(o==null)
            return false;
        if(o instanceof Genre)
            genre=(Genre) o;
        else
            return false;
        return genre_name.equals(genre.getGenre_name());
    }


    @Override
    public int hashCode() {
        if (genre_name!=null)
            return genre_name.hashCode();
        else
            return 0;
    }
}
