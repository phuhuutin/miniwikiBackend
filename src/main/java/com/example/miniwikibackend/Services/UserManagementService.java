package com.example.miniwikibackend.Services;

import com.example.miniwikibackend.Entities.WikiRole;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final FirebaseAuth firebaseAuth;

    public void setUserClaims(String uid) throws FirebaseAuthException {


        Map<String, Object> claims = Map.of("custom_claims", WikiRole.WIKIUSER.toString());

        firebaseAuth.setCustomUserClaims(uid, claims);
    }
}