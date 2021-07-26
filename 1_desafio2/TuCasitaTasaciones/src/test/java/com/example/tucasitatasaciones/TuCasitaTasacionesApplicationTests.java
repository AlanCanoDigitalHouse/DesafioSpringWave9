package com.example.tucasitatasaciones;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TuCasitaTasacionesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculateTotalMetersOneRoom() throws Exception {
        String request = "{\"name\": \"Oficina\", \"rooms\": [" +
                getRoom("Espacio abierto", 3.0, 3.0) +
                "]," +
                "\"district\":" + getDistrict("Palermo",1000.0) + "}";
        this.mockMvc.perform(
                post("/home/meters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("9")));
    }

    @Test
    void calculateTotalMetersManyRoom() throws Exception {
        String request = "{\"name\": \"Oficina\", \"rooms\": [" +
                getRoom("Espacio abierto", 3.0, 3.0) + "," +
                getRoom("Cocina", 3.0, 3.0)  + "," +
                getRoom("Baño", 2.0, 1.0) +
                "]," +
                "\"district\":" + getDistrict("Palermo",1000.0) + "}";
        this.mockMvc.perform(
                post("/home/meters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("20")));
    }

    @Test
    void wrongBodyTest() throws Exception {
        String request = "{\"name\": \"\", \"rooms\": [" +
                getRoom("Espacio abierto", 3.0, 3.0) + "," +
                getRoom("Cocina", 3.0, 3.0)  + "," +
                getRoom("Baño", 2.0, 1.0) +
                "]," +
                "\"district\":" + getDistrict("Palermo",1000.0) + "}";
        this.mockMvc.perform(
                post("/home/meters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest());
    }



    @Test
    void calculateHousePrice() throws Exception {
        String request = "{\"name\": \"Oficina\", \"rooms\": [" +
                getRoom("Espacio abierto", 5.0, 5.0) + "," +
                getRoom("Cocina", 3.0, 3.0)  + "," +
                getRoom("Baño", 2.0, 1.0) +
                "]," +
                "\"district\":" + getDistrict("Palermo",1000.0) + "}";
        this.mockMvc.perform(
                post("/home/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("36")))
                .andExpect(jsonPath("$.price").value(36000));
    }

    @Test
    void calculateHousePriceDistrictNotFound() throws Exception {
        String request = "{\"name\": \"Oficina\", \"rooms\": [" +
                getRoom("Espacio abierto", 5.0, 5.0) + "," +
                getRoom("Cocina", 3.0, 3.0)  + "," +
                getRoom("Baño", 2.0, 1.0) +
                "]," +
                "\"district\":" + getDistrict("Palermo",100.0) + "}";
        this.mockMvc.perform(
                post("/home/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void calculateBiggestRoom() throws Exception {
        String biggestRoom = "Espacio abierto";
        String request = "{\"name\": \"Oficina\", \"rooms\": [" +
                getRoom(biggestRoom, 5.0, 5.0) + "," +
                getRoom("Cocina", 3.0, 3.0)  + "," +
                getRoom("Baño", 2.0, 1.0) +
                "]," +
                "\"district\":" + getDistrict("Palermo",1000.0) + "}";
        this.mockMvc.perform(
                post("/home/biggest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.room.name").value(biggestRoom));
    }

    @Test
    void calculateRoomsSquareFeet() throws Exception {
        String biggestRoom = "Espacio";
        String request = "{\"name\": \"Oficina\", \"rooms\": [" +
                getRoom(biggestRoom, 5.0, 5.0) + "," +
                getRoom("Cocina", 3.0, 3.0)  + "," +
                getRoom("Bano", 2.0, 1.0) +
                "]}";
        this.mockMvc.perform(
                post("/home/roommeters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomsMeters.Espacio").value(25))
                .andExpect(jsonPath("$.roomsMeters.Cocina").value(9))
                .andExpect(jsonPath("$.roomsMeters.Bano").value(2));
    }
    private String getRoom(String name, Double width, Double length) {
        return "{\"name\": \""+name+"\", \"width\": "+width+", \"length\": "+length+"}";
    }

    private String getDistrict(String name, Double price) {
        return "{\"name\": \""+name+"\",  \"price\": "+price+"}";
    }

}
