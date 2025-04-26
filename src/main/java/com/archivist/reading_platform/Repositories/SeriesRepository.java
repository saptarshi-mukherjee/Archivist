package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series,Long> {

    @Query(value = "select * from series where series_name = :series_name", nativeQuery = true)
    public Series fetchBySeriesName(@Param("series_name") String series_name);


    @Query(
            value = "select * from series\n" +
                    "where series_name = :prefix or series_name like concat(:prefix,'%')",
            nativeQuery = true
    )
    public List<Series> fetchSeriesByPrefix(@Param("prefix") String prefix);


}
