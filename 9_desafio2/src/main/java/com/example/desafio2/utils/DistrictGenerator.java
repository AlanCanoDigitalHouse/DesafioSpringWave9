package com.example.desafio2.utils;

import com.example.desafio2.dtos.DistrictDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DistrictGenerator {
    private static List<DistrictDTO> loadJsonDistrict(File file){
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeRef = new TypeReference<>(){};
        List<DistrictDTO> users = null;
        try{
            users = obj.readValue(file,typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    private static File loadFile(String filename){
        File file = null;
        try {
            file = ResourceUtils.getFile(String.format("classpath:static/%s.json",filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static  List<DistrictDTO> getAllDistrict(){
        File file = loadFile("district");
        if (Objects.nonNull(file)){
            List<DistrictDTO> districtDTOS = loadJsonDistrict(file);
            if (Objects.nonNull(districtDTOS)){
                return districtDTOS;
            }else{
                throw new Error("File doesn't match with the entity class");
            }
        }else{
            throw  new Error("Not file found");
        }
    }
}
