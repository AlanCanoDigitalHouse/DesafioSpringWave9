package com.mercado_libre.bootcamp.desafio2.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercado_libre.bootcamp.desafio2.TestUtilsGenerator;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.NeighborhoodRequestDTO;
import com.mercado_libre.bootcamp.desafio2.services.house.implementation.HouseCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseCalculatorIntegrationTest {

    @Autowired
    HouseCalculatorService service;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setUp() {
        TestUtilsGenerator.emptyDistrictsFile();
        NeighborhoodRequestDTO neighborhood = new NeighborhoodRequestDTO("Caseros", 40D);
        TestUtilsGenerator.appendNewNeighborhood(neighborhood);
    }

    @Test
    public void calculateHouseAnalyticsGets200() throws Exception {
        HouseRequestDTO request = TestUtilsGenerator.getRequestWithNeighborhood(new NeighborhoodRequestDTO("Caseros", 40D));

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(request);

        String expectedResponse = writer.writeValueAsString(service.calculate(request));

        MvcResult response = mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(expectedResponse, response.getResponse().getContentAsString());

    }

    @Test
    public void calculateHouseAnalyticsWithUnexistingNeighborhoodGets404() throws Exception {
        HouseRequestDTO request = TestUtilsGenerator.getRequestWithNeighborhood(new NeighborhoodRequestDTO("Haedo", 40D));

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(request);

        mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Debe existir el barrio para la asignación de precio"));


    }

    @Test
    public void calculateHouseAnalyticsWithInvalidNeighborhoodGets400() throws Exception {
        HouseRequestDTO request = TestUtilsGenerator.getRequestWithNeighborhood(new NeighborhoodRequestDTO("Caseros", 100D));
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(request);

        mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("El precio del districto no es correcto"));


    }

}
