package com.mercadolibre.calculadorametroscuadrados.unit.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.EnvironmentRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

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

    public static DistrictDTO getDistrictWithName(String name) {

        DistrictDTO district = new DistrictDTO();
        district.setName(name);
        district.setPrice(1000D);

        return district;
    }

    public static DistrictDTO getDistrictWithPrice(String name, Double price) {

        DistrictDTO district = new DistrictDTO();
        district.setName(name);
        district.setPrice(price);

        return district;
    }

    public static HouseRequestDTO getHouseWith3Environments(String name, String districtName) {

        EnvironmentRequestDTO environment1 = new EnvironmentRequestDTO("Room 1", 8.0, 5.0);
        EnvironmentRequestDTO environment2 = new EnvironmentRequestDTO("Room 2", 4.0, 6.0);
        EnvironmentRequestDTO environment3 = new EnvironmentRequestDTO("Room 3", 3.0, 2.0);

        List<EnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);

        DistrictDTO district = getDistrictWithName(districtName);

        HouseRequestDTO house = new HouseRequestDTO(name, district, environments);

        return house;
    }

    public static HouseRequestDTO getHouseWith5Environments(String name, String districtName) {

        EnvironmentRequestDTO environment1 = new EnvironmentRequestDTO("Room 1", 8.0, 5.0);
        EnvironmentRequestDTO environment2 = new EnvironmentRequestDTO("Room 2", 4.0, 6.0);
        EnvironmentRequestDTO environment3 = new EnvironmentRequestDTO("Room 3", 3.0, 2.0);
        EnvironmentRequestDTO environment4 = new EnvironmentRequestDTO("Room 4", 8.0, 6.0);
        EnvironmentRequestDTO environment5 = new EnvironmentRequestDTO("Room 5", 12.0, 10.0);

        List<EnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);
        environments.add(environment4);
        environments.add(environment5);

        DistrictDTO district = getDistrictWithPrice(districtName, 1200.0);

        HouseRequestDTO house = new HouseRequestDTO(name, district, environments);

        return house;
    }

    public static HouseRequestDTO getHouseWithoutEnvironments(String name, String districtName) {

        List<EnvironmentRequestDTO> environments = new ArrayList<>();

        DistrictDTO district = getDistrictWithName(districtName);

        HouseRequestDTO house = new HouseRequestDTO(name, district, environments);

        return house;
    }

    public static HouseRequestDTO getHouseWithOneEnvironments(String name, String districtName) {

        List<EnvironmentRequestDTO> environments = new ArrayList<>();
        environments.add(new EnvironmentRequestDTO("Room 1", 7D, 18D));

        DistrictDTO district = getDistrictWithName(districtName);

        HouseRequestDTO house = new HouseRequestDTO(name, district, environments);

        return house;
    }

    public static EnvironmentRequestDTO getEnvironmentWithName(String name) {

        EnvironmentRequestDTO environment = new EnvironmentRequestDTO(name, 7D, 18D);

        return environment;
    }

    public static void appendNewDistrict(DistrictDTO district) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        PrintWriter writer = null;

        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/districts.json").getAbsoluteFile().toPath(), StandardCharsets.US_ASCII);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/districts.json"));

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
