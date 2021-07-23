package com.mercadolibre.desafiotesting.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.desafiotesting.dto.HouseRequestDto;
import com.mercadolibre.desafiotesting.utils.HouseTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test calculate house")
    public void testCalculateHouse() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(houseRequestDto);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.squareFeet").value(220.0))
                .andExpect(jsonPath("$.price").value(660000.0));
    }

    @Test
    @DisplayName("Test calculate house rooms")
    public void testCalculateHouseRooms() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(houseRequestDto);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.rooms[0].squareFeet").value(100.0))
                .andExpect(jsonPath("$.rooms[1].squareFeet").value(120.0));

    }

    @Test
    @DisplayName("Test calculate biggest room")
    public void testCalculateBiggestRoom() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(houseRequestDto);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.biggest.squareFeet").value(120.0));

    }

    @Test
    @DisplayName("Test calculate wrong price")
    public void testCalculateWrongPrice() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(5000.0);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(houseRequestDto);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.district_price").value("El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S."));
    }

    @Test
    @DisplayName("Test calculate wrong district")
    public void testCalculateWrongDistrict() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);
        houseRequestDto.getHouseDto().setDistrict_name("barrio null");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(houseRequestDto);

        MvcResult result = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertEquals("district not found",result.getResponse().getContentAsString());
    }

}
