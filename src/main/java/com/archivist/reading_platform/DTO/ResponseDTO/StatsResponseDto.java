package com.archivist.reading_platform.DTO.ResponseDTO;

import com.archivist.reading_platform.Services.StatsService.StatsService;

import java.util.ArrayList;
import java.util.List;

public class StatsResponseDto {
    private long avg_book_length, time_to_finish;
    private List<FaveGenreResponseDto> genre_list;
    private List<FaveAuthorResponseDto> author_list;


    public StatsResponseDto() {
        genre_list=new ArrayList<>();
        author_list=new ArrayList<>();
    }


    public long getAvg_book_length() {
        return avg_book_length;
    }

    public void setAvg_book_length(long avg_book_length) {
        this.avg_book_length = avg_book_length;
    }

    public long getTime_to_finish() {
        return time_to_finish;
    }

    public void setTime_to_finish(long time_to_finish) {
        this.time_to_finish = time_to_finish;
    }

    public List<FaveGenreResponseDto> getGenre_list() {
        return genre_list;
    }

    public void setGenre_list(List<FaveGenreResponseDto> genre_list) {
        this.genre_list = genre_list;
    }

    public List<FaveAuthorResponseDto> getAuthor_list() {
        return author_list;
    }

    public void setAuthor_list(List<FaveAuthorResponseDto> author_list) {
        this.author_list = author_list;
    }
}
