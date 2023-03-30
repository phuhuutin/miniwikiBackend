package com.example.miniwikibackend.Controllers;

import com.example.miniwikibackend.DAO.PostRepository;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Services.PostService;
import com.example.miniwikibackend.requests.AddLikeRequest;
import com.example.miniwikibackend.requests.PostCreationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final PostService postService;

    @GetMapping("/getPostById")
    public Post getPostbyId(@RequestParam Long postId ){
         Post thePost = this.postService.readPost(postId);

       return thePost;
    }
    @PostMapping("/secured/postPost")
    public ResponseEntity<Post> createPost(@RequestBody PostCreationRequest request){
        return ResponseEntity.ok(postService.createPost(request));
    }
    @GetMapping("/getAll")
    public Page<Post> getAllPost(@RequestParam int page, @RequestParam int size ){
        Pageable pageable = PageRequest.of(page,size);

        return postService.getAllPosts(pageable);
    }

    @GetMapping("/secured/hello")
    public String TestHello(Authentication authentication){
        log.info("say hello");
        log.info("from " + authentication.getName() );
        log.info(authentication.toString());
        log.info(authentication.getDetails());
        log.info(authentication.getAuthorities());
        return "HEllo";
    }

    @PutMapping("/secured/addLike")
    public ResponseEntity<Post> addLike(@RequestBody AddLikeRequest request) throws JsonProcessingException {
        return ResponseEntity.ok(postService.addLike(request)) ;
    }
    @PutMapping("/secured/removeLike")
    public ResponseEntity<Post> removeLike(@RequestBody AddLikeRequest request) throws JsonProcessingException {
        return ResponseEntity.ok(postService.removeLike(request)) ;
    }

}
