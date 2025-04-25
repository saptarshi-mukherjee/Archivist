package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


    @Query(value = "select * from author where author_name = :author_name", nativeQuery = true)
    public Author fetchByAuthorName(@Param("author_name") String author_name);
}
