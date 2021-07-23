package com.bootcamp.desafio2.integration;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ErrorMessageDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectWriter writer;

    @BeforeEach @AfterEach
    void Init() {
        TestUtilsGenerator.initDataBase();
        writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
    }

    @Test
    void calculatePriceSuccessTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        TestUtilsGenerator.appendNewDistrict(new District("Boita", 3000D));

        String payloadJSON = writer.writeValueAsString(body);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.biggerEnvironment").value("Cocina"))
                .andExpect(jsonPath("$.propertyPrice").value(1350000D));
    }

    @Test
    void districtNotFoundTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        TestUtilsGenerator.appendNewDistrict(new District("Madrid", 3000D));

        String payloadJSON = writer.writeValueAsString(body);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("No existe un barrio que corresponda a los datos enviados"));
    }

    @Test
    void dataBaseErrorTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        TestUtilsGenerator.wrongDataBase();

        String payloadJSON = writer.writeValueAsString(body);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Hubo un error leyendo la base de datos"));
    }

    @Test
    void propertyWithoutNameTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.setProp_name(null);

        String payloadJSON = writer.writeValueAsString(body);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error"))
                .andExpect(jsonPath("$.fields.prop_name").value("El nombre de la propiedad no puede estar vacío"));
    }

    @Test
    void propertyWithEnvironmentWidthNegativeTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.getEnvironments().get(0).setEnvironment_width(-1D);

        String payloadJSON = writer.writeValueAsString(body);

        MvcResult mvcResult = this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessageDto response = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessageDto.class);
        assertTrue(response.getFields().containsValue("El ancho no puede ser menor a 0"));
    }

    @Test
    void propertyWithEnvironmentLengthOverMaxTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.getEnvironments().get(0).setEnvironment_length(40D);

        String payloadJSON = writer.writeValueAsString(body);

        MvcResult mvcResult = this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessageDto response = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessageDto.class);
        assertTrue(response.getFields().containsValue("El mÃ¡ximo largo permitido por propiedad es de 33mts"));
    }

    @Test
    void propertyWithEmptyEnvironmentsTest() throws Exception {
        PropertyDto body = TestUtilsGenerator.getDefaultProperty();
        body.setEnvironments(new ArrayList<>());

        String payloadJSON = writer.writeValueAsString(body);

        MvcResult mvcResult = this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessageDto response = mapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorMessageDto.class);
        assertTrue(response.getFields().containsValue("La casa debe tener almenos una habitacion"));
    }

}
