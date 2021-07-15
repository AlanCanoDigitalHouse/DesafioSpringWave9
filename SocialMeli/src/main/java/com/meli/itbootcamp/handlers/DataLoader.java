package com.meli.itbootcamp.handlers;

import com.meli.itbootcamp.dto.UserDTO;
import com.meli.itbootcamp.dto.request.UserRequestDTO;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class DataLoader {

    public static List<UserRequestDTO> loadUserSeller() {
        File file = null;
        List<UserRequestDTO> SellersDTO = null;
        try {
            file = ResourceUtils.getFile("classpath:static/sellers.json");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<UserRequestDTO>> typeReference = new TypeReference<List<UserRequestDTO>>() {};
            SellersDTO = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SellersDTO;
    }

    public static List<UserRequestDTO> loadNonSeller() {
        File file = null;
        List<UserRequestDTO> nonSellersDTO = null;
        try {
            file = ResourceUtils.getFile("classpath:static/nonSeller.json");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<UserRequestDTO>> typeReference = new TypeReference<List<UserRequestDTO>>() {};
            nonSellersDTO = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nonSellersDTO;
    }


}
