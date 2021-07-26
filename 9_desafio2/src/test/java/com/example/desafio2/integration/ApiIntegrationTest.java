package com.example.desafio2.integration;

import com.example.desafio2.dtos.HouseDTO;
import com.example.desafio2.utils.TestGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getSquareMeters() throws Exception {
        HouseDTO houseDTO = TestGenerator.generateHouse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(houseDTO);
        this.mockMvc.perform(post("/house/getSquareMeters")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(jsonPath("$.name").value("La Casita"))
                .andExpect(jsonPath("$.squareMeters").value(80.0))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPriceTest() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getPrice")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("La Casita"))
                .andExpect(jsonPath("$.price").value(24000))
                .andExpect(jsonPath("$.district_name").value("Sur"))
                .andExpect(jsonPath("$.district_price").value(300))
                .andExpect(jsonPath("$.environment",hasSize(3)))
                .andReturn();
    }
    @Test
    void getPriceWithWrongDistrict() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        houseDTO.setDistrict_name("nor-este");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getPrice")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("District not found"))
                .andReturn();
    }

    @Test
    void getBiggerEnvTest() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getBiggerEnv")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.environment_name").value("Habitacion Matrimonial"))
                .andExpect(jsonPath("$.environment_width").value(10))
                .andExpect(jsonPath("$.environment_length").value(6))
                .andReturn();
    }

    @Test
    void getSquareMetersEnv() throws Exception{
        final double SURPRICE = 300;
        HouseDTO houseDTO = TestGenerator.generateHouse();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMetersEnv")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].name",is("Baño")))
                .andExpect(jsonPath("$[0].squareMeters",is(8.0)))
                .andExpect(jsonPath("$[0].price",is(8.0*SURPRICE)))
                .andExpect(jsonPath("$[1].name",is("Habitacion Matrimonial")))
                .andExpect(jsonPath("$[1].squareMeters",is(60.0)))
                .andExpect(jsonPath("$[1].price",is(60.0*SURPRICE)))
                .andExpect(jsonPath("$[2].name",is("Habitacion Visitas")))
                .andExpect(jsonPath("$[2].squareMeters",is(12.0)))
                .andExpect(jsonPath("$[2].price",is(12*SURPRICE)))
                .andReturn();
    }

    @Test
    void getSquareMetersEnvWithWrongDistrict() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        houseDTO.setDistrict_name("nor-este");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMetersEnv")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("District not found"))
                .andReturn();
    }
}
