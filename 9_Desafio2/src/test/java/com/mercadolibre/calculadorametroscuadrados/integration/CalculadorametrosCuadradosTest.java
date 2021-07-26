package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.TestUtils;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Api - integration tests")
public class CalculadorametrosCuadradosTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("calculate response ")
    void calculateFromValidInput() throws Exception {

        HouseDTO payloadDTO = TestUtils.createValidInput();
        String payloadJSON = TestUtils.convertToJsonString(payloadDTO);
        ResponseDTO responseDTO = TestUtils.calculateValidResponse();
        String responseJson=TestUtils.convertToJsonString(responseDTO);

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseJson));
    }

    @Test
    @DisplayName("should throw DistrictNotFoundException")
    void  calculateFromAnInvalidDistrict() throws Exception {
        HouseDTO payloadDTO = TestUtils.createValidInput();
        payloadDTO.setDistrict_name("NotValidDistrict");
        String payloadJSON = TestUtils.convertToJsonString(payloadDTO);

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("District Not Found"))
                .andExpect(jsonPath("$.value").value(404));
    }

    @Test
    @DisplayName("should throw PriceNotValidException")
    void calculateFromInvalidPrice() throws Exception {
        HouseDTO payloadDTO = TestUtils.createInputWithInvalidPrice();
        payloadDTO.setDistrict_price(555d);
        String payloadJSON = TestUtils.convertToJsonString(payloadDTO);

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Price"))
                .andExpect(jsonPath("$.value").value(400));
    }

    @Test
    @DisplayName("should throw MethodNotAllowedException")
    void validationErrors() throws Exception {
        HouseDTO payloadDTO = TestUtils.createValidationErrorInput();
        String payloadJSON = TestUtils.convertToJsonString(payloadDTO);

        Map<String, String> result=TestUtils.createFieldsValidationError();

        mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields").value(result));
    }







}
