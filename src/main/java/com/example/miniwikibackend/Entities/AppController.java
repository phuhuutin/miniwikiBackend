package com.example.miniwikibackend.Entities;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping(path = "/test")
    @PreAuthorize("hasAuthority('USER')")
    public String test(Principal principal) {
        return principal.getName();
    }
}
