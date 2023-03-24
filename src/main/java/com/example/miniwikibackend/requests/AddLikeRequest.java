package com.example.miniwikibackend.requests;

import lombok.Data;

@Data
public class AddLikeRequest {
    private String userEmail;
    private Long postId;
}
