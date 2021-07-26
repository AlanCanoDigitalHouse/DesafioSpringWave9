package com.mercadolibre.tucasitatasaciones.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dtos.request.EnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.entities.District;
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

public class TestUtilsGenerator {

    private static String SCOPE;
    private static ObjectWriter mapper;

    public static void initDataBase() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/district.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        writer.print("[]");
        writer.close();
    }

    public static HouseDTO getHouseWith3Rooms() {

        List<EnvironmentDTO> rooms = new ArrayList<>();
        rooms.add(new EnvironmentDTO("Banio",3D,3D, 9D));
        rooms.add(new EnvironmentDTO("Living",6D,10D, 60D));
        rooms.add(new EnvironmentDTO("Cocina",7D,6D, 42D));

        DistrictDTO district = new DistrictDTO("Palermo",1000D);

        HouseDTO house = new HouseDTO();
        house.setEnvironments(rooms);
        house.setProp_name("Test");
        house.setDistrict(district);

        return house;
    }

    public static AssessmentDTO getPriceWith3Rooms() {
        return AssessmentDTO.builder()
                .prop_name("Test")
                .propertyPrice(111000D)
                .build();
    }

    public static AssessmentDTO getSquareMetersWith3Rooms() {
        return AssessmentDTO.builder()
                .prop_name("Test")
                .squareMeters(111D)
                .build();
    }

    public static AssessmentDTO getBiggestRoomWith3Rooms() {
        EnvironmentDTO room1 = new EnvironmentDTO("Banio",3D,3D,9D);
        EnvironmentDTO room2 = new EnvironmentDTO("Living",6D,10D,60D);
        EnvironmentDTO room3 = new EnvironmentDTO("Cocina",7D,6D,42D);

        List<EnvironmentDTO> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return AssessmentDTO.builder()
                .prop_name("Test")
                .biggestRoom(room2)
                .build();
    }

    public static AssessmentDTO get3RoomsWithSquareMeters() {
        EnvironmentDTO room1 = new EnvironmentDTO("Banio",3D,3D,9D);
        EnvironmentDTO room2 = new EnvironmentDTO("Living",6D,10D,60D);
        EnvironmentDTO room3 = new EnvironmentDTO("Cocina",7D,6D,42D);

        List<EnvironmentDTO> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        return AssessmentDTO.builder()
                .prop_name("Test")
                .environments(rooms)
                .build();
    }

    public static void appendNewDistrict(District stu) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        PrintWriter writer = null;

        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/static/district.json").getAbsoluteFile().toPath(), StandardCharsets.US_ASCII);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/district.json"));

            try {
                String studentAsString = mapper.writeValueAsString(stu);
                writer.print(content.substring(0, content.length()-1));
                if (content.length()>2) writer.print(", ");
                writer.print(studentAsString);
                writer.print("]");
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.close();
    }



}
