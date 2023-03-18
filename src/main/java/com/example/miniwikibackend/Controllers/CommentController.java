package com.example.miniwikibackend.Controllers;

import com.example.miniwikibackend.Entities.Comment;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Services.CommentService;
import com.example.miniwikibackend.requests.CommentCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins  = "http://localhost:3000/")
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/postComment")
    public ResponseEntity<Comment> postComment(@RequestBody CommentCreationRequest request){
        return ResponseEntity.ok(commentService.createComment(request));
    }
}
