package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.DataGeneratorTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String calculateEndPoint = "/calculate";

    @Test
    @DisplayName("Test: validar calculo de precio.")
    public void calculateHouseSuccess() throws Exception {
        Double expectedPrice = 71000.0;
        HouseDTO houseDTO = DataGeneratorTest.getCorrectDataHouse("Casa test");
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String requestJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

    @Test
    @DisplayName("Test: validar ambiente mas grande.")
    public void calculateBiggestRoom() throws Exception {
        RoomDTO expectedBiggestRoom = DataGeneratorTest.getBiggestRoomDto();
        HouseDTO houseDTO = DataGeneratorTest.getCorrectDataHouse("Casa test");

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String requestJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.biggest.enviroment_name").value(expectedBiggestRoom.getEnviroment_name()));
    }

    @Test
    @DisplayName("Test: validar metros cuadrados ambiente.")
    public void calculateSquareFeetByRoom() throws Exception {
        Double squareFeetRoom0 = 25.0;
        Double squareFeetRoom1 = 18.0;
        Double squareFeetRoom2 = 28.0;
        HouseDTO houseDTO = DataGeneratorTest.getCorrectDataHouse("Casa test");

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String requestJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.rooms[0].squareFeet").value(squareFeetRoom0))
                .andExpect(jsonPath("$.rooms[1].squareFeet").value(squareFeetRoom1))
                .andExpect(jsonPath("$.rooms[2].squareFeet").value(squareFeetRoom2));
    }

    @Test
    @DisplayName("Test: validar total metros cuadrados de la casa.")
    public void calculateTotalSquareFeet() throws Exception {
        Double expectedSquareFeet = 71.0;
        HouseDTO houseDTO = DataGeneratorTest.getCorrectDataHouse("Casa test");

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String requestJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.squareFeet").value(expectedSquareFeet));
    }

    @Test
    @DisplayName("Test: Error in body post request throw HttpMessageNotReadableException")
    public void calculateHttpMessageNotReadableException() throws Exception {
        // Arrange
        String expectedStatus = "BAD_REQUEST";
        String expectedError = "HttpMessageNotReadableException";
        String request = DataGeneratorTest.getHouseHttpMessageNotReadableException();

        //Act and Assert
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus))
                .andExpect(jsonPath("$.errors").value(expectedError));
    }

    @Test
    @DisplayName("Test: Error district not found DistrictNotFoundException-")
    public void calculateDistrictNotFoundException() throws Exception {
        //Arrange
        String expectedStatus = "NOT_FOUND";
        String expectedMessage = "The district not fount in database";
        String request = DataGeneratorTest.getHouseDistrictNotFoundException();

        // Act and Assert
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus))
                .andExpect(jsonPath("$.message").value(expectedMessage));

    }


}
