package com.mercado_libre.bootcamp.desafio2.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class InformationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET - /information/neighborhoods - 200")
    void getListOfNeighborhoods_ok() throws Exception {

        String expectedJsonResponse = getObjectWriter().writeValueAsString(generateNeighborhoodList());

        MvcResult result = this.mockMvc.perform(get("/information/neighborhoods"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJsonResponse, result.getResponse().getContentAsString());
    }

    private List<Neighborhood> generateNeighborhoodList() {
        List<Neighborhood> neighborhoods = new ArrayList<>();
        neighborhoods.add(new Neighborhood("Recoleta", 2200.00));
        neighborhoods.add(new Neighborhood("Caballito", 2000.00));
        neighborhoods.add(new Neighborhood("Palermo", 2100.00));
        return neighborhoods;
    }

    private ObjectWriter getObjectWriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }
}
