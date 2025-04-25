package com.archivist.reading_platform.Strategies.BookInsertion;

import com.archivist.reading_platform.Models.Book;

import java.util.List;

public interface BookInsertionStrategy {
    public Book insert(String book_name, List<String> author_list, String isbn, Integer page_count, String type,
                       String series_name, List<String> genre_list, Integer book_number);
}
