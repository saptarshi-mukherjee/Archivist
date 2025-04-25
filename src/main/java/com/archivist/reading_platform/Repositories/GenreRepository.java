package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {


    @Query(value = "select * from genre where genre_name = :genre_name", nativeQuery = true)
    public Genre fetchByGenreName(@Param("genre_name") String genre_name);
}
