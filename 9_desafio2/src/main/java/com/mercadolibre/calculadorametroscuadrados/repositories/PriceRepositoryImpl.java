package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository class, loads the LocationDTOs objects from Json.
 */
@Repository
public class PriceRepositoryImpl implements PriceRepository{
    List<LocationDTO> locationDTOS;


    /**
     * Constructor.
     * Postconditions: Loads the database
     */
    public PriceRepositoryImpl(){
        locationDTOS = loadDatabase();
    }

    public LocationDTO findPriceLocation(String location) throws LocationNotFound {
        LocationDTO result = null;

        if(Objects.nonNull(locationDTOS)){
            Optional<LocationDTO> item = locationDTOS.stream().filter(locationDTO -> locationDTO.getLocation().equals(location)).findFirst();
            if (item.isPresent()){
                result = item.get();
            }else{
                throw new LocationNotFound();
            }
        }
        return result;
    }

    private List<LocationDTO> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/price.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<LocationDTO> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<LocationDTO>> typeReference = new TypeReference<>(){};
        List<LocationDTO> locationDTOS = null;
        try {
            locationDTOS = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }

        return locationDTOS;
    }
}
