package com.example.tucasitatasaciones.integrations;

import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.units.utils.UnitTestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TuCasitaTasacionesApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculatePropertyNameFirstLetterLowercase() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.setProp_name("d");
        String payload = UnitTestBuilder.writeObjectToString(property);

        String expectedResponse = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"prop_name\":\"El nombre de la propiedad debe comenzar con mayÃºscula.\"}}";
        MvcResult mvcResult = this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        Assertions.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void calculatePropertyDistrictException() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getDistrict().setDistrict_name("Not Found");
        String payload = UnitTestBuilder.writeObjectToString(property);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("District Not Found"))
                .andExpect(jsonPath("$.message.DistrictException").value("The district Not Found is not in the database."));
    }

    @Test
    void calculatePropertyIsOk() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        PropertyResponseDTO expectedResponse = UnitTestBuilder.property1Response2Env();
        String payload = UnitTestBuilder.writeObjectToString(property);
        String expectedResponseJson = UnitTestBuilder.writeObjectToString(expectedResponse);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        Assertions.assertEquals(expectedResponseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void calculatePropertyEmptyEnviroments() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.setEnviroments(new ArrayList<>());

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payload = writer.writeValueAsString(property);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.*").value("La propiedad debe tener al menos 1 ambiente."));
    }

    @Test
    void calculateProperty0PricePerMts2() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getDistrict().setDistrict_price(0.0);
        String payload = UnitTestBuilder.writeObjectToString(property);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.*").value("El precio minimo permitido por metro cuadrado debe superar los 1U$S."));
    }

    @Test
    void calculatePropertyPriceUpon4000PerMts2() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getDistrict().setDistrict_price(4001.0);
        String payload = UnitTestBuilder.writeObjectToString(property);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.*").value("El precio máximo permitido por metro cuadrado no puede superar los 4000U$S."));
    }

    @Test
    void calculatePropertyLengthEnviromentUpon33mts() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getEnviroments().get(0).setEnviroment_length(34.0);
        String payload = UnitTestBuilder.writeObjectToString(property);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.*").value("El máximo largo permitido por propiedad es de 33 mts."));
    }

    @Test
    void calculatePropertyWidthEnviromentUpon25mts() throws Exception {
        PropertyDTO property = UnitTestBuilder.create1Property2Enviroments();
        property.getEnviroments().get(0).setEnviroment_width(26.0);
        String payload = UnitTestBuilder.writeObjectToString(property);

        this.mockMvc.perform(post("/property/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.*").value("El máximo ancho permitido por propiedad es de 25 mts."));
    }
}
