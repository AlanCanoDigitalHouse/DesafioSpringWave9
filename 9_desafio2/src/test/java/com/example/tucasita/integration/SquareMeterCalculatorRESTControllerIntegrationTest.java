package com.example.tucasita.integration;

import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.TestingUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SquareMeterCalculatorRESTControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Request for valid house")
    void testValidHouseRequest() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJSON = writer.writeValueAsString(hrDTO);

        //act & assert
        mockMvc.perform(post("/calculateForHouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalSquareM").value(20))
                .andExpect(jsonPath("$.houseValue").value(6000))
                .andExpect(jsonPath("$.maxSizeRoomName").value("Room 2"));
    }
}
