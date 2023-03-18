package com.example.miniwikibackend.DAO;


import com.example.miniwikibackend.Entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;


import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByPostdateDesc(Pageable pageable);
    Page<Post> findByTitleContainingOrderByPostdateDesc(@RequestParam("title") String title, Pageable pageable);

    @Query("select p from Post p where p.id = ?1")
    Optional<Post> findPostById(long id);
}
