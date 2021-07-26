package com.meli.bootcamp.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.bootcamp.dto.DistrictDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class DistrictRepositoryImp implements IDistrictRepository {
        private  final List<DistrictDTO> districtDTO = loadDatabase();

    public DistrictRepositoryImp() throws IOException {
    }

    @Override
    public boolean validateDistrict(String District_name) throws DistrictException {
        if(Objects.nonNull(districtDTO)){
            Optional<DistrictDTO> item =
                    districtDTO.stream().filter(priceDTO -> priceDTO.getDistrict_name().equals(District_name)).findFirst();
            if (item.isPresent()){
                return true;
            }
            else {
                throw new DistrictException(DistrictException.DISTRICT_NOTFOUND);
            }
        }
        return false;
    }

    private List<DistrictDTO> loadDatabase() throws IOException {
        File file;
            file = ResourceUtils.getFile("classpath:static/districts.json");
        return mapObject(file);
    }

    private List<DistrictDTO> mapObject(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>(){};
        List<DistrictDTO> districtDTO = null;
            districtDTO = objectMapper.readValue(file, typeReference);

        return districtDTO;
    }
}
