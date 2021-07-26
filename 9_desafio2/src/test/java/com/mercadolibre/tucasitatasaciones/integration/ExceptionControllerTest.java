package com.mercadolibre.tucasitatasaciones.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitatasaciones.dto.response.ErrorDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.ErrorWithFieldsDTO;
import com.mercadolibre.tucasitatasaciones.util.JSONDataUtil;
import com.mercadolibre.tucasitatasaciones.util.TestDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static final String DATA_DIR = "classpath:static/districts.json";

    @BeforeEach
    void initData() {
        JSONDataUtil.cleanData(DATA_DIR);
        TestDataUtil.initDummyData(DATA_DIR);
    }

    private ObjectWriter getObjectWriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }

    @Test
    void testResourceNotFoundException() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictZ");

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.BAD_REQUEST.value();
        var expectedBody = objectWriter
                .writeValueAsString(new ErrorDTO(expectedStatus, "Required district does not exist."));

        var mvcResult = this.mockMvc.perform(post("/property/valuation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

    @Test
    void testInvalidRequestDataException() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");
        propertyData.getDistrict().setPrice(999D);

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.BAD_REQUEST.value();
        var expectedBody = objectWriter
                .writeValueAsString(new ErrorDTO(expectedStatus, "Provided district data does not match the records"));

        var mvcResult = this.mockMvc.perform(post("/property/valuation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

    @Test
    void testHttpMessageNotReadableException() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");

        var requestBody = objectWriter.writeValueAsString(propertyData).substring(0, 15);

        var expectedStatus = HttpStatus.BAD_REQUEST.value();
        var expectedBody = objectWriter
                .writeValueAsString(new ErrorDTO(expectedStatus, "An error occurred while parsing your HTTP request"));

        var mvcResult = this.mockMvc.perform(post("/property/valuation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

    @Test
    void testMethodArgumentNotValidException() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");
        propertyData.setName("testProp");

        Map<String, String> expectedFields = new HashMap<>(){{
            put("name", "El nombre de la propiedad debe comenzar con mayÃºscula.");
        }};

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.BAD_REQUEST.value();
        var expectedBody = objectWriter
                .writeValueAsString(new ErrorWithFieldsDTO(expectedStatus, "Validation Error", expectedFields));

        var mvcResult = this.mockMvc.perform(post("/property/valuation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }
}
