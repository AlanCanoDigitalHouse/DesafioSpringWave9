package com.example.desafio1springboot.handlers;

import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

public class RepositoryHandler {

    public static List<UserSellerDTO> loadDatabaseUserSeller() {
        File file = null;
        List<UserSellerDTO> UserSellerDTOS = null;
        try {
            file = ResourceUtils.getFile("classpath:static/userSeller.json");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<UserSellerDTO>> typeReference = new TypeReference<List<UserSellerDTO>>() {};
            UserSellerDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UserSellerDTOS;
    }
}
