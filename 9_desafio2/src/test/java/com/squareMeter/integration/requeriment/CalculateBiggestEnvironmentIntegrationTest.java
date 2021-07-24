package com.squareMeter.integration.requeriment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.EnvironmentResponseDTO;
import com.squareMeter.exception.model.ErrorAttributes;
import com.squareMeter.exception.model.ErrorMessage;
import com.squareMeter.service.PropertyService;
import com.squareMeter.testUtils.creators.Property;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
0003: Calculate the biggest environment of a property

This test include:
- The validation of the input data is correct (@Valid, de serializate data)
- Validation of the output data (serialize data)
- Validation of the error handling
_ Correct values returned by the service
*/

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculateBiggestEnvironmentIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PropertyService propertyService;


    /*****   Data input validation and http codes ***********/

    @Test
    @DisplayName("A Input 1: Valid input US-0003")
    public void validInput0003() throws Exception {
        EnvironmentResponseDTO expected = EnvironmentResponseDTO.builder().environment_name("Bathroom").environment_width(20.0).environment_length(20.0).build();
        PropertyRequestDTO data = Property.getValidProperty();
        data.setEnvironments(Property.getEnvironmentsBiggerIsBathroom());
        String result = mockMvc.perform(post("/calculator/biggerEnvironment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("A Input 2: Invalid input generic US-0003")
    public void invalidInput0003() throws Exception {
        mockMvc.perform(post("/calculator/biggerEnvironment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Property.getInvalidProperty())))
                .andExpect(status().isBadRequest());
    }

    /************** Exception handling *****************/

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    @DisplayName("C Exception 1: Missing attributes US-0003")
    public void atributteException0003() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("environments[0].environment_name", "El nombre del ambiente no puede estar vacÃ\u00ADo.");
        //noinspection SpellCheckingInspection
        expected.addFieldError("environments[1].environment_width", "El mÃ¡ximo ancho permitido por propiedad es de 25 mts");
        expected.addFieldError("environments[0].environment_width", "El mÃ¡ximo ancho permitido por propiedad es de 25 mts");

        //Test data
        PropertyRequestDTO dataTest = Property.getNullNames();
        String result = mockMvc.perform(post("/calculator/biggerEnvironment", "Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Property.getInvalidProperty())))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("C Exception 2: Not capital letters US-0003")
    public void notCapitalLetterName0003() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("prop_name", "El nombre de la propiedad debe comenzar con mayÃºscula.");
        expected.addFieldError("environments[0].environment_name", "El nombre del ambiente debe comenzar con mayÃºscula.");
        //Test data
        PropertyRequestDTO dataTest = Property.getValidProperty();
        dataTest.setProp_name("not in capital");
        dataTest.getEnvironments().get(0).setEnvironment_name("not capital");
        String result = mockMvc.perform(post("/calculator/biggerEnvironment", "Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dataTest)))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("C Exception 3: Bad body sent US-0003")
    public void badBodyException0003() throws Exception {
        //ExpectedError
        ErrorMessage expected = new ErrorMessage("Error in the body", "Probably the body have a unexpected symbol");
        //Bad json
        String req = "{\"prop_name\":\"a name\",}";
        String result = mockMvc.perform(post("/calculator/biggerEnvironment", "Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("C Exception 4: Bad sizes attributes US-0003")
    public void badSizes0003() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("environments[0].environment_length", "El mÃ¡ximo largo permitido por propiedad es de 33 mts.");
        expected.addFieldError("environments[0].environment_width", "El mÃ¡ximo ancho permitido por propiedad es de 25 mts");

        //Bad json
        String result = mockMvc.perform(post("/calculator/biggerEnvironment", "Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Property.getBadSizes())))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("C Exception 4.1: Bad sizes (negative) attributes US-0003")
    public void badSizesNegative0003() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("district.district_price", "Se intento ingresar un valor negativo");
        PropertyRequestDTO propertyRequestDTO = Property.getValidProperty();
        propertyRequestDTO.getDistrict().setDistrict_price(-100.0);
        //Bad json
        String result = mockMvc.perform(post("/calculator/biggerEnvironment", "Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propertyRequestDTO)))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("C Exception 5: Empty environment list US-0003")
    public void emptyEnvironments0003() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("environments", "Property must have at least one environment");
        PropertyRequestDTO propertyRequestDTO = Property.getValidProperty();
        propertyRequestDTO.setEnvironments(new ArrayList<>());
        //Bad json
        String result = mockMvc.perform(post("/calculator/biggerEnvironment", "Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propertyRequestDTO)))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
}
