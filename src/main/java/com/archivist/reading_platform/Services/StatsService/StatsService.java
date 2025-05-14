package com.archivist.reading_platform.Services.StatsService;

import com.archivist.reading_platform.Projections.AuthorProjection;
import com.archivist.reading_platform.Projections.GenreProjection;

import java.util.List;

public interface StatsService {
    public Long getReaderId(String reader_name);
    public Long getAverageBookLength(long reader_id);
    public Long getTimeToFinish(long reader_id);
    public List<GenreProjection> getFaveGenres(long reader_id);
    public List<AuthorProjection> getFaveAuthors(long reader_id);
}
