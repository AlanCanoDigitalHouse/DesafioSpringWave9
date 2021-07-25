package com.mercadolibre.desafio_testing.integration.edge_case;

import com.mercadolibre.desafio_testing.dto.request.DistrictDTO;
import com.mercadolibre.desafio_testing.dto.request.PropertyDTO;
import com.mercadolibre.desafio_testing.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class HouseValidationIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    private final DistrictDTO district = DistrictDTO.getDistrictDTO(
            ConstantsUtils.DISTRICT_JSONS_ROUTE +
                    "valid_district_creation.json");

    private final PropertyDTO property = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property_creation.json");

    private void checkErrorMessage(String endpoint, String route,
                                   String message) throws Exception {
        String json;

        if (endpoint.equals(ConstantsUtils.DISTRICT_ENDPOINT)) {
            json = FileUtils.objectMapper.writeValueAsString(
                    DistrictDTO.getDistrictDTO(route));
        } else {
            json = FileUtils.objectMapper.writeValueAsString(
                    PropertyDTO.getPropertyDTO(route));
        }

        this.mockMVC.perform(MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.response")
                        .value(message));
    }

    @Test
    public void aPropertyCanBeCreated() throws Exception {
        String propertyJson = FileUtils.objectMapper.writeValueAsString(
                this.property);

        String districtJson = FileUtils.objectMapper.writeValueAsString(
                this.district);

        this.mockMVC.perform(MockMvcRequestBuilders.post(
                ConstantsUtils.DISTRICT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(districtJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value(ConstantsUtils.DISTRICT_CREATED));

        this.mockMVC.perform(MockMvcRequestBuilders.post(
                ConstantsUtils.PROPERTY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(propertyJson))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value(ConstantsUtils.PROPERTY_CREATED));
    }

    @Test
    public void propertyWithVoidNameGivesCorrectMessage() throws Exception {
        checkErrorMessage(ConstantsUtils.PROPERTY_ENDPOINT,
                ConstantsUtils.PROPERTY_JSONS_ROUTE +
                        "null_property_name.json",
                ConstantsUtils.INVALID_PROPERTY_NAME);
    }

    @Test
    public void propertyWithVoidNameGivesCorrectMessage2() throws Exception {
        checkErrorMessage(ConstantsUtils.PROPERTY_ENDPOINT,
                ConstantsUtils.PROPERTY_JSONS_ROUTE +
                        "void_property_name.json",
                ConstantsUtils.INVALID_PROPERTY_NAME);
    }

    @Test
    public void propertyWithNonCapitalizedNameGivesCorrectMessage()
            throws Exception {
        checkErrorMessage(ConstantsUtils.PROPERTY_ENDPOINT,
                ConstantsUtils.PROPERTY_JSONS_ROUTE +
                        "/uncap_property_name.json",
                ConstantsUtils.INVALID_PROPERTY_CAPITALIZATION);
    }

    @Test
    public void propertyWithNameToLongGivesCorrectMessage()
            throws Exception {
        checkErrorMessage(ConstantsUtils.PROPERTY_ENDPOINT,
                ConstantsUtils.PROPERTY_JSONS_ROUTE +
                        "long_property_name.json",
                ConstantsUtils.PROPERTY_NAME_TOO_LONG);
    }

    @Test
    public void districtWithPriceToBigGivesCorrectMessage()
            throws Exception {
        checkErrorMessage(ConstantsUtils.DISTRICT_ENDPOINT,
                ConstantsUtils.DISTRICT_JSONS_ROUTE +
                        "price_too_big.json",
                ConstantsUtils.PRICE_TOO_BIG);
    }

    @Test
    public void properyWithEnvNameToBigGivesCorrectMessage()
            throws Exception {
        checkErrorMessage(ConstantsUtils.PROPERTY_ENDPOINT,
                ConstantsUtils.ROOMS_JSONS_ROUTE +
                        "env_name_too_long.json",
                ConstantsUtils.ENVIRONMENT_NAME_TOO_LONG);
    }

    @Test
    public void properyWithEnvLengthToBigGivesCorrectMessage()
            throws Exception {
        checkErrorMessage(ConstantsUtils.PROPERTY_ENDPOINT,
                ConstantsUtils.ROOMS_JSONS_ROUTE +
                        "env_length_too_big.json",
                ConstantsUtils.ENVIRONMENT_LENGTH_TOO_BIG);
    }
}
