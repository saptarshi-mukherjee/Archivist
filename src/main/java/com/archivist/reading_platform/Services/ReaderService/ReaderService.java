package com.archivist.reading_platform.Services.ReaderService;

import com.archivist.reading_platform.Models.*;

import java.util.List;

public interface ReaderService {
    public Reader registerReader(String name, String email, String address, Long phone, String birthday);
    public Book rateBook(String reader_name, String book_name, Double rating_value);
    public Book reviewBook(String reader_name, String book_name, String review_text);
    public Review likeReview(long review_id);
    public Comment commentOnReview(long review_id, String reader_name, String comment_text);
    public Comment likeComment(long comment_id);
    public Reader addToTBR(String reader_name, String book_name);
    public List<ToRead> getTbr(String reader_name);
    public List<CurrentlyReading> addToCurrentlyReading(String reader_name, String book_name,String isbn);
    public List<CurrentlyReading> getCurrentlyReading(String reader_name);
    public List<CurrentlyReading> updateProgress(String book_name, String reader_name, int page_no) throws Exception;
    public List<Read> addToRead(String reader_name, String book_name, String isbn) throws Exception;
    public List<Read> getReads(String reader_name);
    public void testScheduled();
}
