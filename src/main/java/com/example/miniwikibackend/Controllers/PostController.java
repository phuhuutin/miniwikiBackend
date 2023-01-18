package com.example.miniwikibackend.Controllers;

import com.example.miniwikibackend.DAO.PostRepository;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Services.PostService;
import com.example.miniwikibackend.requests.PostCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
//    @Autowired
//    public PostController(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
    @GetMapping("/getPostById")
    public Post getPostbyId(@RequestParam Long postId ){
         Post thePost = this.postService.readPost(postId);

       return thePost;
    }
    @PostMapping("/postPost")
    public ResponseEntity<Post> createPost(@RequestBody PostCreationRequest request){
        return ResponseEntity.ok(postService.createPost(request));
    }
    @GetMapping("/getAll")
    public Page<Post> getAllPost(@RequestParam int page, @RequestParam int size ){
        Pageable pageable = PageRequest.of(page,size);

        return postService.getAllPosts(pageable);
    }

}
