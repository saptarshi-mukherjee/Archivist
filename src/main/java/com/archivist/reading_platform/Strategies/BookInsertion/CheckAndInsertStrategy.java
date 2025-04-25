package com.archivist.reading_platform.Strategies.BookInsertion;

import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Repositories.*;

import java.util.ArrayList;
import java.util.List;

public class CheckAndInsertStrategy implements BookInsertionStrategy {


    BookRepository book_repo;
    AuthorRepository author_repo;
    FormatRepository format_repo;
    GenreRepository genre_repo;
    SeriesRepository series_repo;


    public CheckAndInsertStrategy(BookRepository book_repo, AuthorRepository author_repo, FormatRepository format_repo, GenreRepository genre_repo, SeriesRepository series_repo) {
        this.book_repo = book_repo;
        this.author_repo = author_repo;
        this.format_repo = format_repo;
        this.genre_repo = genre_repo;
        this.series_repo = series_repo;
    }

    @Override
    public Book insert(String book_name, List<String> author_list, String isbn, Integer page_count, String type, String series_name, List<String> genre_list, Integer book_number) {
        Book book=createBook(book_name);
        book=book_repo.save(book);
        Format format=createFormat(isbn,page_count,type);
        Series series=createSeries(series_name);
        List<Author> authors=createAuthors(author_list);
        List<Genre> genres=createGenres(genre_list);
        if(!book.getFormats().contains(format))
            book.getFormats().add(format);
        format.setBook(book);
        format_repo.save(format);
        book=book_repo.save(book);
        book.setSeries(series);
        if(!series.getBooks().contains(book)) {
            if(series.getSeries_name().equals("standalone"))
                series.getBooks().add(book);
            else
                series.getBooks().add(book_number-1,book);
        }
        series_repo.save(series);
        book=book_repo.save(book);
        for(Author author : authors) {
            if(!book.getAuthors().contains(author))
                book.getAuthors().add(author);
            if(!author.getBooks().contains(book))
                author.getBooks().add(book);
        }
        author_repo.saveAll(authors);
        book=book_repo.save(book);
        for(Genre genre : genres) {
            if(!book.getGenres().contains(genre))
                book.getGenres().add(genre);
            if(!genre.getBooks().contains(book))
                genre.getBooks().add(book);
        }
        genre_repo.saveAll(genres);
        book=book_repo.save(book);
        return book;

    }


    private Book createBook(String book_name) {
        Book book=book_repo.fetchByBookName(book_name);
        if(book==null) {
            book=new Book();
            book.setBook_name(book_name);
        }
        return book;
    }


    private Format createFormat(String isbn, Integer page_count, String type) {
        Format format=format_repo.fetchByIsbn(isbn);
        if(format==null) {
            format=new Format();
            format.setIsbn(isbn);
            format.setPage_count(page_count);
            format.setFormat_type(FormatType.valueOf(type.toUpperCase()));
        }
        return format;
    }


    private List<Author> createAuthors(List<String> author_names) {
        List<Author> author_list=new ArrayList<>();
        for(String author_name : author_names) {
            Author author=author_repo.fetchByAuthorName(author_name);
            if(author==null) {
                author=new Author();
                author.setAuthor_name(author_name);
            }
            author_list.add(author);
        }
        return author_list;
    }


    private List<Genre> createGenres(List<String> genre_names) {
        List<Genre> genre_list=new ArrayList<>();
        for(String genre_name : genre_names) {
            Genre genre=genre_repo.fetchByGenreName(genre_name);
            if(genre==null) {
                genre=new Genre();
                genre.setGenre_name(genre_name);
            }
            genre_list.add(genre);
        }
        return genre_list;
    }


    private Series createSeries(String series_name) {
        Series series=this.series_repo.fetchBySeriesName(series_name);
        if(series==null) {
            series=new Series();
            series.setSeries_name(series_name);
        }
        return series;
    }
}
