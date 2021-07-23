package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.HouseRepositoryDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class HouseDAO implements IHouseDAO{
    private String SCOPE;

    private Set<HouseRepositoryDTO> houses;

    private Set<LocationDTO> strata;

    public HouseDAO(){
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
            this.loadData();
            this.loadStrataData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData(){
        Set<HouseRepositoryDTO> loadedData = new HashSet<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/houses.json");
            loadedData = objectMapper.readValue(file, new TypeReference<Set<HouseRepositoryDTO>>(){});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }

        this.houses = loadedData;
    }

    private void loadStrataData(){
        Set<LocationDTO> loadedData = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/price.json");
            loadedData = objectMapper.readValue(file, new TypeReference<Set<LocationDTO>>(){});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }
        this.strata = loadedData;
    }


    private void saveData(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/houses.json");
            objectMapper.writeValue(file, this.houses);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your JSON formatting.");
        }
    }


    @Override
    public void save(HouseRepositoryDTO house) {
        this.delete(house.getProp_name());
        houses.add(house);
        this.saveData();
    }

    @Override
    public boolean delete(String prop_name) {
        boolean response = false;
        Optional<HouseRepositoryDTO> house= houses
                .stream()
                .filter(hos -> hos.getProp_name().equals(prop_name))
                .findFirst();
        if(house.isPresent()){
            houses.remove(house.get());
            response = true;
            this.saveData();
        }
        return response;
    }

    @Override
    public boolean exists(HouseDTO house) {
        boolean response = false;
        Optional<HouseRepositoryDTO> houseTemp = houses.stream()
                .filter(hos -> hos.equals(house))
                .findFirst();
        if(houseTemp.isPresent())response = true;
        return response;
    }

    @Override
    public Optional<LocationDTO> findStratumByLocationName(String locationName) {
        loadStrataData();
        return strata.stream()
                .filter(str -> str.getLocation().equals(locationName))
                .findFirst();
    }
}
