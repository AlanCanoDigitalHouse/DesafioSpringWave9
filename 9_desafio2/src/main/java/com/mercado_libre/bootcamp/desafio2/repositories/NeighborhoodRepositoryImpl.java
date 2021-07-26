package com.mercado_libre.bootcamp.desafio2.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercado_libre.bootcamp.desafio2.exceptions.NeighborhoodNotFoundException;
import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class NeighborhoodRepositoryImpl implements NeighborhoodRepository {

    private List<Neighborhood> neighborhoods;

    @PostConstruct
    public void loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/neighborhoods.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            neighborhoods = new ArrayList<>();
        }
        neighborhoods = mapObject(file);
    }

    private List<Neighborhood> mapObject(File file) {
        var objectMapper = new ObjectMapper();
        TypeReference<List<Neighborhood>> typeReference = new TypeReference<>() {
        };

        try {
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Neighborhood findNeighborhoodByName(String name) {
        return neighborhoods.stream().filter(x -> x.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NeighborhoodNotFoundException("No se encontró el barrio [" + name + "]"));
    }
}
