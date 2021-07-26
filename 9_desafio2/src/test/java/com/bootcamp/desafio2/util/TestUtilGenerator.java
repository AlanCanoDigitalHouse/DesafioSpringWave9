package com.bootcamp.desafio2.util;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestUtilGenerator {

    private static String SCOPE;
    private static ObjectWriter mapper;

    public static void emptyPricesFile() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/prices.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print("[]");
        writer.close();
    }

        public static PropertyDto getHouseWithRooms (String name) {
            EnvironmentDto room1 = new EnvironmentDto("Bedroom", 14.0, 7.0, null);
            EnvironmentDto room2 = new EnvironmentDto("Bathroom", 6.0, 5.0, null);
            EnvironmentDto room3 = new EnvironmentDto("Kitchen", 4.0, 20.0, null);

            List<EnvironmentDto> rooms = new ArrayList<>();
            rooms.add(room1);
            rooms.add(room2);
            rooms.add(room3);

            DistrictDto districtDto = new DistrictDto("Palermo", 1000.0);

            PropertyDto house = new PropertyDto();
            house.setPropName(name);
            house.setDistrict(districtDto);
            house.setEnvironments(rooms);

            return house;
        }

    public static PropertyDto getHouseWithRoomsWithoutName (String name) {
        EnvironmentDto room1 = new EnvironmentDto("Bedroom", 14.0, 7.0, null);
        EnvironmentDto room2 = new EnvironmentDto("Bathroom", 6.0, 5.0, null);
        EnvironmentDto room3 = new EnvironmentDto("Kitchen", 4.0, 20.0, null);

        List<EnvironmentDto> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        DistrictDto districtDto = new DistrictDto("", 1000.0);

        PropertyDto house = new PropertyDto();
        house.setPropName(name);
        house.setDistrict(districtDto);
        house.setEnvironments(rooms);

        return house;
    }

    public static PropertyDto getHouseWithRoomsWithoutPrice (String name) {
        EnvironmentDto room1 = new EnvironmentDto("Bedroom", 14.0, 7.0, null);
        EnvironmentDto room2 = new EnvironmentDto("Bathroom", 6.0, 5.0, null);
        EnvironmentDto room3 = new EnvironmentDto("Kitchen", 4.0, 20.0, null);

        List<EnvironmentDto> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        DistrictDto districtDto = new DistrictDto("Palermo", null);

        PropertyDto house = new PropertyDto();
        house.setPropName(name);
        house.setDistrict(districtDto);
        house.setEnvironments(rooms);

        return house;
    }

    public static PropertyDto getHouseWithException (String name) {
        EnvironmentDto room1 = new EnvironmentDto("Bedroom", null, 7.0, null);
        EnvironmentDto room2 = new EnvironmentDto("Bathroom", 6.0, 5.0, null);
        EnvironmentDto room3 = new EnvironmentDto("Kitchen", 4.0, 20.0, null);

        List<EnvironmentDto> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        DistrictDto districtDto = new DistrictDto("Palermo", 1000.0);

        PropertyDto house = new PropertyDto();
        house.setPropName(name);
        house.setDistrict(districtDto);
        house.setEnvironments(rooms);

        return house;
    }

    public static PropertyDto getEmptyEnviroments (String name) {

        List<EnvironmentDto> rooms = new ArrayList<>();

        DistrictDto districtDto = new DistrictDto("Palermo", 1000.0);

        PropertyDto house = new PropertyDto();
        house.setPropName(name);
        house.setDistrict(districtDto);
        house.setEnvironments(rooms);

        return house;
    }

    public static PropertyDto getBadPrice (String name) {

        EnvironmentDto room1 = new EnvironmentDto("Bedroom", 14.0, 7.0, null);
        EnvironmentDto room2 = new EnvironmentDto("Bathroom", 6.0, 5.0, null);
        EnvironmentDto room3 = new EnvironmentDto("Kitchen", 4.0, 20.0, null);

        List<EnvironmentDto> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        DistrictDto districtDto = new DistrictDto("Palermo", 5000.0);

        PropertyDto house = new PropertyDto();
        house.setPropName(name);
        house.setDistrict(districtDto);
        house.setEnvironments(rooms);

        return house;
    }

    public static PropertyDto getWrongEnviroment (String name) {
        EnvironmentDto environmentDto = new EnvironmentDto("Bedroom",20.0, 35.0, null);
        List<EnvironmentDto> rooms = new ArrayList<>();
        rooms.add(environmentDto);

        DistrictDto districtDto = new DistrictDto("Palermo", 1000.0);

        PropertyDto house = new PropertyDto();
        house.setPropName(name);
        house.setDistrict(districtDto);
        house.setEnvironments(rooms);

        return house;
    }


    public static void appendNewDistrict(DistrictDto districtDto) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        PrintWriter writer = null;

        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/static/prices.json").getAbsoluteFile().toPath(), StandardCharsets.US_ASCII);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/prices.json"));

            try {
                String studentAsString = mapper.writeValueAsString(districtDto);
                writer.print(content.substring(0, content.length()-1));
                if (content.length()>2) writer.print(", ");
                writer.print(studentAsString);
                writer.print("]");
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.close();
    }
}
