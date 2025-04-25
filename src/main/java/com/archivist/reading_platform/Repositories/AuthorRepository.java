package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Author;
import com.archivist.reading_platform.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


    @Query(value = "select * from author where author_name = :author_name", nativeQuery = true)
    public Author fetchByAuthorName(@Param("author_name") String author_name);


    @Query(
            value = "select * from author\n" +
                    "where author_name= :prefix or author_name like concat(:prefix,'%')",
            nativeQuery = true
    )
    public List<Author> fetchAuthorByPrefix(@Param("prefix") String prefix);
}
