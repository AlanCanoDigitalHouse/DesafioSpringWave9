package com.example.tucasita.repositories.implementations;

import com.example.tucasita.entities.DistrictEntity;
import com.example.tucasita.exceptions.general.DBNotAvailableException;
import com.example.tucasita.repositories.interfaces.DistrictRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    public static final java.lang.String DISTRICTS_DB_ROUTE = "classpath:static/districts.json";

    @Override
    public Double getPriceOrThrowException(String districtName) {
        return getDBPrices().stream()
                .filter(d -> d.getName().equals(districtName))
                .findAny().orElseThrow()
                .getPrice();
    }

    private List<DistrictEntity> getDBPrices() {
        File file;
        try {
            file = ResourceUtils.getFile(DISTRICTS_DB_ROUTE);
        } catch (Exception e) {
            throw new DBNotAvailableException("Error finding DB", e);
        }
        return loadDistrictsJSON(file);
    }

    private List<DistrictEntity> loadDistrictsJSON(File file) throws DBNotAvailableException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictEntity>> typeReference = new TypeReference<>() {
        };
        List<DistrictEntity> districts;
        try {
            districts = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error reading DB", e);
        }
        return districts;
    }
}
