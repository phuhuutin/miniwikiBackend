package com.example.miniwikibackend.DAO;

import com.example.miniwikibackend.Entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommentRepository  extends JpaRepository<Comment, Long>  {
    @Query("select c from Comment c where c.post.id = ?1")
    Page<Comment> findByPostId(@RequestParam("post_id") Long bookId, Pageable pageable);
}
