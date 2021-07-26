package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl{

    private String classPath = "classpath:static/price.json";

    public Boolean findDistrictByName(String name){
        List<PriceDTO> priceDTOS;
        priceDTOS = loadDatabase();
        boolean result = false;

        if(Objects.nonNull(priceDTOS)){
            Optional<PriceDTO> item = priceDTOS.stream().filter(priceDTO -> priceDTO.getDistrict_name().equals(name)).findFirst();
            if (item.isPresent()) {
                result = true;
            } else {
                throw new DistrictNotFoundException("The district not fount in database");
            }
        }

        return result;
    }

    private List<PriceDTO> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile(this.classPath);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<PriceDTO> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PriceDTO>> typeReference = new TypeReference<>(){};
        List<PriceDTO> priceDTOS = null;
        try {
            priceDTOS = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }

        return priceDTOS;
    }
}
