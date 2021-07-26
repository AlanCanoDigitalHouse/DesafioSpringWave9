package com.example.desafio2.integration;

import com.example.desafio2.dtos.EnvDTO;
import com.example.desafio2.dtos.HouseDTO;
import com.example.desafio2.utils.TestGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.ArrayList;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseDTOValidationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getSquareMetersWithOutEnvs() throws  Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        houseDTO.setEnvironments(new ArrayList<>());
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMeters")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message",hasItem("La lista de ambientes debe tener al menos 1 ambiente")))
                .andReturn();
    }

    @Test
    void getSquareMetersWithOutName() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        houseDTO.setProp_name(null);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMeters")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message",hasItem("El nombre de la propiedad no debe estar vacía")))
                .andReturn();
    }

    @Test
    void getSquareMetersWithOutDistrict() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        houseDTO.setDistrict_name(null);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMeters")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message",hasItem("El nombre del barrio no puede estar  vacío")))
                .andReturn();
    }
    @Test
    void getSquareMetersWithOutMultipleFields() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        houseDTO.setDistrict_name(null);
        houseDTO.setDistrict_price(10000);
        houseDTO.setEnvironments(null);
        houseDTO.setProp_name(null);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMeters")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message",hasItem("El nombre del barrio no puede estar  vacío")))
                .andExpect(jsonPath("$.message",hasItem("La lista de ambientes no puede ser null")))
                .andExpect(jsonPath("$.message",hasItem("El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S")))
                .andExpect(jsonPath("$.message",hasItem("La lista de ambientes no puede ser null")))
                .andExpect(jsonPath("$.message",hasItem("La lista de ambientes debe tener al menos 1 ambiente")))
                .andReturn();
    }

    @Test
    void testingEnvironmentValidations() throws Exception{
        HouseDTO houseDTO = TestGenerator.generateHouse();
        EnvDTO env = houseDTO.getEnvironments().get(0);
        env.setEnvironment_name("baño");//testing lowerCase
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/house/getSquareMeters")
                .contentType(MediaType.APPLICATION_JSON).content(jsonPayload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message",hasItem("El nombre del ambiente debe comenzar en mayuscula")))
                .andReturn();
    }
}
