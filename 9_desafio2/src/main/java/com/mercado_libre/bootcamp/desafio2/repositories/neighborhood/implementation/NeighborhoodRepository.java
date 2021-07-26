package com.mercado_libre.bootcamp.desafio2.repositories.neighborhood.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercado_libre.bootcamp.desafio2.exceptions.UnableToAddDuplicatedNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.exceptions.UnexistingNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.neighborhood.NeighborhoodRepositoryI;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class NeighborhoodRepository implements NeighborhoodRepositoryI {

    private String SCOPE;

    private List<Neighborhood> neighborhoodList = new ArrayList<>();

    public NeighborhoodRepository() {
        Properties properties =  new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void loadDistricts() throws IOException {
        List<Neighborhood> loadedData;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/districts.json");
            loadedData = objectMapper.readValue(file, new TypeReference<List<Neighborhood>>(){});
        } catch (FileNotFoundException e) {
            loadedData = new ArrayList<>();
        }

        neighborhoodList.addAll(loadedData);

    }

        @Override
    public boolean isNeighborhoodValid(Neighborhood neighborhood, double price) {
        return neighborhood.getDistrict_price() == price;
    }

    @Override
    public Neighborhood findNeighborhoodByName(String name) {
        Neighborhood neighborhood = neighborhoodList.stream().filter(n->n.getDistrict_name().equals(name)).findAny().orElse(null);
        if(Objects.isNull(neighborhood))
            throw new UnexistingNeighborhoodException("Debe existir el barrio para la asignación de precio");
        return neighborhood;
    }

    @Override
    public void addNewNeighborhood(Neighborhood neighborhood) {
        if(neighborhoodList.contains(neighborhood))
            throw new UnableToAddDuplicatedNeighborhoodException("No se puede agregar un districto repetido");
        neighborhoodList.add(neighborhood);
    }

    @Override
    public int sizeOfNeighborhood(){
        return neighborhoodList.size();
    }
}
