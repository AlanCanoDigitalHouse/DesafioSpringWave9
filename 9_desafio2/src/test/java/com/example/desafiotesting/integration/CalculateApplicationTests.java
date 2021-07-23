package com.example.desafiotesting.integration;

import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.ErrorDto;
import com.example.desafiotesting.dto.response.PropertyResponseDTO;
import com.example.desafiotesting.repository.DistrictRepository;
import com.example.desafiotesting.unit.TestUtilsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @AfterEach
    private void setUp() {
        TestUtilsGenerator.fillDistrictsFile();
    }

    @Test
    public void calculateTest() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        PropertyResponseDTO responseDTO = new PropertyResponseDTO(payloadDTO);
        responseDTO.setBiggest(
                new EnvironmentDTO(
                        "Kitchen",
                        7.0,
                        5.0
                )
        );
        responseDTO.setSquareMetter(119.0);
        responseDTO.setPrice(130900.0);

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void calculateTestInvalidPropertyNameTest() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();
        payloadDTO.setProp_name("lowercase name");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        ErrorDto responseDTO = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Validations Error in fields: {prop_name=Property name must start with uppercase}");

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void calculateTestDistrictPriceTooHighTest() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();
        payloadDTO.getDistrict().setDistrict_price(999999.9);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        ErrorDto responseDTO = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Validations Error in fields: {district.district_price=District price can't be higher than 4500.00 U$S}");

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void calculateTestDistrictPriceNegativeTest() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();
        payloadDTO.getDistrict().setDistrict_price(-10.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        ErrorDto responseDTO = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Validations Error in fields: {district.district_price=District price can't be a negative number}");

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void calculateTestDistrictPriceMismatchedParam() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        ErrorDto responseDTO = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Error tipo: Cannot deserialize value of type `java.lang.Double` from String \"word\": not a valid `Double` value");

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);
        payloadJson = payloadJson.replaceAll("\"district_price\":1100.0\\b", "\"district_price\":\"word\"");
        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void calculateTestDistrictNotFound() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();
        payloadDTO.getDistrict().setDistrict_name("Brooklyn");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        ErrorDto responseDTO = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "District name Brooklyn not found in the database");

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);
        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void calculateTestDistrictPriceNotEquals() throws Exception {
        // arrange
        PropertyDTO payloadDTO = TestUtilsGenerator.getValidProperty();
        payloadDTO.getDistrict().setDistrict_price(987987.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        // mock
        ErrorDto responseDTO = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Validations Error in fields: {district.district_price=District price can't be higher than 4500.00 U$S}");

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);
        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
}


