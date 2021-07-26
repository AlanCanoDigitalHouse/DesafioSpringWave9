package com.mercadolibre.tucasitatasaciones.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.ErrorMessage;
import com.mercadolibre.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectWriter writer;

    @BeforeEach
    void init(){
        TestUtilsGenerator.initDataBase();
        TestUtilsGenerator.appendNewDistrict(new District("Palermo",1000D));

        writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }

    @Test
    @DisplayName("Well calculated square meters house")
    void wellCalculateSquareMetersHouse() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();

        String payloadJSON = writer.writeValueAsString(housePayload);

        this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prop_name").value("Test"))
                .andExpect(jsonPath("$.squareMeters").value(111D));
    }

    @Test
    @DisplayName("Well calculated house price")
    void wellCalculatePriceHouse() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();

        String payloadJSON = writer.writeValueAsString(housePayload);

        this.mockMvc.perform(post("/house/house-price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prop_name").value("Test"))
                .andExpect(jsonPath("$.propertyPrice").value(111000D));
    }

    @Test
    @DisplayName("Well calculated house biggest room")
    void wellCalculateHouseBiggestRoom() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getBiggestRoomWith3Rooms();

        String payloadJSON = writer.writeValueAsString(housePayload);
        String responseJSON = writer.writeValueAsString(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/house-biggest-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJSON, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Well calculated rooms square meters")
    void wellCalculateRoomsSquareMeters() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.get3RoomsWithSquareMeters();

        String payloadJSON = writer.writeValueAsString(housePayload);
        String responseJSON = writer.writeValueAsString(response);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/square-meters-rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJSON, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("House with NULL name")
    void houseWithNullName() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        housePayload.setProp_name(null);

        String payloadJSON = writer.writeValueAsString(housePayload);

        this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.prop_name").value("El nombre de la propiedad no puede estar vacío"));
    }

    @Test
    @DisplayName("House with NULL district")
    void houseWithNullDistrict() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        housePayload.setDistrict(null);

        String payloadJSON = writer.writeValueAsString(housePayload);

        this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.district").value("Debe enviar el barrio donde esta ubicada la casa"));
    }

    @Test
    @DisplayName("House with NULL district")
    void houseWithNullEnvironments() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        housePayload.setEnvironments(null);

        String payloadJSON = writer.writeValueAsString(housePayload);

        this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.environments").value("La casa debe tener al menos una habitacion"));
    }

    @Test
    @DisplayName("House with lower case name")
    void houseWithLowerCaseName() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        housePayload.setProp_name("test");

        String payloadJSON = writer.writeValueAsString(housePayload);

        this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.prop_name").value("El nombre de la propiedad debe comenzar con mayúscula."));
    }

    @Test
    @DisplayName("District with price higher than 4000USD")
    void districtWithPriceHigherThanLimit() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        housePayload.setDistrict(new DistrictDTO("Palermo",4001D));

        String payloadJSON = writer.writeValueAsString(housePayload);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage response = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessage.class);
        Assertions.assertTrue(response.getMessage().containsValue("El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S"));
    }

    @Test
    @DisplayName("District with price negative")
    void districtWithPriceNegative() throws Exception {
        HouseDTO housePayload = TestUtilsGenerator.getHouseWith3Rooms();
        housePayload.setDistrict(new DistrictDTO("Palermo",-1000D));

        String payloadJSON = writer.writeValueAsString(housePayload);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/square-meters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage response = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessage.class);
        Assertions.assertTrue(response.getMessage().containsValue("El precio del metro cuadrado no puede ser menor a cero"));
    }
}
