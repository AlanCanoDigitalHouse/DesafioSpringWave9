package com.kjcelis.calculadora_mts.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;

import com.kjcelis.calculadora_mts.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Integration test - HouseController")
    public void calculeInfoPropIntgTst() throws Exception {

        HouseRequestDTO houseRequestDTO = TestUtilGenerator.getRequestDTO();
        HouseResponseDTO houseResponseDTO = TestUtilGenerator.getResponseDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJson = writer.writeValueAsString(houseRequestDTO);
        String responseJson = writer.writeValueAsString(houseResponseDTO);


        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }

}
