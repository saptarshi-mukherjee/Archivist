package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.ToRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToReadRepository extends JpaRepository<ToRead,Long> {


    @Query(
            value = "select * from to_read where book_id = :book_id",
            nativeQuery = true
    )
    public ToRead fetchByBookId(@Param("book_id") long book_id);
}
