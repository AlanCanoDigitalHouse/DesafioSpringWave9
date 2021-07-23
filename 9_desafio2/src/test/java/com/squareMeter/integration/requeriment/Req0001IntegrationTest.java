package com.squareMeter.integration.requeriment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareMeter.controller.CalculatorController;
import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.model.ErrorAttributes;
import com.squareMeter.exception.model.ErrorMessage;
import com.squareMeter.service.PropertyService;
import com.squareMeter.testUtils.creators.Property;
import com.squareMeter.utils.Mapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
0001: Calculate the total of square meters of a property

This test include:
- The validation of the input data is correct (@Valid, de serializate data)
- Validation of the output data (serialize data)
- Validation of the error handling
_ Correct values returned by the service
*/

//Only for charge this controller and not all the beans of spring
@SuppressWarnings("ALL")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Req0001IntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PropertyService propertyService;


    /*****   Data input validation and http codes ***********/


    @Test
    @DisplayName("A Input 1: Valid input US-0001")
    public void validInput0001() throws Exception {
        PropertySquareMetersResponseDTO expected = new PropertySquareMetersResponseDTO(300);
        PropertyRequestDTO data = Property.getValidProperty();
        data.setEnvironments(Property.getEnvironmentsSum300SquareMeters());
        String result = mockMvc.perform(post("/calculator/squareMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
    @Test
    @DisplayName("A Input 2: Invalid input generic US-0001")
    public void invalidInput0001() throws Exception {
        mockMvc.perform(post("/calculator/squareMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Property.getInvalidProperty())))
                .andExpect(status().isBadRequest());
    }

/***********+ Data output *************/

    @Test
    @DisplayName("B Output 1: Valid output US-0001")
    public void validOutput0001() throws Exception {
        PropertySquareMetersResponseDTO expected = new PropertySquareMetersResponseDTO(300);
        PropertyRequestDTO data = Property.getValidProperty();
        data.setEnvironments(Property.getEnvironmentsSum300SquareMeters());
        String result = mockMvc.perform(post("/calculator/squareMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data)))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

/************** Exception handling *****************/

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    @DisplayName("C Exception 1: Missing attributes US-0001")
    public void atributteException0001() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("environments[0].environment_name","El nombre del ambiente no puede estar vacÃ\u00ADo.");
        //noinspection SpellCheckingInspection
        expected.addFieldError("environments[1].environment_width","El mÃ¡ximo ancho permitido por propiedad es de 25 mts");
        expected.addFieldError("environments[0].environment_width","El mÃ¡ximo ancho permitido por propiedad es de 25 mts");

        //Test data
        PropertyRequestDTO dataTest = Property.getNullNames();
        String result = mockMvc.perform(post("/calculator/squareMeters","Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Property.getInvalidProperty())))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
    @Test
    @DisplayName("C Exception 2: Not capital letters US-0001")
    public void notCapitalLetterName0001() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("prop_name","El nombre de la propiedad debe comenzar con mayÃºscula.");
        expected.addFieldError("environments[0].environment_name","El nombre del ambiente debe comenzar con mayÃºscula.");
        //Test data
        PropertyRequestDTO dataTest = Property.getValidProperty();
        dataTest.setProp_name("not in capital");
        dataTest.getEnvironments().get(0).setEnvironment_name("not capital");
        String result = mockMvc.perform(post("/calculator/squareMeters","Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dataTest)))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
    @Test
    @DisplayName("C Exception 3: Bad body sent US-0001")
    public void badBodyException0001() throws Exception {
        //ExpectedError
        ErrorMessage expected = new ErrorMessage("Error in the body","Probably the body have a unexpected symbol");
        //Bad json
        String req = "{\"prop_name\":\"a name\",}";
        String result = mockMvc.perform(post("/calculator/squareMeters","Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("C Exception 4: Bad sizes attributes US-0001")
    public void badSizes0001() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("environments[0].environment_length","El mÃ¡ximo largo permitido por propiedad es de 33 mts.");
        expected.addFieldError( "environments[0].environment_width", "El mÃ¡ximo ancho permitido por propiedad es de 25 mts");

        //Bad json
        String result = mockMvc.perform(post("/calculator/squareMeters","Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Property.getBadSizes())))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
    @Test
    @DisplayName("C Exception 4.1: Bad sizes (negative) attributes US-0001")
    public void badSizesNegative0001() throws Exception {
        //ExpectedError
        ErrorAttributes expected = new ErrorAttributes("Error with a few attributes");
        expected.addFieldError("district.district_price","Se intento ingresar un valor negativo");
        PropertyRequestDTO propertyRequestDTO = Property.getValidProperty();
        propertyRequestDTO.getDistrict().setDistrict_price(-100.0);
        //Bad json
        String result = mockMvc.perform(post("/calculator/squareMeters","Name of environment is needed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propertyRequestDTO)))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
}
