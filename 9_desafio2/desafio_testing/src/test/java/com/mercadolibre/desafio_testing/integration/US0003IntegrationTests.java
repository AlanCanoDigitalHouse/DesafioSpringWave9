package com.mercadolibre.desafio_testing.integration;

import com.mercadolibre.desafio_testing.dto.request.DistrictDTO;
import com.mercadolibre.desafio_testing.dto.request.PropertyDTO;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import com.mercadolibre.desafio_testing.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class US0003IntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    private final DistrictDTO district = DistrictDTO.getDistrictDTO(
            ConstantsUtils.DISTRICT_JSONS_ROUTE +
                    "valid_district_creation.json");

    private final PropertyDTO property = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property_creation.json");

    private final DistrictDTO district2 = DistrictDTO.getDistrictDTO(
            ConstantsUtils.DISTRICT_JSONS_ROUTE +
                    "valid_district2.json");

    private final PropertyDTO property2 = PropertyDTO.getPropertyDTO(
            ConstantsUtils.PROPERTY_JSONS_ROUTE +
                    "valid_property2.json");

    @Test
    public void getBiggerRoomRespondsCorrectly() throws Exception {
        String districtJson = FileUtils.objectMapper.writeValueAsString(
                this.district);
        String propertyJson = FileUtils.objectMapper.writeValueAsString(
                this.property);

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                ConstantsUtils.DISTRICT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(districtJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value(ConstantsUtils.DISTRICT_CREATED));

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                ConstantsUtils.PROPERTY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(propertyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value(ConstantsUtils.PROPERTY_CREATED));

        this.mockMvc.perform( MockMvcRequestBuilders.get(
                ConstantsUtils.PROPERTY_ENDPOINT + "/Casa1" +
                ConstantsUtils.BIG_ROOM_ENDPOINT) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.big_room")
                .value("Ambiente1"));
    }

    @Test
    public void getBiggerRoomRespondsCorrectly2() throws Exception {
        String districtJson = FileUtils.objectMapper.writeValueAsString(
                this.district2);
        String propertyJson = FileUtils.objectMapper.writeValueAsString(
                this.property2);

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                ConstantsUtils.DISTRICT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(districtJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value(ConstantsUtils.DISTRICT_CREATED));

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                ConstantsUtils.PROPERTY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(propertyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response")
                        .value(ConstantsUtils.PROPERTY_CREATED));

        this.mockMvc.perform( MockMvcRequestBuilders.get(
                ConstantsUtils.PROPERTY_ENDPOINT + "/Casa2" +
                        ConstantsUtils.BIG_ROOM_ENDPOINT) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.big_room")
                        .value("Ambiente2"));
    }
}
