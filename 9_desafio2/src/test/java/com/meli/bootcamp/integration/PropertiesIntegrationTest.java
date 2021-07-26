package com.meli.bootcamp.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.bootcamp.dto.ExceptionDTO;
import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import com.meli.bootcamp.unit.utilsTest.TestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
public class PropertiesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectWriter JSONwriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }

    @Test
    @DisplayName("Valuation Property - Integration")
    public void PropertyValueTest() throws Exception {

        PropertyDTO requestProperty = TestGenerator.PropertyDTOTest();
        ValuationDTO expectedValuation = TestGenerator.valuationDTO();

        String requestJSON = JSONwriter().writeValueAsString(requestProperty);
        String expectedJSON = JSONwriter().writeValueAsString(expectedValuation);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/valuation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assertions.assertEquals(expectedJSON, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Invalid District (Exception) - Integration")
    public void InvalidDistrictTest() throws Exception {
        PropertyDTO requestProperty = TestGenerator.PropertyDTOTest();
        requestProperty.getDistrict().setDistrict_name("Devoto");
        ExceptionDTO expectedValuation =
                ExceptionDTO.builder()
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .message(DistrictException.DISTRICT_NOTFOUND)
                        .details(null).build();

        String requestJSON = JSONwriter().writeValueAsString(requestProperty);
        String expectedJSON = JSONwriter().writeValueAsString(expectedValuation);

        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/valuation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Validate DTO arguments (Exception) - Integration")
    public void DTOArgumentTest() throws Exception {
        PropertyDTO requestProperty = TestGenerator.PropertyDTOTest();
        requestProperty.setProp_name("invalid");
        Map<String, String> error = new HashMap<>();
        error.put("prop_name", "El nombre de la propiedad debe comenzar con mayúscula");
        ExceptionDTO expectedValuation =
                ExceptionDTO.builder()
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .message("El nombre de la propiedad debe comenzar con mayúscula")
                        .details(error).build();

        String requestJSON = JSONwriter().writeValueAsString(requestProperty);
        String expectedJSON = JSONwriter().writeValueAsString(expectedValuation);

        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/valuation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("Validate DTO type (Exception) - Integration")
    public void DTOTypeTest() throws Exception {
        PropertyDTO requestProperty = TestGenerator.PropertyDTOTest();
        Map<String, String> error = new HashMap<>();
        error.put("HttpMessageNotReadableException", "El payload contiene un tipo de datos no permitido");
        ExceptionDTO expectedValuation =
                ExceptionDTO.builder()
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .message("El payload contiene un tipo de datos no permitido")
                        .details(error).build();

        String requestJSON = JSONwriter().writeValueAsString(requestProperty);
        requestJSON = requestJSON.replaceAll("\"district_price\":1555.0\\b", "\"district_price\":\"hundreds\"");
        String expectedJSON = JSONwriter().writeValueAsString(expectedValuation);

        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/valuation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
}
