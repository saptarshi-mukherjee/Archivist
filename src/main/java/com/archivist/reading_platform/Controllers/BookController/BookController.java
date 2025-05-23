package com.archivist.reading_platform.Controllers.BookController;


import com.archivist.reading_platform.DTO.RequestDTO.NewBookRequestDto;
import com.archivist.reading_platform.DTO.ResponseDTO.BookListResponseDto;
import com.archivist.reading_platform.DTO.ResponseDTO.BookResponseDto;
import com.archivist.reading_platform.DTO.ResponseDTO.GeneralSearchResponseDto;
import com.archivist.reading_platform.DTO.ResponseDTO.SmallBookResponseDto;
import com.archivist.reading_platform.Models.Author;
import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.BookEntry;
import com.archivist.reading_platform.Models.Genre;
import com.archivist.reading_platform.Services.BookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    BookService book_service;
    @Autowired
    private RedisTemplate<String,Object> redis_template;


    @PostMapping("/add")
    public BookResponseDto addNewBook(@RequestBody NewBookRequestDto request) {
        String book_name=request.getBook_name();
        String series_name= request.getSeries_name();
        String isbn= request.getIsbn();
        String type=request.getType();
        List<String> authors=request.getAuthors();
        List<String> genres=request.getGenres();
        Integer page_count= request.getPage_count();
        Double book_number= request.getBook_number();
        Book book=book_service.addBook(book_name,authors,isbn,page_count,type,series_name,genres,book_number);
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



    @GetMapping("/get/all")
    public List<GeneralSearchResponseDto> search(@RequestParam("search") String prefix) {
        List<GeneralSearchResponseDto> cached_response_list=(List<GeneralSearchResponseDto>) redis_template.opsForValue().get("PREFIX::"+prefix);
        if(cached_response_list!=null)
            return cached_response_list;
        List<GeneralSearchResponseDto> response_list=new ArrayList<>();
        List<Book> books=book_service.generalSearch(prefix);
        for(Book book : books) {
            GeneralSearchResponseDto response=new GeneralSearchResponseDto();
            response.setBook_name(book.getBook_name());
            for(Author author : book.getAuthors())
                response.getAuthor_name().add(author.getAuthor_name());
            response.setSeries_name(book.getSeries().getSeries_name());
            response_list.add(response);
        }
        redis_template.opsForValue().set("PREFIX::"+prefix, response_list, Duration.ofMinutes(15));
        return response_list;
    }


    @GetMapping("/get/series")
    public List<BookListResponseDto> searchSeries(@RequestParam("search") String series_name) {
        List<BookListResponseDto> cached_response_list =(List<BookListResponseDto>) redis_template.opsForValue().get("SERIES_CACHE::"+series_name);
        if(cached_response_list !=null)
            return cached_response_list;
        List<BookEntry> book_entries=book_service.searchBooksInSeries(series_name);
        List<BookListResponseDto> response_list=new ArrayList<>();
        for(BookEntry book_entry : book_entries) {
            BookListResponseDto response=new BookListResponseDto();
            response.setBook_name(book_entry.getBook().getBook_name());
            response.setBook_number(book_entry.getBook_number());
            response_list.add(response);
        }
        redis_template.opsForValue().set("SERIES_CACHE::"+series_name, response_list, Duration.ofMinutes(15));
        return response_list;
    }


    @GetMapping("/get")
    public SmallBookResponseDto getBookByName(@RequestParam("book") String book_name) {
        return book_service.getBookByName(book_name);
    }
}
