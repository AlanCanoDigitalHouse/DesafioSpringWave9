package com.bootcamp.desafio2.integrations.controllers;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ErrorMessageDto;
import com.bootcamp.desafio2.utils.TestUtilGenerator;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("Test validar response propiedad")
    public void propertyResponse() throws Exception {
        PropertyDto property = TestUtilGenerator.getProperty("Campo");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Obtener respuesta completa")
    public void propertyData() throws Exception{
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate").contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalArea").value(995.5))
                .andExpect(jsonPath("$.propertyPrice").value(1095050D))
                .andExpect(jsonPath("$.biggerEnvironment").value("Villamiriam"));
    }
    @Test
    @DisplayName("Respuesta con propiedad nula")
    public void errorResponse() throws Exception{
        PropertyDto property = TestUtilGenerator.getProperty(null);
        ErrorMessageDto errorMessage = TestUtilGenerator.get400();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate").contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }
    @Test
    @DisplayName("Test validar propiedad")
    public void BadRequestResponse() throws Exception {
        PropertyDto property = TestUtilGenerator.getProperty("");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("Test validar distrito vacio")
    public void districtEmpty() throws Exception {
        PropertyDto property = TestUtilGenerator.getPropDistrictEmpty("Campo");
        ErrorMessageDto errorMessage = TestUtilGenerator.get400District();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }
    @Test
    @DisplayName("Test No existe distrito")
    public void inexistentDistrict() throws Exception {
        PropertyDto property = TestUtilGenerator.getPropWrongDistrict("Campo");
        ErrorMessageDto errorMessage = TestUtilGenerator.get400DistrictNotFound();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }
    @Test
    @DisplayName("Test No empieza con mayuscula")
    public void capitalizeName() throws Exception {
        PropertyDto property = TestUtilGenerator.getProperty("campo");
        ErrorMessageDto errorMessage = TestUtilGenerator.get400Capitalize();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }
    @Test
    @DisplayName("Test No mayor a 30 caracteres")
    public void sizeName() throws Exception {
        PropertyDto property = TestUtilGenerator.getProperty("CampoCampoCampoCampoCampoCampoCampo");
        ErrorMessageDto errorMessage = TestUtilGenerator.get400size();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }

    @Test
    @DisplayName("Test ambiente sin elementos")
    public void emptyEnvironment() throws Exception {
        PropertyDto property = TestUtilGenerator.getEmptyEnvironment("Campo");
        ErrorMessageDto errorMessage = TestUtilGenerator.get400EmptyEnvironment();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }
    @Test
    @DisplayName("Test length excedido")
    public void exceededWidth() throws Exception {
        PropertyDto property = TestUtilGenerator.getExceededLength("Campo");
        ErrorMessageDto errorMessage = TestUtilGenerator.get400Width();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(errorMessage.getStatus()))
                .andExpect(jsonPath("$.message").value(errorMessage.getMessage()))
                .andExpect(jsonPath("$.fields").value(errorMessage.getFields()));
    }
}
