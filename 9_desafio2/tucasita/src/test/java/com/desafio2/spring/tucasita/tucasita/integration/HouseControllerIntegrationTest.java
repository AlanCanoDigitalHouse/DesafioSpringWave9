package com.desafio2.spring.tucasita.tucasita.integration;


import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.ErrorMessage;
import com.desafio2.spring.tucasita.tucasita.util.TestUtilGenerator;
import com.desafio2.spring.tucasita.tucasita.util.ValidationErrorsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests")
public class HouseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectWriter writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();

    @Test
    @DisplayName("Validate Correct House Size Post")
    public void validateCorrectHouseSizeTest() throws Exception {
        //arrange
        HouseDTO payloadDTO = TestUtilGenerator.simpleHouseForTestSize33();
        HouseSizeDTO responseDTO = new HouseSizeDTO(33.0);

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/house/size")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Invalid House Name")
    public void invalidHouseNameTest() throws Exception {
        HouseDTO payloadDTO = TestUtilGenerator.invalidHouseName();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        Map<String, String> errors = ValidationErrorsGenerator.getError("name", "El nombre de la propiedad debe comenzar con mayúscula.");

        testValidationErrorsMockMvcPerform(payloadJson, errors);
    }

    @Test
    @DisplayName("Invalid House Long Name")
    public void invalidHouseLongNameTest() throws Exception {
        HouseDTO payloadDTO = TestUtilGenerator.invalidHouseLongName();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        Map<String, String> errors = ValidationErrorsGenerator.getError("name", "La longitud del nombre no puede superar los 30 caracteres.");

        testValidationErrorsMockMvcPerform(payloadJson, errors);
    }

    @Test
    @DisplayName("Invalid District Price")
    public void invalidDistrictPriceTest() throws Exception {
        HouseDTO payloadDTO = TestUtilGenerator.invalidDistrictPriceHouse();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        Map<String, String> errors = ValidationErrorsGenerator.getError("district.district_price", "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.");

        testValidationErrorsMockMvcPerform(payloadJson, errors);
    }

    @Test
    @DisplayName("Empty rooms list")
    public void emptyRoomsListTest() throws Exception {
        HouseDTO payloadDTO = TestUtilGenerator.emptyRoomsList();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        Map<String, String> errors = ValidationErrorsGenerator.getError("rooms", "La lista de cuartos no puede estar vacía.");

        testValidationErrorsMockMvcPerform(payloadJson, errors);
    }


    /** Separated method to avoid code repetition */
    private void testValidationErrorsMockMvcPerform(String payloadJson, Map<String, String> errors) throws Exception{
        this.mockMvc.perform(post("/house/size")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("Validation errors"))
                .andExpect(jsonPath("$.message").value(errors));
    }

}
