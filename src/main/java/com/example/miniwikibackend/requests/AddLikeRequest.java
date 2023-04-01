package com.example.miniwikibackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class AddLikeRequest {
    private String userEmail;
    private Long postId;

    public AddLikeRequest(String email, Long postId) {
        this.userEmail = email;
        this.postId = postId;
    }

    public AddLikeRequest() {

    }
}
