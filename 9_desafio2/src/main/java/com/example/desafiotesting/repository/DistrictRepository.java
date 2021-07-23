package com.example.desafiotesting.repository;

import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;
import com.example.desafiotesting.models.District;
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
public class DistrictRepository {
    public District findByName(String location) throws DistrictException, FileException {
        List<District> districts;
        districts = loadDatabase();
        District result = null;

        if (Objects.nonNull(districts)) {
            Optional<District> item = districts.stream().filter(l -> l.getLocation().equals(location)).findFirst();
            if (item.isPresent()) {
                result = item.get();
            } else
                throw new DistrictException(String.format(DistrictException.NAME_NOT_FOUND, location));
        }
        return result;
    }

    public District findById(Integer id) throws DistrictException, FileException {
        List<District> districts;
        districts = loadDatabase();
        District result = null;

        if (Objects.nonNull(districts)) {
            Optional<District> item = districts.stream().filter(l -> l.getId().equals(id)).findAny();
            if (item.isPresent()) {
                result = item.get();
            } else
                throw new DistrictException(String.format(DistrictException.ID_NOT_FOUND, id));
        }
        return result;
    }

    private List<District> loadDatabase() throws FileException {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/districts.json");

        } catch (FileNotFoundException e) {
            throw new FileException(FileException.FILE_NOT_FOUND);
        }

        return mapObject(file);
    }

    private List<District> mapObject(File file) throws FileException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeReference = new TypeReference<>() {
        };
        List<District> priceDTOS = null;
        try {
            priceDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new FileException(FileException.CANT_DECODE_FILE);
        }

        return priceDTOS;
    }
}
