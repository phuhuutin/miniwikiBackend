package com.example.miniwikibackend.requests;

import lombok.Data;
@Data
public class CommentCreationRequest {
    private String body;

    private String userEmail;

    private  long postId;
}
