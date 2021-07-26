package com.mercadolibre.calculadorametroscuadrados.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.DataBaseNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnvironmentRepository implements IEnvironmentRepository {
    List<EnvironmentModel> environments;

    public EnvironmentRepository() throws DataBaseNotFoundException {
        // load environment.json file and create a EnvironmentModel List
        environments = this.mapDataBase("src/main/resources/environments.json");
    }

    // find environment by environment name
    @Override
    public Optional<EnvironmentModel> findEnvironment(String environment){
        return environments.stream().filter(env -> env.getEnvironment_name().equals(environment)).findFirst();
    }

    // load mapDataBase of json file
    @Override
    public List<EnvironmentModel> mapDataBase(String path) throws DataBaseNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper(); // indagar en este mappeador
        TypeReference<List<EnvironmentModel>> typeReference = new TypeReference<>() {};
        List<EnvironmentModel> objects = null;
        try{
            objects = objectMapper.readValue(new File(path),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, EnvironmentModel.class));
        } catch (IOException e) {
            throw new DataBaseNotFoundException("Data not found");
        }
        return objects;
    }
}
