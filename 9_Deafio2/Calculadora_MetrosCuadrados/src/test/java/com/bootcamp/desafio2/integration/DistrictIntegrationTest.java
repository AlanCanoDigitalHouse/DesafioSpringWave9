package com.bootcamp.desafio2.integration;

import com.bootcamp.desafio2.dtos.request.DistrictDTO;
import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
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
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class DistrictIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Validación nombre del distrito vacio.")
    void emptyDistrictNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("", 1100D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El barrio no puede estar vacio."));
    }

    @Test
    @DisplayName("Validación distrito no existe en BD")
    void districtNotFoundTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouseIncorrectDistrict();

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
                .andExpect(jsonPath("$.error").value("El distrito no existe en la base."));


    }

    @Test
    @DisplayName("Validación precio del distrito ingresado distinto al precio en BD.")
    void priceNotMatchTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouseIncorrectPrice();

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
                .andExpect(jsonPath("$.error")
                        .value("El precio ingresado no coincide con el que se encuentra en la bd."));
    }

    @Test
    @DisplayName("Validación nombre del distrito - Supera 45 caracteres.")
    void lengthDistrictNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1100D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
         MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("La longitud del barrio no puede superar los 45 caracteres."));
    }

    @Test
    @DisplayName("Validación nombre del distrito - Igual a 45 caracteres.")
    void lengthDistrictNameBorderTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Test nombre distrito test nombre distrito abc", 1100D);
        request.setDistrict(district);

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
    @DisplayName("Validación precio del distrito - No puede estar vacio.")
    void emptyDistrictPrice() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Belgrano", 0);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("El precio ingresado no coincide con el que se encuentra en la bd.")).andReturn();

    }

    @Test
    @DisplayName("Validación precio del distrito - Supera los 4000.")
    void priceMore4000Test() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Belgrano", 4001D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400D))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S."));

    }

    @Test
    @DisplayName("Validación precio del distrito - Igual a 4000.")
    void priceBorder4000Test() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Puerto Madero", 4000D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Validación distrito - Distrito sin nombre")
    void nullDistrictNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO(null, 4000D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El barrio no puede estar vacio."));
    }


}
