package com.example.miniwikibackend.config;

import com.example.miniwikibackend.Entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FirebaseAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)){
            String idToken = authorizationHeader.substring(BEARER_PREFIX.length());
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String email = decodedToken.getEmail();
                Map<String, Object> myCustomClaimValue = decodedToken.getClaims();
                String authority = myCustomClaimValue.get("custom_claims").toString();
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(authority));
                User theUser = firebaseTokenToUserDto(decodedToken);
                theUser.setRole(authority);
                Authentication authentication = new UsernamePasswordAuthenticationToken(theUser, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }

        }
        filterChain.doFilter(request, response);
    }

    private User firebaseTokenToUserDto(FirebaseToken decodedToken){
        User user = null;
        if (decodedToken != null) {
            user = new User();

            user.setUsername(decodedToken.getName());
            user.setEmail(decodedToken.getEmail());
        }
    return user;
    }
}
