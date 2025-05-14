package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Read;
import com.archivist.reading_platform.Projections.AuthorProjection;
import com.archivist.reading_platform.Projections.GenreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadRepository extends JpaRepository<Read,Long> {


    @Query(
            value = "select * from `read` where reader_id = :reader_id",
            nativeQuery = true
    )
    public List<Read> fetchByReaderId(@Param("reader_id") long reader_id);


    @Query(
            value = "select genre.genre_name as genre, count(genre.genre_name) as count_val\n" +
                    "from `read` as r\n" +
                    "join book\n" +
                    "on r.book_id=book.id\n" +
                    "join book_genres\n" +
                    "on book.id=book_genres.books_id\n" +
                    "join genre\n" +
                    "on book_genres.genres_id=genre.id\n" +
                    "where r.reader_id= :reader_id\n" +
                    "group by genre.genre_name\n" +
                    "order by count_val desc\n" +
                    "limit 3",
            nativeQuery = true
    )
    public List<GenreProjection> fetchFaveGenres(@Param("reader_id") long reader_id);


    @Query(
            value = "select author.author_name as author_name, count(author.author_name) as count_val\n" +
                    "from `read` as r\n" +
                    "join book\n" +
                    "on r.book_id=book.id\n" +
                    "join book_authors as ba\n" +
                    "on book.id=ba.books_id\n" +
                    "join author\n" +
                    "on ba.authors_id=author.id\n" +
                    "where r.reader_id=:reader_id\n" +
                    "group by author.author_name\n" +
                    "order by count_val desc\n" +
                    "limit 3",
            nativeQuery = true
    )
    public List<AuthorProjection> fetchFaveAuthors(@Param("reader_id") long reader_id);
}
