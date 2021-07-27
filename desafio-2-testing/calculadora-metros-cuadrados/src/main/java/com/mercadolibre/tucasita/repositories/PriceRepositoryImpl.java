package com.mercadolibre.tucasita.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.tucasita.dto.DistrictDTO;
import com.mercadolibre.tucasita.exception.DistrictNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This Price repository provides the price for a district location. It can be found using the location name.
 */
@Repository
public class PriceRepositoryImpl {

    /** Finds the price for a district using its name. In case it can not find the desired location, a
     *  {@link DistrictNotFoundException} is thrown.
     * @param location name of the district.
     * @return a {@link DistrictDTO} with district name and price
     */
    public DistrictDTO findPriceLocation(String location) {
        List<DistrictDTO> districtDTOS;
        districtDTOS = loadDatabase();
        DistrictDTO result = null;

        if (Objects.nonNull(districtDTOS)) {
            Optional<DistrictDTO> item =
                    districtDTOS.stream().filter(districtDTO -> districtDTO.getDistrict_name().equals(location)).findFirst();
            if (item.isPresent()) {
                result = item.get();
            }
            else{
                throw new DistrictNotFoundException();
            }
        }
        return result;
    }

    /** Loads the price database from a json file.
     * @return DistrictDTO list
     */
    private List<DistrictDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/price.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    /** Maps the json file to a collection of objects
     * @param file the json file
     * @return list of DistrictDTO
     */
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
