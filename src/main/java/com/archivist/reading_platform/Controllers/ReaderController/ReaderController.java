package com.archivist.reading_platform.Controllers.ReaderController;


import com.archivist.reading_platform.DTO.RequestDTO.RateBookRequestDto;
import com.archivist.reading_platform.DTO.RequestDTO.ReaderRegistrationRequestDto;
import com.archivist.reading_platform.DTO.ResponseDTO.BookResponseDto;
import com.archivist.reading_platform.Models.Author;
import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.Genre;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Services.ReaderService.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService reader_service;


    @PostMapping("/register")
    public String registerReader(@RequestBody ReaderRegistrationRequestDto request) {
        Reader reader=reader_service.registerReader(request.getReader_name(), request.getReader_email(),
                request.getReader_address(),request.getReader_phone(),request.getReader_birthday());
        if(reader.getId()==null)
            return "registration failed";
        else
            return "new reader registered";
    }



    @PostMapping("/rate")
    public BookResponseDto rateBook(@RequestBody RateBookRequestDto request) throws Exception {
        if(request.getRating_value()>10)
            throw new Exception("rating cannot be greater than 10");
        Book book=reader_service.rateBook(request.getReader_name(), request.getBook_name(),request.getRating_value());
        BookResponseDto response=new BookResponseDto();
        response.setBook_name(book.getBook_name());
        for(Author author : book.getAuthors())
            response.getAuthors().add(author.getAuthor_name());
        response.setSeries_name(book.getSeries().getSeries_name());
        response.setBook_number(book.getBook_entry().getBook_number());
        for(Genre genre : book.getGenres())
            response.getGenres().add(genre.getGenre_name());
        response.setAvg_rating(book.getAvg_rating());
        response.setRating_count(book.getRatings().size());
        response.setReviews(book.getReviews());
        response.setReview_count(book.getReviews().size());
        return response;
    }
}
