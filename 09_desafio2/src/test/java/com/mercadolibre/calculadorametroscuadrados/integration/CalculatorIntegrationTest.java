package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculateHouseWithMultipleRoom() throws Exception {
        /* TODO: Object Request Payload*/
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWith3Environments("House 1", "Palermo");
        payloadDTO.setDistrict( new DistrictDTO("Palermo", 1000D));

        /* TODO: Object Response Compare*/
        EnvironmentResponseDTO responseDTO = new EnvironmentResponseDTO(
                "Room1",
                8D,
                5D
        );
        responseDTO.setSquareFeet(40D);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        this.mockMvc.perform(post("/calculator/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.squareFeet").value(70D))
                .andReturn();
    }

    @Test
    void calculateHouseWithoutRooms() throws Exception {
        /* TODO: Object Request Payload*/
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithoutEnvironments("House 2", "Palermo");
        payloadDTO.setDistrict( new DistrictDTO("Palermo", 1000D));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        this.mockMvc.perform(post("/calculator/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.squareFeet").value(0D))
                .andReturn();
    }

    @Test
    void calculateHouseWithOneRooms() throws Exception {
        /* TODO: Object Request Payload*/
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithOneEnvironments("House 3", "Recoleta");
        payloadDTO.setDistrict( new DistrictDTO("Recoleta", 900D));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        this.mockMvc.perform(post("/calculator/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.squareFeet").value(126D))
                .andReturn();
    }

    @Test
    void calculateHousePrice() throws Exception {
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWith5Environments("House 5", "Puerto Madero");
        payloadDTO.setDistrict( new DistrictDTO("Puerto Madero", 2000D));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        this.mockMvc.perform(
                post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value(238D))
                .andExpect(jsonPath("$.price").value(476000));
    }

    @Test
    void calculateBiggestRoom() throws Exception {
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWith3Environments("House 6", "Palermo");
        payloadDTO.setDistrict( new DistrictDTO("Palermo", 1000D));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        this.mockMvc.perform(
                post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.biggest.name").value("Room 1"))
                .andExpect(jsonPath("$.biggest.squareFeet").value(40D));
    }

    @Test
    void calculateRoomsSquareFeet() throws Exception {
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithOneEnvironments("House 7", "Palermo");
        payloadDTO.setDistrict( new DistrictDTO("Palermo", 1000D));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        this.mockMvc.perform(
                post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.environments").isArray())
                .andExpect(jsonPath("$.environments[0].squareFeet").value(126D));
    }
}
