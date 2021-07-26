package com.example.desafio2.utils;

import com.example.desafio2.dtos.DistrictDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class DistrictGenerator {
    private static List<DistrictDTO> loadJsonDistrict(File file) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeRef = new TypeReference<>() {
        };
        return obj.readValue(file, typeRef);
    }

    private static File loadFile(String filename) throws FileNotFoundException {
        return ResourceUtils.getFile(String.format("classpath:static/%s.json", filename));

    }

    /**
     * Function thats load a certain file in the resource directory
     * @param fileName the name of the file to load
     * @return The list of DistrictDto coming from the district file
     * @throws RuntimeException if the file cannot be found in the resource directory
     */
    public static List<DistrictDTO> getDistrictFile(String fileName) {
        try{
            File file = loadFile(fileName);
            return loadJsonDistrict(file);
        }catch (Exception e) {
            throw new RuntimeException("File not found or json cant be parsed");
        }
    }
}
