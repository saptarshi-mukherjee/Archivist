package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.CurrentlyReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentlyReadingRepository extends JpaRepository<CurrentlyReading,Long> {
}
