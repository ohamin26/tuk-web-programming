package com.example.backend.json;

import com.example.backend.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Map;

public class JsonParsing {
    public static Map<String,String> parsing(HttpServletRequest request) {
        Map<String, String > jsonMap = null;
        //request json 가져오기
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String jsonData = sb.toString();

        // Jackson ObjectMapper를 사용하여 JSON 데이터 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readValue(jsonData, User.class);
            jsonMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonMap;
    }

}
