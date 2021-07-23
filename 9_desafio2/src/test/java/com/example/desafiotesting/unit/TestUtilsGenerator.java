package com.example.desafiotesting.unit;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.models.District;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestUtilsGenerator {

    /**
     * Generates a list of valid districts
     *
     * @return List<District>
     */
    public static List<District> getDistrictsSet() {
        District district1 = new District(1, "Palermo", 1000.0);
        District district2 = new District(2, "Belgrano", 1100.0);
        District district3 = new District(3, "Recoleta", 900.0);
        District district4 = new District(4, "Puerto Madero", 2000.0);

        return List.of(district1, district2, district3, district4);
    }


    /**
     * Fills the districts.json file with valid districts
     */
    public static void fillDistrictsFile() {
        List<District> districts = getDistrictsSet();

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/districts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listJson = objectMapper.writeValueAsString(districts);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listJson);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a random json string into the districts.json file in order to
     * force the exception throwing in district repository method mapObject
     *
     * @throws Exception
     */
    public static void corruptDistrictsFile() throws Exception {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/districts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String randomString = new String(array, UTF_8);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(randomString);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * Returns a valid property data object
     *
     * @return PropertyDTO
     */
    public static PropertyDTO getValidProperty() {
        List<EnvironmentDTO> environments = List.of(
                new EnvironmentDTO("Bathroom", 2.0, 10.0),
                new EnvironmentDTO("Kitchen", 7.0, 5.0),
                new EnvironmentDTO("Living Room", 8.0, 4.0),
                new EnvironmentDTO("Bedroom", 8.0, 4.0)
        );
        return new PropertyDTO(
                "Petronas Towers",
                new DistrictDTO(
                        "Belgrano",
                        1100.0
                ),
                environments
        );
    }
}
