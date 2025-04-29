package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


    @Query(
            value = "select * from review where id = :review_id",
            nativeQuery = true
    )
    public Review fetchByReviewId(@Param("review_id") long review_id);
}
