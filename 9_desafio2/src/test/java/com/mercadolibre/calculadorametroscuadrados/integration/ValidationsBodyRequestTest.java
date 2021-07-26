package com.mercadolibre.calculadorametroscuadrados.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationsBodyRequestTest {
    @Autowired
    private MockMvc mockMvc;

    private final String calculateEndPoint = "/calculate";

    @Test
    @DisplayName("Test error NotNull prop_name")
    public void validatePropNameValue() throws Exception {
        // Arrange
        String request = "{ \"district_name\": \"Palermo\", \"district_price\": 1000.0, " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "El nombre de la propiedad no puede estar vacío.";
        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.prop_name").value(expectedMessage));
    }

    @Test
    @DisplayName("Test error Pattern prop_name")
    public void validatePropNamePattern() throws Exception {
        // Arrange
        String request = "{\"prop_name\": \"test\", \"district_name\": \"Palermo\", \"district_price\": 1000.0, " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "El nombre de la propiedad debe comenzar con mayuscula.";

        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.prop_name").value(expectedMessage));
    }

    @Test
    @DisplayName("Test error Size prop_name")
    public void validatePropNameSize() throws Exception {
        // Arrange
        String request = "{\"prop_name\": \"Testtesttesttesttesttesttesttest\" ,\"district_name\": \"Palermo\", \"district_price\": 1000.0, " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "La longitud del nombre no puede superar los 30 caracteres.";
        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.prop_name").value(expectedMessage));
    }

    @Test
    @DisplayName("Test error NotNull district_name")
    public void validateDistrictNameNotNull() throws Exception {
        // Arrange
        String request = "{\"prop_name\": \"Test\" , \"district_price\": 1000.0, " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "El barrio no puede estar vacio.";
        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.district_name").value(expectedMessage));
    }

    @Test
    @DisplayName("Test error Size district_name")
    public void validateDistrictNameSize() throws Exception {
        String districName = "\"PalermoPalermoPalermoPalermoPalermoPalermoPalermoPalermoPalermoPalermoPalermo\"";
        // Arrange
        String request = "{\"prop_name\": \"Test\" ,\"district_name\": "+ districName +",\"district_price\": 1000.0, " +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "La longitud del barrio no puede superar los 45 caracteres.";
        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.district_name").value(expectedMessage));
    }

    @Test
    @DisplayName("Test error NotNull district_price")
    public void validateDistrictPriceNotNull() throws Exception {
        // Arrange
        String request = "{\"prop_name\": \"Test\" ,\"district_name\": \"Palermo\" ," +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "El precio de un barrio no puede estar vacio.";
        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.district_price").value(expectedMessage));
    }

    @Test
    @DisplayName("Test error DecimalMax district_price")
    public void validatePriceMax() throws Exception {
        // Arrange
        String request = "{\"prop_name\": \"Test\" ,\"district_name\": \"Palermo\" , \"district_price\" : 6000," +
                "\"rooms\": [{ \"enviroment_name\": \"Cocina\", \"enviroment_width\": 5.0, \"enviroment_length\": 5.0, \"squareFeet\": 25.0 }]}";
        String expectedMessage = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S.";
        // Act
        this.mockMvc.perform(post(this.calculateEndPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.district_price").value(expectedMessage));
    }


}
