package com.mercadolibre.tucasita.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.tucasita.domain.District;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DistrictFileHandler {

    public static List<District> loadDistricts() {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:static/district.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> tr = new TypeReference<>() {};
        List<District> result = new ArrayList<>();

        try {
            result = objectMapper.readValue(file, tr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
