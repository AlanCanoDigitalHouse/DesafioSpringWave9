package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
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
    @DisplayName("Calcular el área de casa con 1 habitación")
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
    @DisplayName("Calcular el área de casa con 2 habitaciones")
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
    @DisplayName("Calcular el área de casa con 3 habitaciones")
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
    @DisplayName("Bad request por buscar un distrito que no existe")
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
    @DisplayName("Bad request por pasar precio de distrito igual a cero")
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
    @DisplayName("Bad request por pasar nombre de distrito nulo")
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
    @DisplayName("Bad request por pasar nombre de propiedad vacío")
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
    @DisplayName("Bad request por pasar nombre de distrito de más de 45 caracteres y precio mayor a 4000")
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
    @DisplayName("Bad request por pasar nombre de propiedad y de distrito nulos")
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
    @DisplayName("Bad request por pasar nombre de distrito vacío y precio igual a cero")
    void districtNameEmptyAndDistrictPriceEqualToZeroReturnBadRequestAndTwoErrorMessages() throws Exception {
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
    @DisplayName("Bad request por pasar nombre de propiedad vacío y precio de distrito negativo")
    void propNameEmptyAndDistrictPriceNegativeReturnBadRequestAndAsksForUpperCaseFirstLetter() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .propNameEmptyString()
                .districtPriceNegative();
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
    @DisplayName("Bad request por pasar nombre de propiedad de más de 35 caracteres")
    void propNameLongerThan35ReturnsBadRequest() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .propNameOfLength40();
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

    @Test
    @DisplayName("Bad request por pasar una casa sin habitaciones (lista vacía)")
    void houseWithNoRoomsReturnsBadRequestAndAnErrorMessage() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().roomsEmpty();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("La cantidad de cuartos no puede ser cero.")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    @DisplayName("Bad request por pasar una casa con habitaciones nulas (lista nula)")
    void houseWithNullRoomsReturnsBadRequestAndAnErrorMessage() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house().roomsNull();
        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("La cantidad de cuartos no puede ser cero.")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    @DisplayName("Bad request por pasar precio de distrito nulo")
    void districtPriceNullReturnsBadRequestAndAnErrorMessage() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .districtPriceNull();
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
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    @Rollback
    @DisplayName("Bad request por pasar habitación con ancho negativo")
    void roomWithNegativeWidthReturnsBadRequestAndAnErrorMessage() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house()
                .firstRoomWithNegativeWidth();

        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("El ancho de un ambiente no puede ser negativo ni cero.")))
                .andExpect(jsonPath("$.error").value("Error en payload"));
    }

    @Test
    @Rollback
    @DisplayName("Bad request por pasar 2 habitaciones con nombre vacío")
    void havingTwoRoomsWithEmptyNameReturnsBadRequestAndAnErrorMessageForEachRoom() throws Exception {
        //Arrange
        HouseRequestDTO payloadDTO = HouseRequestInitializer.house();
        payloadDTO.getRooms().get(1).setEnvironment_name("");
        payloadDTO.getRooms().get(2).setEnvironment_name("");

        //Act
        String payloadJson = writer.writeValueAsString(payloadDTO);
        //Assert
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Error en payload"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }
}
