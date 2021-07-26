package com.mercadolibre.tucasitatasaciones.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitatasaciones.dto.response.*;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyController {

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
    void testPropertyTotalAreaEndpoint() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.OK.value();
        var expectedBody = objectWriter
                .writeValueAsString(new PropertyTotalAreaDTO(propertyData.getName(), 300.0));

        var mvcResult = this.mockMvc.perform(post("/property/totalArea")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

    @Test
    void testPropertyValuationEndpoint() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.OK.value();
        var expectedBody = objectWriter
                .writeValueAsString(new PropertyValuationDTO(propertyData.getName(), 30000.0));

        var mvcResult = this.mockMvc.perform(post("/property/valuation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

    @Test
    void testLargestEnvironmentEndpoint() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyWithDifferentEnvironments("DistrictA");

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.OK.value();
        var expectedBody = objectWriter
                .writeValueAsString(new LargestEnvironmentDTO(propertyData.getName(),
                        new EnvironmentAreaDTO("TestEnv3", 200D)));

        var mvcResult = this.mockMvc.perform(post("/property/environment/largest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

    @Test
    void testEnvironmentsAreaEndpoint() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");
        List<EnvironmentAreaDTO> resultData = new ArrayList<>(){{
            add(new EnvironmentAreaDTO("TestEnv", 100D));
            add(new EnvironmentAreaDTO("TestEnv2", 100D));
            add(new EnvironmentAreaDTO("TestEnv3", 100D));
        }};

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.OK.value();
        var expectedBody = objectWriter
                .writeValueAsString(new PropertyEnvironmentsAreaDTO(propertyData.getName(), resultData));

        var mvcResult = this.mockMvc.perform(post("/property/environment/area")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }
}
