package com.bootcamp.desafio2.repositories.implementation;

import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Repository
public class DistrictRepository implements IDistrictRepository {

    private final ObjectMapper mapper;
    private String SCOPE;


    public DistrictRepository(){
        this.mapper = new ObjectMapper();
        Properties properties = new Properties();

        try{
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public District findPriceByLocation(String location) throws DistrictNotExistsException {

        List<District> district = loadDataBase();
        District result = null;
        if (Objects.nonNull(district)) {
            Optional<District> item = district.stream().filter(d-> d.getLocation().equals(location)).findFirst();
            if (!item.isPresent())
                throw new DistrictNotExistsException("El distrito no existe en la base.");
            else
                result = item.get();

        }
        return result;

    }


    private List<District> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("./src/" + this.SCOPE + "/resources/static/prices.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<District> mapObject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<District>> typeReference = new TypeReference<>(){};
        List<District> district = null;
        try {
            district = mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return district;
    }
}

