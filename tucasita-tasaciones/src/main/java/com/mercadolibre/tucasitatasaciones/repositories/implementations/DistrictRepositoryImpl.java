package com.mercadolibre.tucasitatasaciones.repositories.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.IDistrictRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository("districtRepository")
public class DistrictRepositoryImpl implements IDistrictRepository {

    private static List<District> districtList;

    public DistrictRepositoryImpl() {
        districtList = loadDatabase();
    }

    @Override
    public District findByName(String location) throws DistrictNotFoundException {
        return districtList.stream()
                .filter(district -> district.getLocation().equals(location))
                .findFirst()
                .orElseThrow(() -> new DistrictNotFoundException(location));
    }

    private List<District> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/district.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapToObject(file);
    }

    private List<District> mapToObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeReference = new TypeReference<>() {
        };
        List<District> districtsBase = null;
        try {
            districtsBase = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return districtsBase;
    }
}
