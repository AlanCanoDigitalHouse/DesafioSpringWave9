package com.desafio.tucasitatasaciones.integration;

import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.exception.ErrorMessage;
import com.desafio.tucasitatasaciones.util.Request;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Controller Testing")
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Right Response DTO")
    void rightResponse() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        PropertyResponseDTO response = Request.getResponse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);
        String responseDTO = writer.writeValueAsString(response);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(responseDTO, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Error when district does not exist")
    void exceptionDistrict() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        String district = "alamos";
        request.setDistrict_name(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("District "+ district +" does not exists."));
    }

    @Test
    @DisplayName("Validates Property Name")
    void validationPropName() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.setProp_name(null);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

    @Test
    @DisplayName("Validates Distric Name")
    void validationDistrictName() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.setDistrict_name("aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa aaaaa");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

    @Test
    @DisplayName("Validates Distric Price")
    void validationDistrictPrice() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.setDistrict_price(4000.01);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

    @Test
    @DisplayName("Validates at least one enviroment.")
    void validationEnviroments() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.setEnvironments(null);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

    @Test
    @DisplayName("Validates Enviroment Name")
    void validationEnviromentName() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.getEnvironments().get(0).setEnvironment_name("cuarto");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

    @Test
    @DisplayName("Validates Enviroment Width")
    void validationEnviromentWidth() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.getEnvironments().get(0).setEnvironment_width(-1);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

    @Test
    @DisplayName("Validates Enviroment Length")
    void validationEnviromentLength() throws Exception{
        PropertyRequestDTO request = Request.getRequest();
        request.getEnvironments().get(0).setEnvironment_length(33.01);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadDTO = writer.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadDTO))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Validations Error"));
    }

}
