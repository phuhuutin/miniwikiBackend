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
import java.util.List;
@Converter
public class UserListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> likedUserList) {
        String customerInfoJson = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            customerInfoJson = objectMapper.writeValueAsString(likedUserList);
        } catch (final JsonProcessingException e) {
             
        }

        return customerInfoJson;
    }

    @Override
    public List<String> convertToEntityAttribute(String likedUserListJson) {
        List<String> likedUserList = new ArrayList<>();
        try {
            if(likedUserListJson != null){
                ObjectMapper objectMapper = new ObjectMapper();
                likedUserList = objectMapper.readValue(likedUserListJson, new TypeReference<List<String>>() {});
            }

        } catch (final IOException e) {

        }
        return likedUserList;
    }


}
