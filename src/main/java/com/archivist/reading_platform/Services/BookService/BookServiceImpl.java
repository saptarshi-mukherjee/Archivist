package com.archivist.reading_platform.Services.BookService;


import com.archivist.reading_platform.DTO.ResponseDTO.SmallBookResponseDto;
import com.archivist.reading_platform.Models.Author;
import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.BookEntry;
import com.archivist.reading_platform.Models.Series;
import com.archivist.reading_platform.Repositories.*;
import com.archivist.reading_platform.Strategies.BookInsertion.BookInsertionStrategy;
import com.archivist.reading_platform.Strategies.BookInsertion.CheckAndInsertStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @Autowired
    CacheManager cache_manager;


    @Override
    public Book addBook(String book_name, List<String> author_list, String isbn, Integer page_count, String type, String series_name, List<String> genre_list, Double book_number) {
        BookInsertionStrategy strategy=new CheckAndInsertStrategy(book_repo,author_repo,format_repo,genre_repo,series_repo,entry_repo);
        Book book=strategy.insert(book_name,author_list,isbn,page_count,type,series_name,genre_list,book_number);
        return book;
    }

    @Override
    public List<Book> generalSearch(String prefix) {
        List<Book> results=new ArrayList<>();
        List<Book> book_search=book_repo.fetchBookByPrefix(prefix);
        results.addAll(book_search);
        List<Author> authors=author_repo.fetchAuthorByPrefix(prefix);
        for(Author author : authors) {
            results.addAll(author.getBooks());
        }
        List<Series> series_list=series_repo.fetchSeriesByPrefix(prefix);
        for(Series series : series_list) {
            results.addAll(series.getBooks());
        }
        return results;
    }

    @Override
    public List<BookEntry> searchBooksInSeries(String series_name) {
        Series series=series_repo.fetchBySeriesName(series_name);
        List<BookEntry> book_entries=series.getBook_entries();
        Comparator<BookEntry> comp=new Comparator<BookEntry>() {
            @Override
            public int compare(BookEntry o1, BookEntry o2) {
                if(o2.getBook_number()< o1.getBook_number())
                    return 1;
                else if(o2.getBook_number()>o1.getBook_number())
                    return -1;
                else
                    return 0;
            }
        };
        Collections.sort(book_entries,comp);
        return book_entries;
    }

    @Override
    public SmallBookResponseDto getBookByName(String book_name) {
        Cache cache= cache_manager.getCache("BOOKS_CACHE");
        if(cache!=null) {
            SmallBookResponseDto cached_response=cache.get(book_name, SmallBookResponseDto.class);
            if(cached_response!=null) {
                System.out.println("FROM CACHE!!!");
                return cached_response;
            }
        }
        Book book=book_repo.fetchByBookName(book_name);
        SmallBookResponseDto response=new SmallBookResponseDto();
        response.setBook_name(book.getBook_name());
        for(Author author : book.getAuthors())
            response.getAuthors().add(author.getAuthor_name());
        response.setSeries_name(book.getSeries().getSeries_name());
        if(cache!=null)
            cache.put(book_name,response);
        return response;
    }
}
