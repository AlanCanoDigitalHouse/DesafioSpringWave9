package com.example.desafiotesting.repository;

import com.example.desafiotesting.exception.DistrictNotFoundException;
import com.example.desafiotesting.model.DistrictModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepository {
    List<DistrictModel> properties = loadDatabase().orElse(new ArrayList<>());

    public boolean propertyExists(String districtName) throws DistrictNotFoundException {
        boolean exists =  properties.stream().anyMatch((item) -> item.getDistrict_name().equals(districtName));
        if (!exists) throw new DistrictNotFoundException();
        return true;
    }

    public Optional<List<DistrictModel>> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/districtsData.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private Optional<List<DistrictModel>> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictModel>> typeReference = new TypeReference<>(){};
        Optional<List<DistrictModel>> usersData = Optional.ofNullable(null);
        try {
            usersData = Optional.ofNullable(objectMapper.readValue(file, typeReference));
        }catch (Exception e){
            e.printStackTrace();
        }
        return usersData;
    }
}
