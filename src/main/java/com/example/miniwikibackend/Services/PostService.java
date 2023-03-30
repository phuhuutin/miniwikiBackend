package com.example.miniwikibackend.Services;

import com.example.miniwikibackend.DAO.CommentRepository;
import com.example.miniwikibackend.DAO.PostRepository;
import com.example.miniwikibackend.DAO.UserRepository;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Entities.User;
import com.example.miniwikibackend.requests.AddLikeRequest;
import com.example.miniwikibackend.requests.PostCreationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        postToCreate.setLikedUserList(new ArrayList<String>());
        return postRepository.save(postToCreate);
    }

    public Page<Post> getAllPosts(Pageable pageable){
        Page<Post> list = postRepository.findAllByOrderByPostdateDesc(pageable);
        return list;
    }

    public Post addLike(AddLikeRequest addlikerequest) throws JsonProcessingException {
        Optional<Post> post = postRepository.findById(addlikerequest.getPostId());
        if(post.isPresent()) {
            List<String> currentList;
            if(post.get().getLikedUserList() != null) {
                currentList = post.get().getLikedUserList();
            } else currentList = new ArrayList<String>();

            currentList.add(addlikerequest.getUserEmail());
            post.get().setLikedUserList(currentList);
            return postRepository.save(post.get());
        } else throw new EntityNotFoundException("Cant find the post with the given id");
    }

    public Post removeLike(AddLikeRequest addlikerequest) throws JsonProcessingException{
        Optional<Post> post = postRepository.findById(addlikerequest.getPostId());
        if(post.isPresent()) {
            List<String> currentList;
            if(post.get().getLikedUserList() != null && post.get().getLikedUserList().contains(addlikerequest.getUserEmail()) ) {
                currentList = post.get().getLikedUserList();
            } else throw new EntityNotFoundException("This User does not already like the post");


            currentList.remove(addlikerequest.getUserEmail());
            post.get().setLikedUserList(currentList);
            return postRepository.save(post.get());
        } else throw new EntityNotFoundException("Cant find the post with the given id");
    }
}
