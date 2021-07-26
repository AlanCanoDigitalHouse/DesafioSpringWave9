package com.bootcamp.desafio2.repositories.implementation;

import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class DistrictRepository implements IDistrictRepository {

    private final ObjectMapper mapper;
    private String SCOPE;

    public DistrictRepository() {
        this.mapper = new ObjectMapper();
        Properties properties =  new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean districtExist(String name, Double price) throws IOException {
        return loadDataBase().stream().anyMatch(x -> x.getLocation().equalsIgnoreCase(name) && x.getPrice().equals(price));
    }

    private List<District> loadDataBase() throws IOException {
        return mapObject(Paths.get("src/" + this.SCOPE + "/resources/static/prices.json").toFile());
    }

    private List<District> mapObject(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<>(){});
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }
}
