package com.desafio.tucasitatasaciones.repository;

import com.desafio.tucasitatasaciones.exception.DistrictNotFoundException;
import com.desafio.tucasitatasaciones.model.District;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepository implements IDistrictRepository{
    private final List<District> districtsRepository;

    public DistrictRepository(){
        this.districtsRepository = loadDistrictsDataBase();
    }

    @Override
    public Optional<District> findDistrictByName(String name){
        return this.districtsRepository.stream()
                .filter(ds -> ds.getDistrict_name().equals(name))
                .findAny();
    }

    private List<District> loadDistrictsDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:district.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeRef = new TypeReference<>() {};
        List<District> districts = null;
        try {
            districts = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return districts;
    }
}
