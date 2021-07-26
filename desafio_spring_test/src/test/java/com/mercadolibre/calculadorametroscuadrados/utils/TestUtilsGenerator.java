package com.mercadolibre.calculadorametroscuadrados.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.HouseRepositoryDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import static com.mercadolibre.calculadorametroscuadrados.utils.UtilsHome.calculateRoomSquareFeet;

public class TestUtilsGenerator {
    private static String SCOPE;
    private static ObjectWriter mapper;

    public static void emptyUsersFile() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/users.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print("[]");
        writer.close();
    }

    public static HouseDTO getHouseDTO(String name) {
       HouseDTO response = new HouseDTO();
       List<EnvironmentDTO> environments = new ArrayList<>();
       environments.add(new EnvironmentDTO("Habitacion Principal",3.0,4.0));
       environments.add(new EnvironmentDTO("Hall",2.5,1.5));
       environments.add(new EnvironmentDTO("Cocina",2.0,3.0));

       response.setProp_name(name);
       response.setDistrict_name("Belgrano");
       response.setDistrict_price(2000.0);
       response.setEnvironments(environments);
       return response;
    }

    public static HouseDTO getHouseNoExistDostrictName(String name) {
        HouseDTO response = new HouseDTO();
        List<EnvironmentDTO> environments = new ArrayList<>();
        environments.add(new EnvironmentDTO("Habitacion Principal",3.0,4.0));
        environments.add(new EnvironmentDTO("Hall",2.5,1.5));
        environments.add(new EnvironmentDTO("Cocina",2.0,3.0));

        response.setProp_name(name);
        response.setDistrict_name("None");
        response.setDistrict_price(2000.0);
        response.setEnvironments(environments);
        return response;
    }

    public static HouseDTO getHouseEnvironmentNameError(String name) {
        HouseDTO response = new HouseDTO();
        List<EnvironmentDTO> environments = new ArrayList<>();
        environments.add(new EnvironmentDTO("habitacion Principal",3.0,4.0));
        environments.add(new EnvironmentDTO("Hall",2.5,1.5));
        environments.add(new EnvironmentDTO("Cocina",2.0,3.0));

        response.setProp_name(name);
        response.setDistrict_name("Belgrano");
        response.setDistrict_price(2000.0);
        response.setEnvironments(environments);
        return response;
    }

    public static Optional<LocationDTO> getOptionalLocation(){
        return Optional.of(new LocationDTO("Belgrano"));
    }
    public static HouseResponseDTO getHouseResponseDTOfromHouseDTO(HouseDTO house){
        HouseResponseDTO response = new HouseResponseDTO();
        response.setProp_name(house.getProp_name());
        response.setEnvironments(new ArrayList<>());
        for (EnvironmentDTO env : house.getEnvironments()){
            response.getEnvironments().add(new EnvironmentResponseDTO(env.getEnvironment_name(),env.getSquareFeet())); // US-0004
        }
        calculateRoomSquareFeet(house, response);// US-0001 & US-0002
        response.setProp_price(house.getDistrict_price() * response.getProp_area());
        return response;
    }
}
