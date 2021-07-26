package com.bootcamp.desafio2.integration;

import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PropertyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Validación propiedad - Largo de nombre - Supera 30 caracteres.")
    void lengthPropertyNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        request.setProp_name("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error"))
                .andExpect(jsonPath("$.message.prop_name")
                        .value("La longitud del nombre no puede superar los 30 caracteres."));
    }

    @Test
    @DisplayName("Validación propiedad - Largo de nombre del nombre - Igual a 30 caracteres.")
    void lengthPropertyNameBorderTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        request.setProp_name("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Validación propiedad - Inicio de nombre con maysucula.")
    void capitalPropertyNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        request.setProp_name("testName");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error"))
                .andExpect(jsonPath("$.message.prop_name")
                        .value("El nombre de la propiedad debe comenzar con mayúscula."));
    }

    @Test
    @DisplayName("Validación propiedad - Datos obtenidos")
    void housePriceAndAreaTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalArea").value(61D))
                .andExpect(jsonPath("$.housePrice").value(67100D));

    }

}
