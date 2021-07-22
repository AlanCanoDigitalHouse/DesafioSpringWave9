package com.bootcamp.desafio2.repositories.implementation;

import com.bootcamp.desafio2.entities.Neighborhood;
import com.bootcamp.desafio2.repositories.PriceRepository;
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
public class PriceRepositoryImpl implements PriceRepository {


    @Override
    public Neighborhood valdiarExistencia(String location) {
        List<Neighborhood> priceDtos;
        priceDtos = loadDataBase();
        Neighborhood result = null;
        if (Objects.nonNull(priceDtos)) {
            Optional<Neighborhood> item = priceDtos.stream().filter(priceDto -> priceDto.getLocation().equals(location)).findFirst();
            if (item.isPresent())
                result = item.get();
        }
        return result;
    }

    private List<Neighborhood> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/prices.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<Neighborhood> mapObject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Neighborhood>> typeReference = new TypeReference<>(){};
        List<Neighborhood> priceDtos = null;
        try {
            priceDtos = mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceDtos;
    }
}
