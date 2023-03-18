package com.example.miniwikibackend.Services;

import com.example.miniwikibackend.DAO.CommentRepository;
import com.example.miniwikibackend.DAO.PostRepository;
import com.example.miniwikibackend.DAO.UserRepository;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Entities.User;
import com.example.miniwikibackend.requests.PostCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post readPost(long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return post.get();
        }
        throw new EntityNotFoundException("Cant find the post with the given id");
    }

    public Post createPost(PostCreationRequest post) {
        Post postToCreate = new Post();
        Optional<User> user = userRepository.findUserByEmail(post.getUserEmail());
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User Not Found");
        } else {
            postToCreate.setUser(user.get());
        }


        BeanUtils.copyProperties(post, postToCreate);

        postToCreate.setPostdate(LocalDateTime.now());

        return postRepository.save(postToCreate);
    }

    public Page<Post> getAllPosts(Pageable pageable){
        Page<Post> list = postRepository.findAllByOrderByPostdateDesc(pageable);
        return list;
    }
}
