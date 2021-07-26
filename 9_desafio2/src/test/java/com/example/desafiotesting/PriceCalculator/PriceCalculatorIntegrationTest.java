package com.example.desafiotesting.PriceCalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceCalculatorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculateAll() throws Exception {
        String request = "{\"prop_name\":\"Recoleta\",\"district\":{\"district_name\":\"Barracas\",\"district_price\":1000},\"environments\":[{\"environment_name\":\"Ba\u00f1o\",\"environment_width\":25,\"environment_length\":33},{\"environment_name\":\"Pieza\",\"environment_width\":25,\"environment_length\":33}]}";
        this.mockMvc.perform(
                post("/properties/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.propertySize").value(1650.0))
                .andExpect(jsonPath("$.propertyPrice").value(1650000.0))
                .andExpect(jsonPath("$.biggerEnvironment.environment").value("Baño"));
    }
}
