package com.mercado_libre.bootcamp.desafio2.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.EnvironmentDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HouseCalculatorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET - /calculate/house - 200")
    void calculateHouse_ok() throws Exception {
        HouseRequestDTO houseRequest = getHouseRequestDTO();
        HouseResponseDTO houseResponse = getHouseResponseDTO();

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);
        String expectedJSON = getObjectWriter().writeValueAsString(houseResponse);

        MvcResult result = this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJSON, result.getResponse().getContentAsString());
    }

    private HouseResponseDTO getHouseResponseDTO() {
        List<EnvironmentDTO> environments = generate3EnvironmentsMock();

        return HouseResponseDTO.builder()
                .rooms(environments)
                .biggestRoom(environments.get(0))
                .squaresMeters(42.00)
                .valuePerSquaresMeters(88200.00)
                .build();
    }

    private HouseRequestDTO getHouseRequestDTO() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Little apartment");
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2100.00));
        houseRequest.setEnvironments(generate3EnvironmentsMock());
        return houseRequest;
    }

    private List<EnvironmentDTO> generate3EnvironmentsMock() {
        List<EnvironmentDTO> environments = new ArrayList<>();
        environments.add(new EnvironmentDTO("Living room", 5.0, 5.0));
        environments.add(new EnvironmentDTO("Dining room", 4.0, 2.0));
        environments.add(new EnvironmentDTO("Bedroom", 3.0, 3.0));
        return environments;
    }

    private ObjectWriter getObjectWriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }
}
