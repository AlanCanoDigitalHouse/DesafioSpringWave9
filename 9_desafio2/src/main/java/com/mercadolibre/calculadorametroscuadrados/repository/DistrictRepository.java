package com.mercadolibre.calculadorametroscuadrados.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class DistrictRepository implements IDistrictRepository {

   /* private final String PATH = "classpath:static/price.json";

    public DistrictRepository() throws FileNotFoundException {
        loadDatabase(PATH);
    }*/
   private final String PATH = "classpath:static/price.json";

    @Override
    public void findNameDistrict(String nameDistrict, String path) throws DistrictNotFoundException {
        List<DistrictDTO> district;

        try {
            district = loadDatabase(path);
        } catch (FileNotFoundException ex) {
            throw new DistrictNotFoundException("No existe el distrito con nombre " + nameDistrict);
        }

        DistrictDTO result = null;
        if (Objects.nonNull(district)) {
            Optional<DistrictDTO> item = district.stream().filter(districtDTO -> districtDTO.getDistrict_name().equals(nameDistrict)).findFirst();
            if (item.isPresent()) {
                result = item.get();
            } else {
                throw new DistrictNotFoundException("No existe el distrito con nombre " + nameDistrict);
            }
        }
    }

    @Override
    public List<DistrictDTO> loadDatabase(String path) throws FileNotFoundException {
        File file = null;
        file = ResourceUtils.getFile(path);
        return mapObject(file);
    }

    @Override
    public List<DistrictDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>() {
        };
        List<DistrictDTO> priceDTOS;
        try {
            priceDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return priceDTOS;
    }
}
