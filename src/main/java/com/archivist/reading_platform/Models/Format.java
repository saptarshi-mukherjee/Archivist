package com.archivist.reading_platform.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Format {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    @ManyToOne
    @JsonBackReference
    private Book book;
    private Integer page_count;
    private FormatType format_type;
    @OneToMany(mappedBy = "format")
    @JsonManagedReference
    private List<CurrentlyReading> current_reads;


    public Format() {
        current_reads=new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public FormatType getFormat_type() {
        return format_type;
    }

    public void setFormat_type(FormatType format_type) {
        this.format_type = format_type;
    }

    public List<CurrentlyReading> getCurrent_reads() {
        return current_reads;
    }

    public void setCurrent_reads(List<CurrentlyReading> current_reads) {
        this.current_reads = current_reads;
    }


    @Override
    public boolean equals(Object o) {
        Format format=null;
        if(o==null)
            return false;
        if(o instanceof Format)
            format=(Format) o;
        else
            return false;
        return isbn.equals(format.getIsbn());
    }


    @Override
    public int hashCode() {
        if(isbn!=null)
            return isbn.hashCode();
        else
            return 0;
    }
}
