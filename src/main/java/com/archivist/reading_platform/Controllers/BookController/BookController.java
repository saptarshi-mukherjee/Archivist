package com.archivist.reading_platform.Controllers.BookController;


import com.archivist.reading_platform.DTO.RequestDTO.NewBookRequestDto;
import com.archivist.reading_platform.DTO.ResponseDTO.BookResponseDto;
import com.archivist.reading_platform.Models.Author;
import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.Genre;
import com.archivist.reading_platform.Services.BookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    BookService book_service;


    @PostMapping("/add")
    public BookResponseDto addNewBook(@RequestBody NewBookRequestDto request) {
        String book_name=request.getBook_name();
        String series_name= request.getSeries_name();
        String isbn= request.getIsbn();
        String type=request.getType();
        List<String> authors=request.getAuthors();
        List<String> genres=request.getGenres();
        Integer page_count= request.getPage_count();
        Integer book_number= request.getBook_number();
        Book book=book_service.addBook(book_name,authors,isbn,page_count,type,series_name,genres,book_number);
        BookResponseDto response=new BookResponseDto();
        response.setBook_name(book.getBook_name());
        for(Author author : book.getAuthors())
            response.getAuthors().add(author.getAuthor_name());
        response.setSeries_name(book.getSeries().getSeries_name());
        response.setBook_number((book.getSeries().getBooks().indexOf(book))+1);
        for(Genre genre : book.getGenres())
            response.getGenres().add(genre.getGenre_name());
        response.setAvg_rating(book.getAvg_rating());
        response.setRating_count(book.getRatings().size());
        response.setReviews(book.getReviews());
        response.setReview_count(book.getReviews().size());
        return response;
    }
}
