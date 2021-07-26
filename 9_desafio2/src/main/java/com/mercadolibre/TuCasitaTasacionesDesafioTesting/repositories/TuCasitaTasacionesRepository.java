package com.mercadolibre.TuCasitaTasacionesDesafioTesting.repositories;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.entity.District;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TuCasitaTasacionesRepository implements ITuCasitaTasacionesRepository {
    @Override
    public Optional<District> findPriceByLocation(String location) {
        ArrayList<District> district_names;
        district_names = loadDatabase();
        Optional<District> item = Optional.empty();

        if (Objects.nonNull(district_names)) {
            item = district_names.stream().filter(Environment -> Environment.getDistrict_name().equals(location)).findFirst();
        }
        return item;
    }

    @Override
    public ArrayList<District> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("src/main/resources/price.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<ArrayList<District>> typeReference = new TypeReference<>() {
        };
        ArrayList<District> district = null;
        try {
            district = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return district;
    }
}
