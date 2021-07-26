package com.mercadolibre.squaremeter.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepositoryImp implements DistrictRepository {
    final List<DistrictDTO> database;

    public DistrictRepositoryImp() {
        database = loadDataBase();
    }

    @Override
    public Boolean findDistrictByName(String name) {
        boolean response = false;
        Optional<DistrictDTO> op = database.stream().filter(d -> d.getName().equals(name)).findFirst();
        if (op.isPresent()) {
            response = true;
        }
        return response;
    }


    @Override
    public List<DistrictDTO> findDistrictAll() {
        return database;
    }

    private List<DistrictDTO> loadDataBase() {
        ObjectMapper obj = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeRef = new TypeReference<List<DistrictDTO>>() {
        };
        List<DistrictDTO> districtDto = null;
        try {
            File file = ResourceUtils.getFile("classpath:static/district.json");
            districtDto = obj.readValue(file, typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtDto;
    }

}
