package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatRepository extends JpaRepository<Format,Long> {


    @Query(value = "select * from format where isbn = :isbn", nativeQuery = true)
    public Format fetchByIsbn(@Param("isbn") String isbn);
}
