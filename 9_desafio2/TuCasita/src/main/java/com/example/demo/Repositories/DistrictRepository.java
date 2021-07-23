package com.example.demo.Repositories;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.ExistingNameException;
import com.example.demo.Models.District;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Data
@Repository
public class DistrictRepository implements IDistrictRepository {

    private List<District> districts;

    public DistrictRepository(){
        this.districts = loadDistrictsDB();
    }

    @Override
    public District findDistrictByName(String name) {
        return districts.stream()
                .filter(d->d.getDistrict_name().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addDistrict(District district) throws CustomExceptionHandler {

        District existent = findDistrictByName(district.getDistrict_name());

        if (existent == null){
            districts.add(district);
        } else {
            throw new ExistingNameException(district.getDistrict_name());
        }
    }

    private List<District> loadDistrictsDB() {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:districts.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeRef = new TypeReference<>() {};

        List<District> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (IOException e){
            e.printStackTrace();
        }
        return db;
    }

}
