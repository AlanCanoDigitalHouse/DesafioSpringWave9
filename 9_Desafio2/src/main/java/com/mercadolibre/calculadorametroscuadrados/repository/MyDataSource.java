package com.mercadolibre.calculadorametroscuadrados.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.entities.District;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class MyDataSource {

    public List<District> loadDB() throws IOException {
        File file = null;
        file = ResourceUtils.getFile("classpath:static/districts.json");

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeReference = new TypeReference<List<District>>() {
        };

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<District> districts;
        districts = objectMapper.readValue(file, typeReference);

        return districts;
    }

}
