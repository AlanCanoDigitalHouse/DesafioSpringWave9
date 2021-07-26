package com.kjcelis.calculadora_mts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjcelis.calculadora_mts.dto.DistrictDTO;

import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    private final List<DistrictDTO> districtDTOS;

    public DistrictRepositoryImpl() {
        this.districtDTOS = loadDatabase();
    }

    public DistrictDTO findDistrictRepo(String districtName, double districtPrice) throws NotFoundDistricException {
        return districtDTOS.stream().filter(I -> I.getName().equals(districtName.toUpperCase()) && I.getPrice() == (districtPrice)).findFirst().orElseThrow(NotFoundDistricException::new);
    }


    private List<DistrictDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/districts.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<DistrictDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>() {
        };
        List<DistrictDTO> priceDTOS = null;
        try {
            priceDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceDTOS;
    }
}