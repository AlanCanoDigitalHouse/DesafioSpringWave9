package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl{

    public PriceDTO findPriceLocation(String location){
        List<PriceDTO> priceDTOS;
        priceDTOS = loadDatabase();
        PriceDTO result = null;

        if(Objects.nonNull(priceDTOS)){
            Optional<PriceDTO> item = priceDTOS.stream().filter(priceDTO -> priceDTO.getLocation().equals(location)).findFirst();
            if (item.isPresent()){
                result = item.get();
            }
        }
        return result;
    }

    private List<PriceDTO> loadDatabase(){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/price.json");

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
