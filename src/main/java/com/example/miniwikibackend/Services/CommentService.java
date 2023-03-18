package com.example.miniwikibackend.Services;

import com.example.miniwikibackend.DAO.CommentRepository;
import com.example.miniwikibackend.DAO.PostRepository;
import com.example.miniwikibackend.DAO.UserRepository;
import com.example.miniwikibackend.Entities.Comment;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Entities.User;
import com.example.miniwikibackend.requests.CommentCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public Comment createComment(CommentCreationRequest request){
        Comment newComment = new Comment();

        Optional<User> user = userRepository.findUserByEmail(request.getUserEmail());
        if(user.isEmpty()){
            throw  new EntityNotFoundException("Cant find the user with the given email");
        }
        Optional<Post> post = postRepository.findPostById(request.getPostId());
        if(post.isEmpty()){
            throw  new EntityNotFoundException("Cant find the post with the given id");
        }

        newComment.setUser(user.get());

        newComment.setPost(post.get());

        newComment.setBody(request.getBody());




        return commentRepository.save(newComment);



    }


}
