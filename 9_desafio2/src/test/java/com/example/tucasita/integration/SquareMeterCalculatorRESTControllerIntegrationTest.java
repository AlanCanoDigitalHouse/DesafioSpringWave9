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
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

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

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalSquareM").value(20))
                .andExpect(jsonPath("$.houseValue").value(6000))
                .andExpect(jsonPath("$.maxSizeRoomName").value("Room 2"));
    }

    @Test
    @DisplayName("House Name Null")
    void testHouseNameNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setName(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("House Name Empty")
    void testHouseNameEmpty() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setName("");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("House Name Too Long")
    void testHouseNameTooLong() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setName("Nombre re largo de mas de 30 caracteres");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("House Name LowerCase")
    void testHouseNameLowerCase() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setName("casita");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("District Name Null")
    void testDistrictNameNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setDistrictName(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("District Name Blank")
    void testDistrictNameBlank() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setDistrictName("");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("District Name Too Long")
    void testDistrictNameTooLong() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setDistrictName("Este es un nombre re largo de mas de 45 caracteres");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("District Price Null")
    void testDistrictPriceNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setDistrictPrice(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("District Price 0.001")
    void testDistrictPriceTooLow() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setDistrictPrice(0.001);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("District Price 4000.1")
    void testDistrictPriceTooHigh() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setDistrictPrice(4000.1);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Rooms Null")
    void testRoomsNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setRooms(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Rooms Empty")
    void testRoomsEmpty() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.setRooms(new ArrayList<>());

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Name Null")
    void testRoomNameNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setName(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Name Blank")
    void testRoomNameBlank() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setName("");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Name LowerCase")
    void testRoomNameLowerCase() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setName("room 0");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Name Too Long")
    void testRoomNameTooLong() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setName("Este es un nombre de habitación muy largo de mas de 30 caracteres");

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Width Null")
    void testRoomWidthNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setWidth(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Width 0.49")
    void testRoomWidthTooLow() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setWidth(0.49);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Width 25.1")
    void testRoomWidthTooHigh() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setWidth(25.1);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Length Null")
    void testRoomLengthNull() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setLength(null);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Length 0.49")
    void testRoomLengthTooLow() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setLength(0.49);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Room Length 33.1")
    void testRoomLengthTooHigh() throws Exception {
        //arrange
        HouseRequestDTO hrDTO = TestingUtils.getValidHouseWith3Rooms();
        hrDTO.getRooms().get(0).setLength(33.1);

        String payloadJSON = getAsJSON(hrDTO);

        //act & assert
        getResultActions(payloadJSON)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }


    private String getAsJSON(HouseRequestDTO hrDTO) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(hrDTO);
    }

    private ResultActions getResultActions(String payloadJSON) throws Exception {
        return mockMvc.perform(post("/calculateForHouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print());
    }
}
