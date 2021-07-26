package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ErrorCalculatorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Calculate House With Name Error")
    void calculateHouseNameError() throws Exception {
        /* TODO: Object Request Payload */
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithOneEnvironments("house 1", "Palermo");

        /* TODO: Object Response Compare*/
        ErrorDTO responseDTO = new ErrorDTO(
                "MethodArgumentNotValidException",
                "El nombre de la propiedad debe comenzar con mayúscula."
        );

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                        .andReturn();

        Assertions.assertEquals(responseJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("Calculate House With Price District Error")
    void calculateDistrictPriceHighError() throws Exception {
        /* TODO: Object Request Payload */
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithOneEnvironments("House 2", "Palermo");
        payloadDTO.getDistrict().setPrice(5000D);
        /* TODO: Object Response Compare*/
        ErrorDTO responseDTO = new ErrorDTO(
                "MethodArgumentNotValidException",
                "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S."
        );

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(jsonPath("$.name").value(responseDTO.getName()))
                        .andReturn();

        Assertions.assertEquals(responseJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("Calculate House With Length District Error")
    void calculateEnvironmentLengthHighError() throws Exception {
        /* TODO: Object Request Payload */
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithOneEnvironments("House 3", "Palermo");
        payloadDTO.getEnvironments().get(0).setLength(50D);
        /* TODO: Object Response Compare*/
        ErrorDTO responseDTO = new ErrorDTO(
                "MethodArgumentNotValidException",
                "El máximo largo permitido por propiedad es de 33 mts."
        );

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(jsonPath("$.name").value(responseDTO.getName()))
                        .andReturn();

        Assertions.assertEquals(responseJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("Calculate House With District Not Found Error")
    void calculateDistrictNotFoundError() throws Exception {
        /* TODO: Object Request Payload */
        HouseRequestDTO payloadDTO = TestUtilsGenerator.getHouseWithOneEnvironments("House 3", "Error");

        /* TODO: Object Response Compare*/
        ErrorDTO responseDTO = new ErrorDTO(
                "DistrictNotFoundException",
                "The District with a Name Error is not registered."
        );

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculator/house")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJSON))
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(jsonPath("$.name").value(responseDTO.getName()))
                        .andReturn();

        Assertions.assertEquals(responseJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("Calculate House With Htto Error Message")
    void calculateHttpMessageError() throws Exception {
        /* TODO: Object Request Payload */
        String request = "{\"name\": \"Oficina\", \"district\": {\"name\": \"Palermo\", \"price\": \"precio\"}, " +
                "\"environments\": [ ]}";

        this.mockMvc.perform(post("/calculator/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("HttpMessageNotReadableException"));
    }
}
