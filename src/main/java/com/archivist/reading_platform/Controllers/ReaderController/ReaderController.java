package com.archivist.reading_platform.Controllers.ReaderController;


import com.archivist.reading_platform.DTO.RequestDTO.*;
import com.archivist.reading_platform.DTO.ResponseDTO.*;
import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Services.ReaderService.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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



    @PostMapping("/review")
    public AllBookReviewResponseDto reviewBook(@RequestBody ReviewRequestDto request) {
        Book book=reader_service.reviewBook(request.getReader_name(), request.getBook_name(), request.getReview_text());
        List<ReviewResponseDto> review_response_list=new ArrayList<>();
        for(Review review : book.getReviews()) {
            ReviewResponseDto review_response=new ReviewResponseDto();
            review_response.setReader_name(review.getReader().getName());
            review_response.setReview_time(review.getReview_time());
            review_response.setReview_text(review.getReview_text());
            review_response.setLike_count(review.getLike_count());
            review_response_list.add(review_response);
        }
        AllBookReviewResponseDto response=new AllBookReviewResponseDto();
        response.setBook_name(book.getBook_name());
        response.setReviews(review_response_list);
        return response;
    }


    @PostMapping("/review/like/{review_id}")
    public LikeReviewResponseDto likeReview(@PathVariable("review_id") long review_id) {
        Review review=reader_service.likeReview(review_id);
        LikeReviewResponseDto response=new LikeReviewResponseDto();
        response.setReader_name(review.getReader().getName());
        response.setReview_text(review.getReview_text());
        response.setLike_count(review.getLike_count());
        return response;
    }


    @PostMapping("/comment")
    public AllCommentsResponseDto commentOnReview(@RequestBody CommentRequestDto request) {
        Comment comment=reader_service.commentOnReview(request.getReview_id(), request.getCommenter_name(), request.getComment_text());
        List<CommentResponseDto> comment_responses=new ArrayList<>();
        for(Comment com : comment.getReview().getComments()) {
            CommentResponseDto comment_response=new CommentResponseDto();
            comment_response.setCommenter_name(com.getReader().getName());
            comment_response.setComment_time(com.getComment_time());
            comment_response.setComment_text(com.getComment_text());
            comment_response.setLike_count(com.getLike_count());
            comment_responses.add(comment_response);
        }
        AllCommentsResponseDto response=new AllCommentsResponseDto();
        response.setReviewer_name(comment.getReview().getReader().getName());
        response.setReview_time(comment.getReview().getReview_time());
        response.setReview_text(comment.getReview().getReview_text());
        response.setLike_count(comment.getReview().getLike_count());
        response.setComments_list(comment_responses);
        return response;
    }


    @PostMapping("/comment/like/{comment_id}")
    public CommentResponseDto likeComment(@PathVariable("comment_id") long comment_id) {
        Comment comment=reader_service.likeComment(comment_id);
        CommentResponseDto response=new CommentResponseDto();
        response.setCommenter_name(comment.getReader().getName());
        response.setComment_time(comment.getComment_time());
        response.setComment_text(comment.getComment_text());
        response.setLike_count(comment.getLike_count());
        return response;
    }



    @PostMapping("/add/tbr")
    public List<TbrResponseDto> addToTbr(@RequestParam("book") String book_name,
                                         @RequestParam("reader") String reader_name) {
        Reader reader=reader_service.addToTBR(reader_name,book_name);
        List<TbrResponseDto> response_list=new ArrayList<>();
        for(ToRead tbr : reader.getTbr()) {
            TbrResponseDto response=new TbrResponseDto();
            response.setBook_name(tbr.getBook().getBook_name());
            response.setSeries_name(tbr.getBook().getSeries().getSeries_name());
            for(Author author : tbr.getBook().getAuthors())
                response.getAuthor_names().add(author.getAuthor_name());
            response_list.add(response);
        }
        return response_list;
    }


    @GetMapping("/get/tbr/{reader_name}")
    public List<TbrResponseDto> getTbr(@PathVariable("reader_name") String reader_name) {
        List<ToRead> tbr_entries=reader_service.getTbr(reader_name);
        List<TbrResponseDto> response_list=new ArrayList<>();
        for(ToRead tbr : tbr_entries) {
            TbrResponseDto response=new TbrResponseDto();
            response.setBook_name(tbr.getBook().getBook_name());
            response.setSeries_name(tbr.getBook().getSeries().getSeries_name());
            for(Author author : tbr.getBook().getAuthors())
                response.getAuthor_names().add(author.getAuthor_name());
            response_list.add(response);
        }
        return response_list;
    }



    @PostMapping("/add/currently-reading")
    public List<CurrentlyReadingResponseDto> addToCurrentlyReading(@RequestBody AddToCurrentlyReadingRequestDto request) {
        List<CurrentlyReading> current_reads=reader_service.addToCurrentlyReading(request.getReader_name(), request.getBook_name(), request.getIsbn());
        List<CurrentlyReadingResponseDto> response_list=new ArrayList<>();
        for(CurrentlyReading current_read : current_reads) {
            CurrentlyReadingResponseDto response=new CurrentlyReadingResponseDto();
            response.setBook_name(current_read.getBook().getBook_name());
            response.setProgress(current_read.getProgress());
            response_list.add(response);
        }
        return response_list;
    }
}
