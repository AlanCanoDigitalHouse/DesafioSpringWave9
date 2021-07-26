package com.example.tucasitatasaciones.repository;

import com.example.tucasitatasaciones.dtos.DistrictDto;
import com.example.tucasitatasaciones.exceptions.GetDistrictException;
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
public class DistrictRepository implements IDistrictRepository{
    private final List<DistrictDto> dsitricts;

    public DistrictRepository() {
        this.dsitricts = loadDatabase();
    }

    @Override
    public DistrictDto findDistrict(String name, Double price){
        DistrictDto result = null;
        if(Objects.nonNull(this.dsitricts)){
            Optional<DistrictDto> item = dsitricts.stream().filter(districtDto -> districtDto.getName().equals(name) &&
                    districtDto.getPrice().equals(price)).findFirst();
            if (!item.isPresent()){
                throw new GetDistrictException();
            }
            result = item.get();
        }
        return result;
    }

    private List<DistrictDto> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/price.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<DistrictDto> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDto>> typeReference = new TypeReference<>(){};
        List<DistrictDto> districtDtos = null;
        try {
            districtDtos = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }

        return districtDtos;
    }

}
