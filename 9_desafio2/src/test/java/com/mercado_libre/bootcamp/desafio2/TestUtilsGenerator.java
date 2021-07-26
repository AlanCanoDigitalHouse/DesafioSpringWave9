package com.mercado_libre.bootcamp.desafio2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.NeighborhoodRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.RoomRequestDTO;
import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;
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

    public static void emptyDistrictsFile() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/districts.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print("[]");
        writer.close();
    }

    public static void appendNewNeighborhood(NeighborhoodRequestDTO neighborhood) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        PrintWriter writer = null;

        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/districts.json").getAbsoluteFile().toPath(), StandardCharsets.US_ASCII);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/districts.json"));

            try {
                String neighborhoodAsString = mapper.writeValueAsString(neighborhood);
                writer.print(content.substring(0, content.length()-1));
                if (content.length()>2) writer.print(", ");
                writer.print(neighborhoodAsString);
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

    public static HouseRequestDTO getRequestWithNeighborhood(NeighborhoodRequestDTO neighborhood){
        RoomRequestDTO room = new RoomRequestDTO("Living", 25, 33);
        List<RoomRequestDTO> rooms = new ArrayList<>();
        rooms.add(room);
        return new HouseRequestDTO("Casa de Karen", neighborhood, rooms);
    }


}
