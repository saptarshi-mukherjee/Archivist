package com.archivist.reading_platform.DTO.ResponseDTO;

import com.archivist.reading_platform.Models.Review;

import java.util.ArrayList;
import java.util.List;

public class BookResponseDto {
    private String book_name, series_name;
    private List<String> authors, genres;
    private Double avg_rating;
    private Integer rating_count, review_count;
    private List<Review> reviews;
    private Double book_number;


    public BookResponseDto() {
        authors=new ArrayList<>();
        genres=new ArrayList<>();
    }


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }

    public Integer getRating_count() {
        return rating_count;
    }

    public void setRating_count(Integer rating_count) {
        this.rating_count = rating_count;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getBook_number() {
        return book_number;
    }

    public void setBook_number(Double book_number) {
        this.book_number = book_number;
    }
}
