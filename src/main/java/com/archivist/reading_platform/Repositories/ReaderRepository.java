package com.archivist.reading_platform.Repositories;

import com.archivist.reading_platform.Models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {



    @Query(
            value = "select * from reader where name = :reader_name",
            nativeQuery = true
    )
    public Reader fetchByReaderName(@Param("reader_name") String reader_name);



    @Query(
            value = "select * from reader where email = :email",
            nativeQuery = true
    )
    public Reader fetchByReaderEmail(@Param("email") String email);
}
