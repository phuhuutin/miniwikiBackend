package com.example.miniwikibackend.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Disable Cross site Request Forgery
        http.csrf().disable();
        //protect endpoints at /api/<type>/secure
        http.authorizeHttpRequests(configurer ->
                configurer
                        .antMatchers("/api/posts/secure/**")
                        .authenticated())
                .oauth2ResourceServer()
                .jwt();
        //add CORS filter
        http.cors();

        //add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        //Force a non-emty response body for 401
        Okta.configureResourceServer401ResponseBody(http);
        return http.build();
    }
}
