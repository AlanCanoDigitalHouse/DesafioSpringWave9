package com.example.desafio1springboot.handlers;

import com.example.desafio1springboot.dtos.UserClientDTO;
import com.example.desafio1springboot.dtos.UserSellerDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class RepositoryHandler {

    public static List<UserClientDTO> loadDatabaseUserClient() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/userClient.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserClientDTO>> typeReference = new TypeReference<List<UserClientDTO>>() {};
        List<UserClientDTO> userClientDTOS = null;
        try {
            userClientDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userClientDTOS;
    }

    public static List<UserSellerDTO> loadDatabaseUserSeller() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/userSeller.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserSellerDTO>> typeReference = new TypeReference<List<UserSellerDTO>>() {};
        List<UserSellerDTO> UserSellerDTOS = null;
        try {
            UserSellerDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UserSellerDTOS;
    }

    public static <T> List<T> loadDatabase(String path) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/" + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {};
        List<T> listDTOS = null;
        try {
            listDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDTOS;
    }

}
