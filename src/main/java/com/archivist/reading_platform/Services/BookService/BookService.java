package com.archivist.reading_platform.Services.BookService;

import com.archivist.reading_platform.Models.Book;

import java.util.List;

public interface BookService {
    public Book addBook(String book_name, List<String> author_list, String isbn, Integer page_count, String type, String series_name, List<String> genre_list,
                        Integer book_number);
}
