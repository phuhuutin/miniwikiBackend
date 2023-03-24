package com.example.miniwikibackend.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name="body")
    private String body;

    @Column(name="imgurl")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> comment = new ArrayList<>();
    @Column(name = "postdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime postdate ;
    @Column(name = "snippet")
    private String snippet;


    @Convert(converter = UserListConverter.class)
    @Column(name = "likelist", columnDefinition = "TEXT")
    private List<String> likedUserList;

//    private String likedUserListJson;
//
//    public void serializeCustomerAttributes() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        this.likedUserListJson = objectMapper.writeValueAsString(this.likedUserList);
//    }
//
//    public void deserializeCustomerAttributes() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        this.likedUserList = objectMapper.readValue(likedUserListJson,
//                new TypeReference<List<String>>() {});
//    }



}
