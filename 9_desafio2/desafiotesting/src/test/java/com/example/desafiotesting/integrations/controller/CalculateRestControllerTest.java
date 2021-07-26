package com.example.desafiotesting.integrations.controller;

import com.example.desafiotesting.dto.HouseDTO;
import com.example.desafiotesting.dto.RoomDTO;
import com.example.desafiotesting.service.CalculateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest

public class CalculateRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculateService calculateService;

    @Test
    public void testCalculate() throws Exception {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setProp_name("Casa");
        houseDTO.setDistrict_name("Palermo");

        List<RoomDTO> rooms = new ArrayList<>();

        rooms.add(new RoomDTO("Living", 5, 5));
        rooms.add(new RoomDTO("Comedor", 10, 10));
        houseDTO.setRooms(rooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void calculateBiggestRoom() throws Exception {
        String biggestRoom = "Espacio";
        String request = "{\"prop_name\": \"Oficina\", \"district_name\": \"Palermo\", \"rooms\": [" +
                getRoom(biggestRoom, 5, 5) + "," +
                getRoom("Cocina", 3, 3)  + "," +
                getRoom("Comedor", 2, 1) +
                "]}";
        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void calculateRoomsSquareFeet() throws Exception {
        String biggestRoom = "Espacio";
        String request = "{\"prop_name\": \"Oficina\", \"district_name\": \"Palermno\", \"rooms\": [" +
                getRoom(biggestRoom, 5, 5) + "," +
                getRoom("Cocina", 3, 3)  + "," +
                getRoom("Comedor", 2, 1) +
                "]}";
        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }


   private String getRoom(String name, int width, int length) {
        return "{\"environment_name\": \""+name+"\", \"environment_width\": "+width+", \"environment_length\": "+length+"}"; }


}

