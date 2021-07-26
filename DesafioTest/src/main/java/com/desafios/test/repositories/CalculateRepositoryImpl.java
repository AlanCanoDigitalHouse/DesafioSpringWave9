package com.desafios.test.repositories;

import com.desafios.test.dtos.DistrictDTO;
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
public class CalculateRepositoryImpl implements CalculateRepository {

    private List<DistrictDTO> districtList;

    public CalculateRepositoryImpl() {
        this.districtList = loadPricesDatabase();
    }

    public DistrictDTO findPriceDistrict(String location) {
        DistrictDTO result = null;
        if(Objects.nonNull(this.districtList)){
            Optional<DistrictDTO> item = this.districtList.stream().filter(district -> district.getDistrict_name().equals(location)).findFirst();
            if(item.isPresent())
                result = item.get();
        }
        return result;
    }

    private List<DistrictDTO> loadPricesDatabase() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/districts.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<DistrictDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>(){};
        List<DistrictDTO> priceDTOS = null;
        try {
            priceDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return priceDTOS;
    }
}
