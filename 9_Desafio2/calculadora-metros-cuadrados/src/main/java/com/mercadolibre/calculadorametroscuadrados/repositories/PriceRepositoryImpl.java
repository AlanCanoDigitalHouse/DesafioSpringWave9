package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DuplicatedLocationException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFoundException;
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
    private final List<PriceDTO> priceDTOS;

    public PriceRepositoryImpl() {
        this.priceDTOS = loadDatabase();
    }

    public PriceDTO findPriceLocation(String location) {

        if(Objects.nonNull(priceDTOS)){
            Optional<PriceDTO> item = priceDTOS.stream().filter(priceDTO -> priceDTO.getLocation().equals(location)).findFirst();
            if (item.isPresent()){
                return item.get();
            }
        }

        throw new LocationNotFoundException(location);
    }

    public void insertPrice(PriceDTO price){
        try{
            PriceDTO priceDTO = findPriceLocation(price.getLocation());
            if(priceDTO.getLocation().equals(price.getLocation())) throw new DuplicatedLocationException(price.getLocation());
        }catch(LocationNotFoundException ex){
            priceDTOS.add(price);
        }
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
