package com.example.tucasitatasaciones.handlers;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RepositoryHandlers {
    public static List<DistrictDTO> loadDatabase() {
        File file;
        List<DistrictDTO> districtDTOS = null;
        try {
            file = ResourceUtils.getFile("classpath:static/districts.json");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>() {
            };
            districtDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return districtDTOS;
    }
}
