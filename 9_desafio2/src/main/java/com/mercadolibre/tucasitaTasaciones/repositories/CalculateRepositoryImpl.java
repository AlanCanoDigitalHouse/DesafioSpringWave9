package com.mercadolibre.tucasitaTasaciones.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class CalculateRepositoryImpl implements CalculateRepository {

    private String SCOPE;

    public CalculateRepositoryImpl() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca en la base de datos el barrio pasado, si no lo encuentra lanza una excepción.
     * @param location
     * @return DistrictDTO
     * @throws ExceptionLocationNotFound
     */
    @Override
    public DistrictDTO findPriceLocation(String location) throws ExceptionLocationNotFound {
        List<DistrictDTO> districtDTOS = loadDatabase();
        DistrictDTO result = null;

        if (Objects.nonNull(districtDTOS)) {
            Optional<DistrictDTO> item = districtDTOS.stream().filter(districtDTO -> districtDTO.getDistrict_name().equals(location)).findFirst();
            if (item.isPresent()) {
                result = item.get();
            } else {
                throw new ExceptionLocationNotFound("El barrio " + location + " no existe.");
            }
        }
        return result;

    }

    /**
     * Verifica que el precio pasado sea el mismo de la base de datos.
     * @param district
     * @throws ExceptionLocationPriceIncorrect
     * @throws ExceptionLocationNotFound
     */
    @Override
    public void checkPrice(DistrictDTO district) throws ExceptionLocationPriceIncorrect, ExceptionLocationNotFound {
        DistrictDTO correctPrice = findPriceLocation(district.getDistrict_name());
        if ((correctPrice.getDistrict_price()).equals(district.getDistrict_price())) {
        } else throw new ExceptionLocationPriceIncorrect("El precio por metro cuadradro del barrio es incorrecto.");
    }

    /**
     * Carga la base de datos
     * @return List<DistrictDTO>
     */
    private List<DistrictDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("src/" + SCOPE + "/resources/static/districts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<DistrictDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictDTO>> typeReference = new TypeReference<>() {
        };
        List<DistrictDTO> districtDTOS = new ArrayList<>();
        try {
            districtDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return districtDTOS;
    }
}
