package com.example.miniwikibackend.Entities;


import antlr.ASTFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Converter
public class UserListConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> likedUserList) {
        String customerInfoJson = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            customerInfoJson = objectMapper.writeValueAsString(likedUserList);
        } catch (final JsonProcessingException e) {
             
        }

        return customerInfoJson;
    }

    @Override
    public Set<String> convertToEntityAttribute(String likedUserListJson) {
        Set<String> likedUserList = new HashSet<>();
        try {
            if(likedUserListJson != null){
                ObjectMapper objectMapper = new ObjectMapper();
                likedUserList = objectMapper.readValue(likedUserListJson, new TypeReference<Set<String>>() {});
            }

        } catch (final IOException e) {

        }
        return likedUserList;
    }


}
