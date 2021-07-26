package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.Found.DistrictNotFoundException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Repository
public class DistrictRepository implements IDistrictRepository {

    private String SCOPE;

    private Set<DistrictDTO> districts;


    public DistrictRepository() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
            this.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(DistrictDTO district) {
        this.delete(district.getName());

        districts.add(district);

        this.saveData();
    }

    @Override
    public boolean delete(String name) {
        boolean ret = false;

        try {
            DistrictDTO found = this.findByName(name);

            districts.remove(found);
            ret = true;
            this.saveData();

        } catch (DistrictNotFoundException e) {
        }

        return ret;
    }

    public boolean exists(DistrictDTO district) {
        boolean ret = false;

        try {
            ret = this.findByName(district.getName()) != null;
        } catch (DistrictNotFoundException e) {
        }

        return ret;
    }

    @Override
    public DistrictDTO findByName(String name) {
        loadData();
        return districts.stream()
                .filter(district -> district.getName().equals(name))
                .findFirst().orElseThrow(() -> new DistrictNotFoundException(name));
    }

    private void loadData() {
        Set<DistrictDTO> loadedData = new HashSet<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/districts.json");
            loadedData = objectMapper.readValue(file, new TypeReference<Set<DistrictDTO>>() {
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }

        this.districts = loadedData;
    }

    private void saveData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/districts.json");
            objectMapper.writeValue(file, this.districts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while writing to DB, check your JSON formatting.");
        }
    }
}
