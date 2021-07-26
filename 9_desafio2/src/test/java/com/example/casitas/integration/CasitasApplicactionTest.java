package com.example.casitas.integration;

import com.example.casitas.dtos.HouseDTO;
import com.example.casitas.utils.HouseTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class CasitasApplicactionTest {

    @Autowired
    private MockMvc mockMvc;

    HouseDTO house = HouseTestUtils.buildHouse();

    @Test
    void getSquareMeters() throws Exception{
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(house);

        this.mockMvc.perform(MockMvcRequestBuilders.post(("/house/squareMeters"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("La casita de Brian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.squareMeters").value(53.5))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getPrice() throws Exception{
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(house);

        this.mockMvc.perform(MockMvcRequestBuilders.post(("/house/price"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("La casita de Brian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(2675.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environment", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.district_name").value("Palermo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.district_price").value(50.0))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getBiggerEnvironment() throws Exception{
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(house);

        this.mockMvc.perform(MockMvcRequestBuilders.post(("/house/biggerEnv"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environment_name").value("Living"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environment_width").value(5.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environment_length").value(5.0))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void getSquareMetersEnvironment() throws Exception{
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(house);

        this.mockMvc.perform(MockMvcRequestBuilders.post(("/house/squareMetersEnvironment"))
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Cocina"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].squareMeters").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(600))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Living"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].squareMeters").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(1250))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Banio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].squareMeters").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].price").value(300))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("Habitacion"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].squareMeters").value(10.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].price").value(525))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
