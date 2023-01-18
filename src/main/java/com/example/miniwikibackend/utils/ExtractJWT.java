package com.example.miniwikibackend.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {
    //extraction argument must be \"extraction\"
    public static String payloadJWTExtraction(String token, String extraction){
        token.replace("Bearer", "");
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");
        Map<String,String> map = new HashMap<>();

        for(String entry: entries){
            String[] keyValue = entry.split(":");
            if(keyValue[0].equals("extraction")){
                int remove = 1; // code"
                if(keyValue[1].endsWith("}")){
                    remove = 2; //code"}
                }
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length()-remove);
                keyValue[1] = keyValue[1].substring(1);

            }




        }
        if(map.containsKey("extraction")) return map.get("extraction");

        return null;
    }
}
