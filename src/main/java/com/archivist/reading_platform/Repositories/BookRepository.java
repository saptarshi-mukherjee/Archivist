package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


    @Query(value = "select * from book where book_name = :book_name", nativeQuery = true)
    public Book fetchByBookName(@Param("book_name") String book_name);
}
