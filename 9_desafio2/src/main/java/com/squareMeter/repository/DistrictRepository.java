package com.squareMeter.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareMeter.SquareMeterApplication;
import com.squareMeter.exception.exception.DistrictAlreadyExistsException;
import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.DistrictModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepository {
    final Logger logger = LoggerFactory.getLogger(SquareMeterApplication.class);
    final String FILE_NAME = "./districts.json";

    public DistrictModel findDistrictByName(String name) throws DistrictNotExistsException {
        List<DistrictModel> all = this.loadDatabase();
        Optional<DistrictModel> district = all.stream().filter(model -> model.getDistrict_name().equals(name)).findFirst();
        if(district.isEmpty()) throw new DistrictNotExistsException(name);
        return district.get();
    }
    public void insert(DistrictModel districtModel) throws DistrictAlreadyExistsException {
        List<DistrictModel> models = this.loadDatabase();
        if (modelAlreadyExists(models, districtModel)) throw new DistrictAlreadyExistsException(districtModel.getDistrict_name());
        models.add(districtModel);
        this.saveDatabase(models);
    }

    private boolean modelAlreadyExists(List<DistrictModel> models, DistrictModel model) {
        Optional<DistrictModel> possibleModel = models.stream().filter(x -> x.getDistrict_name().equals(model.getDistrict_name())).findAny();
        return possibleModel.isPresent();
    }

    private void saveDatabase(List<DistrictModel> list) {
        try {
            //To determine de name of the file to write is defined by the model used
            File file = new File(FILE_NAME);
            if (file.createNewFile()) logger.info("Created file db!");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
            logger.info("Models wrote to db!");
        } catch (Exception e) {
            logger.warn("Error occurred when writing to db");
            e.printStackTrace();
        }
    }

    private List<DistrictModel> loadDatabase() {
        File file = null;
        try {
            file = new File(FILE_NAME);
            if (file.createNewFile()) {
                logger.info("File created: " + file.getName());
            }
        } catch (IOException e) {
            logger.warn("An error occurred");
            e.printStackTrace();
        }

        return mapObject(file);
    }
    private List<DistrictModel> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DistrictModel>> typeReference = new TypeReference<>() {
        };
        List<DistrictModel> districts;
        try {
            districts = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            logger.error("Cant read json (probably is empty) full error: " + e.getMessage());
            districts = new ArrayList<>();
        }
        return districts;
    }
    private String getPathFile(String name) {
        return "./" + name + ".json";
    }

}
