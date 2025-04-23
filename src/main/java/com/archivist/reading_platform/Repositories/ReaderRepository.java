package com.archivist.reading_platform.Repositories;

import com.archivist.reading_platform.Models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {
}
