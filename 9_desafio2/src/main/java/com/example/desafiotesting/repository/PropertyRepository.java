package com.example.desafiotesting.repository;

import com.example.desafiotesting.model.PropertyModel;
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
public class PropertyRepository {
    List<PropertyModel> properties = loadDatabase().orElse(new ArrayList<>());

    public boolean propertyExists(String propertyName) {
        return properties.stream().anyMatch((item) -> item.getProp_name().equals(propertyName));
    }

    public Optional<List<PropertyModel>> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/propertiesData.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private Optional<List<PropertyModel>> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PropertyModel>> typeReference = new TypeReference<>(){};
        Optional<List<PropertyModel>> usersData = Optional.ofNullable(null);
        try {
            usersData = Optional.ofNullable(objectMapper.readValue(file, typeReference));
        }catch (Exception e){
            e.printStackTrace();
        }
        return usersData;
    }
}
