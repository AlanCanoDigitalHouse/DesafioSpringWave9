package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculadoraMetrosCuadradosIntegrationTests {

    ObjectWriter writer;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }

    @Test
    void calculateHouseAreaWithOneRoom() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().withOneRoom();
        //
        String payloadJson = writer.writeValueAsString(payloadDTO);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("3")));
    }

    @Test
    void calculateHouseAreaWithTwoRooms() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().withTwoRooms();
        //
        String payloadJson = writer.writeValueAsString(payloadDTO);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("35")));
    }

    @Test
    void calculateHouseAreaWithThreeRooms() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("50")));
    }

    @Test
    void nonExistingDistrictReturnsBadRequest() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().inDistrict("Chapadmalal");
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void districtPriceEqualToZeroReturnsBadRequest() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().districtPriceEqualToZero();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void districtNameNullReturnsBadRequest() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().districtNameNull();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void propNameEmptyStringSendsBadRequestAndMessageRequiringFirstUpperCaseLetter() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .propNameEmptyString();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("El nombre de la propiedad debe comenzar con may")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }


    @Test
    void districtNameLongerThan45AndPriceGreaterThan4000ReturnBadRequestAndTwoErrorMessages() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .districtPriceGreaterThan4000()
                .districtNameOfLength50();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("no puede superar los 45 caracteres")))
                .andExpect(content().string(containsString("no puede superar los 4000")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    void districtNameNullAndPropNameNullReturnBadRequestAndTwoErrorMessages() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .propNameNull()
                .districtNameNull();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("El nombre de la propiedad no puede estar vac")))
                .andExpect(content().string(containsString("El barrio no puede estar vac")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    void districtNameEmpty5AndDistrictPriceEqualToZeroReturnBadRequestAndTwoErrorMessages() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .districtPriceEqualToZero()
                .districtNameEmptyString();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("El precio de un barrio no puede ser negativo ni cero.")))
                .andExpect(content().string(containsString("El barrio no puede estar vac")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    void propNameEmptyAndDistrictPriceNegativeReturnBadRequestAndAsksForUpperCaseFirstLetter() throws Exception {
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .propNameEmptyString()
                .districtPriceNegative();
        //Arrange
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("El precio de un barrio no puede ser negativo ni cero.")))
                .andExpect(content().string(containsString("El nombre de la propiedad debe comenzar con may")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    void propNameLongerThan35ReturnsBadRequest() throws Exception {
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .propNameOfLength40();
        //Arrange
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("La longitud del nombre no puede superar los 30 caracteres.")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }
/*
    @Test
    void houseWithNoRoomsReturnsBadRequestAndAnErrorMessage() throws Exception {
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().firstRoomWithNegativeWidth();
                //.roomsEmpty();
        //Arrange
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("La longitud del nombre no puede superar los 30 caracteres.")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }
 */
}
