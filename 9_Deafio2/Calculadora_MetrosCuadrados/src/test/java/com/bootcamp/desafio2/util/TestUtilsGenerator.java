package com.bootcamp.desafio2.util;

import com.bootcamp.desafio2.dtos.request.DistrictDTO;
import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import com.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.bootcamp.desafio2.entities.District;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.*;

public class TestUtilsGenerator {

    private static String SCOPE;
    private static ObjectWriter mapper;



    public static void getPriceFile(){
        Properties properties = new Properties();

        try{
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static HouseDTO getDefaultHouse(){

        List<EnvironmentDTO> rooms = new ArrayList<>();
        EnvironmentDTO room1 = new EnvironmentDTO("Cuarto", 5D, 5D, 25D);
        EnvironmentDTO room2 = new EnvironmentDTO("Cocina", 2D, 4D, 8D);
        EnvironmentDTO room3 = new EnvironmentDTO("Comedor", 7D, 4D, 28D);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        DistrictDTO district =  new DistrictDTO("Belgrano", 1100D);

        HouseDTO houseDTO = new HouseDTO("TestHouse", district, rooms);

        return houseDTO;
    }


    public static HouseResponseDTO getDefaultHouseResponse(){

        List<EnvironmentDTO> rooms = new ArrayList<>();
        EnvironmentDTO room1 = new EnvironmentDTO("Cuarto", 5D, 5D, 25D);
        EnvironmentDTO room2 = new EnvironmentDTO("Cocina", 2D, 4D, 8D);
        EnvironmentDTO room3 = new EnvironmentDTO("Comedor", 7D, 4D, 28D);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        HouseResponseDTO houseResponseDTO = new HouseResponseDTO(61D, 67100D, room3, rooms);

        return houseResponseDTO;
    }

    public static HouseDTO getDefaultHouseIncorrectDistrict(){

        List<EnvironmentDTO> rooms = new ArrayList<>();
        EnvironmentDTO room1 = new EnvironmentDTO("Cuarto", 5D, 5D, 25D);
        EnvironmentDTO room2 = new EnvironmentDTO("Cocina", 2D, 4D, 8D);
        EnvironmentDTO room3 = new EnvironmentDTO("Comedor", 7D, 4D, 28D);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);


        DistrictDTO district =  new DistrictDTO("incorrectName", 1100D);
        HouseDTO houseDTO = new HouseDTO("TestHouse", district, rooms);

        return houseDTO;
    }

    public static HouseDTO getDefaultHouseIncorrectPrice(){

        List<EnvironmentDTO> rooms = new ArrayList<>();
        EnvironmentDTO room1 = new EnvironmentDTO("Cuarto", 5D, 5D, 25D);
        EnvironmentDTO room2 = new EnvironmentDTO("Cocina", 2D, 4D, 8D);
        EnvironmentDTO room3 = new EnvironmentDTO("Comedor", 7D, 4D, 28D);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        DistrictDTO district =  new DistrictDTO("Belgrano", 500D);
        HouseDTO houseDTO = new HouseDTO("TestHouse", district, rooms);

        return houseDTO;
    }


    public static List<EnvironmentDTO> getEnvironmentDefault(){

        List<EnvironmentDTO> rooms = new ArrayList<>();
        EnvironmentDTO room1 = new EnvironmentDTO("Cuarto", 5D, 5D, 25D);
        EnvironmentDTO room2 = new EnvironmentDTO("Cocina", 2D, 4D, 8D);
        EnvironmentDTO room3 = new EnvironmentDTO("Comedor", 7D, 4D, 28D);
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return rooms;

    }
}

