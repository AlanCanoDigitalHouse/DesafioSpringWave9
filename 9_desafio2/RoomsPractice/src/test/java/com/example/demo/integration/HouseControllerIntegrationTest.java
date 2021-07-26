package com.example.demo.integration;

import com.example.demo.dtos.request.HouseRequestDto;
import com.example.demo.dtos.request.RoomRequestDto;
import com.example.demo.dtos.response.BiggestRoomResponseDto;
import com.example.demo.dtos.response.HouseSizeResponseDto;
import com.example.demo.dtos.response.HouseValueResponseDto;
import com.example.demo.dtos.response.RoomSizeResponseDto;
import com.example.demo.util.TestUtilGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test to validate house's size")
    public void testGetHouseSize() throws Exception {
            /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
            HouseRequestDto houseDto = TestUtilGenerator.createHouseWithThreeRooms();

            /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
            HouseSizeResponseDto responseDto = TestUtilGenerator.createValidHouseSizeResponse();

            ObjectWriter writer = new ObjectMapper()
                    .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                    .writer();

            String payloadJson = writer.writeValueAsString(houseDto);
            String responseJson = writer.writeValueAsString(responseDto);

            MvcResult  mvcResult =
                    this.mockMvc.perform(post("/house/size")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(payloadJson))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(content().contentType("application/json"))
                            .andReturn();

            Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test to validate house value")
    public void testGetHouseValue() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        HouseRequestDto houseDto = TestUtilGenerator.createHouseWithThreeRooms();

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        HouseValueResponseDto responseDto = TestUtilGenerator.createValidHouseValueResponse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(houseDto);
        String responseJson = writer.writeValueAsString(responseDto);

        MvcResult  mvcResult =
                this.mockMvc.perform(post("/house/value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test to validate biggest room")
    public void testGetBiggestHouse() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        HouseRequestDto houseDto = TestUtilGenerator.createHouseWithThreeRooms();

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        RoomRequestDto biggestRoom = TestUtilGenerator.createValidBiggestRoom();
        BiggestRoomResponseDto responseDto = new BiggestRoomResponseDto(biggestRoom);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(houseDto);
        String responseJson = writer.writeValueAsString(responseDto);

        MvcResult  mvcResult =
                this.mockMvc.perform(post("/house/biggest-room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test to validate area by room")
    public void testGetAreaByRoom() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        HouseRequestDto houseDto = TestUtilGenerator.createHouseWithThreeRooms();

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        HouseRequestDto house = TestUtilGenerator.createHouseWithThreeRooms();
        RoomSizeResponseDto responseDto = new RoomSizeResponseDto(house.getRooms());

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(houseDto);
        String responseJson = writer.writeValueAsString(responseDto);

        MvcResult  mvcResult =
                this.mockMvc.perform(post("/house/rooms-size")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }
}
