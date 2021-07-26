package com.mercadolibre.desafiotesting.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.desafiotesting.dto.HouseRequestDto;
import com.mercadolibre.desafiotesting.dto.RoomDto;
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
class HouseIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test calculate house")
    void testCalculateHouse() throws Exception {

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
    void testCalculateHouseRooms() throws Exception {

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
    void testCalculateBiggestRoom() throws Exception {

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
    void testCalculateWrongPrice() throws Exception {

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
                .andExpect(jsonPath("$[0].description").value("El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S."));
    }

    @Test
    @DisplayName("Test calculate wrong district")
    void testCalculateWrongDistrict() throws Exception {

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

        Assertions.assertEquals("district not found", result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test calculate wrong name")
    void testCalculateWrongName() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);
        houseRequestDto.getHouseDto().setProp_name("casa");

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
                .andExpect(jsonPath("$[0].description").value("El nombre de la propiedad debe comenzar con mayúscula."));
    }

    @Test
    @DisplayName("Test calculate wrong name room")
    void testCalculateWrongNameRoom() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);
        RoomDto roomDto = houseRequestDto.getHouseDto().getRooms().get(0);
        roomDto.setEnvironment_name("patio");

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
                .andExpect(jsonPath("$[0].description").value("El nombre del ambiente debe comenzar con mayúscula."));
    }

    @Test
    @DisplayName("Test calculate wrong width")
    void testCalculateWrongWidth() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);
        RoomDto roomDto = houseRequestDto.getHouseDto().getRooms().get(0);
        roomDto.setEnvironment_width(26.0);

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
                .andExpect(jsonPath("$[0].description").value("El máximo ancho permitido por propiedad es de 25 mts."));
    }

    @Test
    @DisplayName("Test calculate wrong length")
    void testCalculateWronglength() throws Exception {

        HouseRequestDto houseRequestDto = HouseTestUtils.getRequestHouse(3000.0);
        RoomDto roomDto = houseRequestDto.getHouseDto().getRooms().get(0);
        roomDto.setEnvironment_length(36.0);

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
                .andExpect(jsonPath("$[0].description").value("El máximo largo permitido por propiedad es de 33 mts."));
    }

}
