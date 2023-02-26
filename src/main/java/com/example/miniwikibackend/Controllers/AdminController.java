package com.example.miniwikibackend.Controllers;

import com.example.miniwikibackend.DAO.UserRepository;
import com.example.miniwikibackend.Entities.Post;
import com.example.miniwikibackend.Entities.User;
import com.example.miniwikibackend.Entities.WikiRole;
import com.example.miniwikibackend.Services.UserManagementService;
import com.example.miniwikibackend.requests.UserCreationRequest;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository userRepository;
    private final UserManagementService userManagementService;
    @Secured("ROLE_ANONYMOUS")
    @GetMapping
    public String testing(){
        return "hello";
    }


    @PostMapping(path = "/user-claims/{uid}")
    public void setUserClaims(@PathVariable String uid) throws FirebaseAuthException {
        userManagementService.setUserClaims(uid);
    }

    @PostMapping(path="/register")
    public ResponseEntity<User> registerUser(@RequestBody UserCreationRequest newUser){
        User u = new User();
        u.setUsername(newUser.getUsername());
        u.setEmail(newUser.getEmail());
        u.setRole(WikiRole.WIKIUSER.toString());
       return ResponseEntity.ok( userRepository.save(u));
    }

    @GetMapping (path="/checkemail")
    public boolean checkEmail(@RequestParam String email){



        return userRepository.existsByEmail(email);
    }



}
