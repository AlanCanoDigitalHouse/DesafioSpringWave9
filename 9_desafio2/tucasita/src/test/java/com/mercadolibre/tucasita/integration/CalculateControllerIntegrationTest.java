package com.mercadolibre.tucasita.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasita.domain.House;
import com.mercadolibre.tucasita.dto.RoomDTO;
import com.mercadolibre.tucasita.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test Validar Post totalSquareMeters")
    public void testTotalSquareMetersPostMethod() throws Exception {

        House payloadDTO = TestDataGenerator.getValidHouse();
        Double expectedValue = Double.valueOf(413.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(expectedValue);

        MvcResult  mvcResult = this.mockMvc.perform(post("/house/totalSquareMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(responseJSON, mvcResult.getResponse().getContentAsString());

    }


    @Test
    @DisplayName("Test Validar Post price")
    public void testPricePostMethod() throws Exception {

        House payloadDTO = TestDataGenerator.getValidHouse();
        Double expectedValue = Double.valueOf(826000.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(expectedValue);

        MvcResult  mvcResult = this.mockMvc.perform(post("/house/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(responseJSON, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test validar Post price con Barrio invalido")
    public void testPricePostMethodWithInvalidDistrictInput() throws Exception{

        House payloadDTO = TestDataGenerator.getInvalidHouseByDistrictName();
        ResponseStatusException expectedException = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid district input.");
        String expectedValue = expectedException.getMessage();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        MvcResult  mvcResult = this.mockMvc.perform(post("/house/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();

        assertEquals(expectedValue, mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("Test validar Post biggestRoom")
    public void testBiggestRoomPostMethod() throws Exception{

        House payloadDTO = TestDataGenerator.getValidHouse();
        RoomDTO expectedValue = TestDataGenerator.getBiggestRoomOfValidHouse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(expectedValue);

        MvcResult  mvcResult = this.mockMvc.perform(post("/house/biggestRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(responseJSON, mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("Test Validar Post roomSizes")
    public void testRoomSizesPostMethod() throws Exception {

        House payloadDTO = TestDataGenerator.getValidHouse();
        List<RoomDTO> expectedValue = TestDataGenerator.getRoomSizeList();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(expectedValue);

        MvcResult  mvcResult = this.mockMvc.perform(post("/house/roomSizes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(responseJSON, mvcResult.getResponse().getContentAsString());
    }


}
