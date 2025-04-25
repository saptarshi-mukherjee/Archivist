package com.archivist.reading_platform.Services.BookService;


import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Repositories.*;
import com.archivist.reading_platform.Strategies.BookInsertion.BookInsertionStrategy;
import com.archivist.reading_platform.Strategies.BookInsertion.CheckAndInsertStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository book_repo;
    @Autowired
    AuthorRepository author_repo;
    @Autowired
    FormatRepository format_repo;
    @Autowired
    GenreRepository genre_repo;
    @Autowired
    SeriesRepository series_repo;
    @Autowired
    BookEntryRepository entry_repo;


    @Override
    public Book addBook(String book_name, List<String> author_list, String isbn, Integer page_count, String type, String series_name, List<String> genre_list, Double book_number) {
        BookInsertionStrategy strategy=new CheckAndInsertStrategy(book_repo,author_repo,format_repo,genre_repo,series_repo,entry_repo);
        Book book=strategy.insert(book_name,author_list,isbn,page_count,type,series_name,genre_list,book_number);
        return book;
    }
}
