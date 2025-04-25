package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEntryRepository extends JpaRepository<BookEntry,Long> {


    @Query(value = "select * from book_entry where book_id = :book_id", nativeQuery = true)
    public BookEntry fetchByBookId(@Param("book_id") Long book_id);
}
