package com.desafio2.spring.tucasita.tucasita.repositories;


import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PriceLocationRepository {

    public DistrictDTO findPriceLocation(String location) {
        List<DistrictDTO> districtDTOS;
        districtDTOS = loadDatabase();
        DistrictDTO result = null;

        if (Objects.nonNull(districtDTOS)) {
            Optional<DistrictDTO> item = districtDTOS.stream()
                    .filter(districtDTO -> location.equals(districtDTO.getDistrict_name())).findFirst();
            if (item.isPresent()) {
                result = item.get();
            }
        }
        return result;
    }

    private List<DistrictDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/price.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<DistrictDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>() {
        };
        List<DistrictDTO> districtDTOS = null;
        try {
            districtDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return districtDTOS;
    }
}
