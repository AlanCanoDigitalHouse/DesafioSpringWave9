package com.example.desafiotesting.repositories;

import com.example.desafiotesting.dto.PriceDTO;
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
public class PriceRepositoryImpl{
    private final List<PriceDTO> priceDto;

    public PriceRepositoryImpl() {
        this.priceDto = loadDatabase();
    }

    public PriceDTO findPriceLocation(String location) {

        if(Objects.nonNull(priceDto)){
            Optional<PriceDTO> item = priceDto.stream().filter(priceDTO -> priceDTO.getDistrict_name().equals(location)).findFirst();
            if (item.isPresent()){
                return item.get();
            }
        }

        throw new IllegalStateException(location);
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
