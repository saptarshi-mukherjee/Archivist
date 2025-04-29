package com.archivist.reading_platform.Repositories;


import com.archivist.reading_platform.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {


    @Query(
            value = "select * from comment where id = :comment_id",
            nativeQuery = true
    )
    public Comment fetchByCommentId(@Param("comment_id") long comment_id);

}
