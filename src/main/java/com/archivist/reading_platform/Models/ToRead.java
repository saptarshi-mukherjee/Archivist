package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ToRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JsonManagedReference
    private Book book;
    @ManyToMany(mappedBy = "tbr")
    @JsonBackReference
    private List<Reader> readers;


    public ToRead() {
        readers=new ArrayList<>();
    }


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

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }


    @Override
    public boolean equals(Object obj) {
        ToRead tbr_entry=null;
        if(this==obj)
            return true;
        if(obj instanceof ToRead)
            tbr_entry=(ToRead) obj;
        else
            return false;
        return (book!=null && book.getBook_name()!=null
                && book.getBook_name().equals(tbr_entry.getBook().getBook_name()));
    }


    @Override
    public int hashCode() {
        if(book!=null && book.getBook_name()!=null)
            return book.getBook_name().hashCode();
        else
            return 0;
    }
}
