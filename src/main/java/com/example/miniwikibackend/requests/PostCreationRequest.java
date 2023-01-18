package com.example.miniwikibackend.requests;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PostCreationRequest {
    private String title;
    private String body;
    private String imgUrl;
    private String userEmail;
}
