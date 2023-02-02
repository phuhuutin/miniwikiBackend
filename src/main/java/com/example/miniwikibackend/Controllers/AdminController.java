package com.example.miniwikibackend.Controllers;

import com.example.miniwikibackend.Entities.WikiRole;
import com.example.miniwikibackend.Services.UserManagementService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserManagementService userManagementService;
    @Secured("ROLE_ANONYMOUS")
    @GetMapping
    public String testing(){
        return "hello";
    }

    @Secured("ROLE_ANONYMOUS")
    @PostMapping(path = "/user-claims/{uid}")
    public void setUserClaims(
            @PathVariable String uid,
            @RequestBody WikiRole requestedClaims
    ) throws FirebaseAuthException {
        userManagementService.setUserClaims(uid, requestedClaims);
    }



}
