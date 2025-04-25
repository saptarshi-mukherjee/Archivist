package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class BookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JsonBackReference
    private Book book;
    private Double book_number;
    @ManyToOne
    @JsonBackReference
    private Series series;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Double getBook_number() {
        return book_number;
    }

    public void setBook_number(Double book_number) {
        this.book_number = book_number;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }


    @Override
    public boolean equals(Object o) {
        BookEntry book_entry;
        if(o==null)
            return false;
        if(o instanceof BookEntry)
            book_entry=(BookEntry) o;
        else
            return false;
        return book.getBook_name().equals(book_entry.getBook().getBook_name());
    }


    @Override
    public int hashCode() {
        if(book!=null && book.getBook_name()!=null)
            return book.getBook_name().hashCode();
        else
            return 0;
    }
}
