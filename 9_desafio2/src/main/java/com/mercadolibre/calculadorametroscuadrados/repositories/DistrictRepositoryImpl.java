package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dtos.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    @Override
    public Double findPriceDistrict(DistrictDTO district) throws DistrictNotFoundException {
        List<DistrictDTO> database = loadDatabase();
        Double result = null;
        if (Objects.nonNull(database)) {
            Optional<DistrictDTO> item = database.stream().filter(districtDTO -> districtDTO.getDistrict_name().equals(district.getDistrict_name())).findFirst();
            if (item.isPresent()) {
                result = item.get().getDistrict_price();
            }
        }
        if (Objects.nonNull(result))
            return result;
        else throw new DistrictNotFoundException();
    }

    private List<DistrictDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/price.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<DistrictDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>() {
        };
        List<DistrictDTO> districtDTOS = null;
        try {
            districtDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return districtDTOS;
    }
}
