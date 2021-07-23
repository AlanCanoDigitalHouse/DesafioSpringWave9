package com.bootcamp.desafio2.util;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.EnvironmentDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.entities.District;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
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
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/districts.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        writer.print("[]");
        writer.close();
    }

    public static void wrongDataBase() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/districts.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }

    public static PropertyDto getDefaultProperty() {
        PropertyDto response = new PropertyDto();
        response.setProp_name("Test");
        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(new EnvironmentDto("Baño", 10D, 7D, null));
        environments.add(new EnvironmentDto("Sala", 15D, 12D, null));
        environments.add(new EnvironmentDto("Cocina", 10D, 20D, null));
        response.setEnvironments(environments);
        response.setDistrict(new DistrictDto("Boita", 3000D));
        return response;
    }

    public static ResponseDto getDefaultResponse() {
        List<EnvironmentDto> environments = new ArrayList<>();
        environments.add(new EnvironmentDto("Baño", 10D, 7D, 70D));
        environments.add(new EnvironmentDto("Sala", 15D, 12D, 180D));
        environments.add(new EnvironmentDto("Cocina", 10D, 20D, 200D));
        return new ResponseDto(450D, 1350000D, "Cocina", environments);
    }

    public static void appendNewDistrict(District district) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        PrintWriter writer = null;
        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/static/districts.json")
                    .getAbsoluteFile().toPath(), StandardCharsets.US_ASCII);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/static/districts.json"));
            try {
                String studentAsString = mapper.writeValueAsString(district);
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
