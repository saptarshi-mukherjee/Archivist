package com.archivist.reading_platform.Controllers.StatsController;

import com.archivist.reading_platform.DTO.ResponseDTO.FaveAuthorResponseDto;
import com.archivist.reading_platform.DTO.ResponseDTO.FaveGenreResponseDto;
import com.archivist.reading_platform.DTO.ResponseDTO.StatsResponseDto;
import com.archivist.reading_platform.Projections.AuthorProjection;
import com.archivist.reading_platform.Projections.GenreProjection;
import com.archivist.reading_platform.Services.StatsService.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {

    @Autowired
    private StatsService stats_service;

    @GetMapping("/stats/get")
    public StatsResponseDto getStats(@RequestParam("reader") String reader_name) {
        long reader_id= stats_service.getReaderId(reader_name);
        long avg_book_length= stats_service.getAverageBookLength(reader_id);
        long time_to_finish=stats_service.getTimeToFinish(reader_id);
        List<GenreProjection> genres=stats_service.getFaveGenres(reader_id);
        List<AuthorProjection> authors=stats_service.getFaveAuthors(reader_id);
        StatsResponseDto response=new StatsResponseDto();
        response.setAvg_book_length(avg_book_length);
        response.setTime_to_finish(time_to_finish);
        for(GenreProjection genre : genres) {
            FaveGenreResponseDto genre_response=new FaveGenreResponseDto();
            genre_response.setGenre(genre.getGenre());
            genre_response.setCount(genre.getCount_val());
            response.getGenre_list().add(genre_response);
        }
        for(AuthorProjection author : authors) {
            FaveAuthorResponseDto author_response=new FaveAuthorResponseDto();
            author_response.setAuthor(author.getAuthor_name());
            author_response.setCount(author.getCount_val());
            response.getAuthor_list().add(author_response);
        }
        return response;
    }
}
