package com.archivist.reading_platform.Services.ReaderService;

import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.Comment;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Models.Review;

public interface ReaderService {
    public Reader registerReader(String name, String email, String address, Long phone, String birthday);
    public Book rateBook(String reader_name, String book_name, Double rating_value);
    public Book reviewBook(String reader_name, String book_name, String review_text);
    public Review likeReview(long review_id);
    public Comment commentOnReview(long review_id, String reader_name, String comment_text);
    public Comment likeComment(long comment_id);
    public Reader addToTBR(String reader_name, String book_name);
}
