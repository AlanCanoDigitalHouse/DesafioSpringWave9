package com.mercadolibre.calculadorametroscuadrados.repositories;

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
public class LocationRepository {

    public Boolean locationExists(String district_name){
        List<String> districts;
        districts = loadDatabase();
        Boolean exists = false;

        if(Objects.nonNull(districts)){
            Optional<String> item = districts
                    .stream()
                    .filter( s -> s.equals(district_name)).findFirst();
            if (item.isPresent()){
                exists = true;
            }
        }
        return exists;
    }

    private List<String> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/location.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<String> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<String>> typeReference = new TypeReference<>(){};
        List<String> districs = null;
        try {
            districs = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }

        return districs;
    }

}
