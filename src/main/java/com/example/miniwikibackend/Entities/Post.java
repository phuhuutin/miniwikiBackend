package com.example.miniwikibackend.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
//    @Column(name = "user_id")
//    private Long userId;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate postdate ;
    @Column(name = "snippet")
    private String snippet;
}
