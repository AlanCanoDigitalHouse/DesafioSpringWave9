package com.bootcamp.desafio2.repositories.implementation;

import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class DistrictRepository implements IDistrictRepository {

    private String SCOPE;

    private final ObjectMapper mapper;

    public DistrictRepository() {
        this.mapper = new ObjectMapper();

        Properties properties =  new Properties();

        try {
            var resource = new ClassPathResource("application.properties");
            properties.load(resource.getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean districtExist(String name, Double price) throws IOException {
        return loadDataBase().stream().anyMatch(x ->
                x.getDistrictName().equalsIgnoreCase(name) && x.getDistrictPrice().equals(price));
    }

    private List<District> loadDataBase() throws IOException {
        return mapObject(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/prices.json"));
    }

    private List<District> mapObject(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<>(){});
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }
}
