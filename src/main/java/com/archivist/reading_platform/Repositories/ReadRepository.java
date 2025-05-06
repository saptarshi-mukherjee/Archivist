package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Read;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadRepository extends JpaRepository<Read,Long> {
}
