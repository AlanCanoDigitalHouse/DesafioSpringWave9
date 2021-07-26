package com.bootcamp.desafio2.integrations;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.utils.TestUtilsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        TestUtilsGenerator.init();
    }

    @Test
    @DisplayName("Integration Test calculate with default property Http 200")
    public void calculateIntegrationTestWithDefaultProperty() throws Exception{
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration Test calculate with default property Http 200 and success response")
    public void calculateIntegrationTestWithDefaultPropertyAndSuccessResponse() throws Exception{
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalArea").value(25.0))
                .andExpect(jsonPath("$.propertyPrice").value(27500.0));
    }

    @Test
    @DisplayName("Integration Test calculate with bad property")
    public void calculateIntegrationTestWithBadProperty() throws Exception{
        PropertyDto propertyDto = TestUtilsGenerator.getBadPropertyRequest();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("No existe un barrio que corresponda a los datos enviados"));
    }

    @Test
    @DisplayName("Integration Test calculate with property blank name")
    public void calculateIntegrationTestWithPropertyBlankName() throws Exception{
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        propertyDto.setProp_name("");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error"))
                .andExpect(jsonPath("$.fields.prop_name").value("El nombre de la propiedad no puede estar vacío"));
    }

    @Test
    @DisplayName("Integration Test calculate with property long name")
    public void calculateIntegrationTestWithLongName() throws Exception{
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        propertyDto.setProp_name("Esta es la casa de uno de los escritores mas famosos de latinoamerica");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error"))
                .andExpect(jsonPath("$.fields.prop_name").value("La longitud del nombre no puede superar los 30 caracteres"));
    }

    @Test
    @DisplayName("Integration Test calculate with various properties validations")
    public void calculateIntegrationTestWithPropertiesValidations() throws Exception{
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        propertyDto.setProp_name("Esta es la casa de uno de los escritores mas famosos de latinoamerica");
        propertyDto.setDistrict(null);
        propertyDto.setEnvironments(new ArrayList<>());

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error"))
                .andExpect(jsonPath("$.fields.environments").value("La casa debe tener al menos una habitacion"))
                .andExpect(jsonPath("$.fields.district").value("Debe enviar el barrio donde esta ubicada la casa"));
    }

    @Test
    @DisplayName("Integration Test calculate with null property Http 400 and success response")
    public void calculateIntegrationTestWithNullPropertyAndSuccessResponse() throws Exception{
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(null);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
