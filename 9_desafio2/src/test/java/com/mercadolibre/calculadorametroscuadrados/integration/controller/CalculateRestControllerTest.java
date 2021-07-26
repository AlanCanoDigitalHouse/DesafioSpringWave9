package com.mercadolibre.calculadorametroscuadrados.integration.controller;

import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateRestControllerTest {

    String POST_OF_TOTAL_SQUARE_METERS = "/property/calculateTotalSquareMeters";
    String POST_OF_TOTAL_VALUE_METERS = "/property/calculatePropertyValue";
    String POST_OF_TOTAL_BIGGER_ENVIRONMENT = "/property/calculateBiggerEnvironment";
    String POST_OF_TOTAL_SQUARE_ENVIRONMENTS = "/property/calculateTotalSquareMetersEnvironments";

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

    }

    // a) Se pide retornar un objeto de tipo Json que diga la cantidad totales de metros cuadrados de la casa Caso Feliz.
    @Test
    void calculateSquareMetersPropertyType() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("Environment1", 6, 2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";
        String response = "{\"nameProperty\":\"Test\",\"district\":{\"district_name\":\"Belgrano\",\"district_price\":1100.0},\"squareFeet\":25.0}";

        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    // a) Se pide retornar la cantidad correcta de metros cuadrados de la propiedad
    @Test
    void calculateSquareMetersPropertyTrue() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        String request2 = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 6, 2) + "," +
                getEnvironments("environment3", 6, 2) +
                "]}";
        String request3 = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 4, 8) + "," +
                getEnvironments("environment2", 4, 2) + "," +
                getEnvironments("environment3", 4, 4) +
                "]}";


        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value(25.0));

        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value(36.0));

        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value(56.0));
    }

    //Valor de la una propiedad
    @Test
    void calculateValueMetersPropertyType() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        String response = "{\"nameProperty\":\"Test\",\"district\":{\"district_name\":\"Belgrano\",\"district_price\":1100.0},\"priceProperty\":27500.0}";

        this.mockMvc.perform(
                post(POST_OF_TOTAL_VALUE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));

    }

    @Test
    void calculateValueMetersPropertyTrue() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        String request2 = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 6, 2) + "," +
                getEnvironments("environment3", 6, 2) +
                "]}";
        String request3 = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 4, 8) + "," +
                getEnvironments("environment2", 4, 2) + "," +
                getEnvironments("environment3", 4, 4) +
                "]}";


        this.mockMvc.perform(
                post(POST_OF_TOTAL_VALUE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceProperty").value(27500.0));

        this.mockMvc.perform(
                post(POST_OF_TOTAL_VALUE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceProperty").value(39600.0));

        this.mockMvc.perform(
                post(POST_OF_TOTAL_VALUE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceProperty").value(61600.0));
    }

    //Determinar cual es el ambiente más grande
    @Test
    void calculateBiggerMetersPropertyType() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        String response = "{\"nameProperty\":\"Test\",\"district\":{\"district_name\":\"Belgrano\",\"district_price\":1100.0},\"bigger\":{\"enviroment_name\":\"environment1\",\"enviroment_width\":6.0,\"enviroment_length\":2.0,\"squareFeet\":12.0}}";

        this.mockMvc.perform(
                post(POST_OF_TOTAL_BIGGER_ENVIRONMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    void calculateBiggerMetersPropertyTrue() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        String request2 = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 9, 2) + "," +
                getEnvironments("environment3", 6, 2) +
                "]}";
        String request3 = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 4, 8) + "," +
                getEnvironments("environment2", 4, 2) + "," +
                getEnvironments("environment3", 4, 4) +
                "]}";


        this.mockMvc.perform(
                post(POST_OF_TOTAL_BIGGER_ENVIRONMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bigger.squareFeet").value(12d));

        this.mockMvc.perform(
                post(POST_OF_TOTAL_BIGGER_ENVIRONMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bigger.squareFeet").value(18d));

        this.mockMvc.perform(
                post(POST_OF_TOTAL_BIGGER_ENVIRONMENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bigger.squareFeet").value(32d));
    }

    // Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.
    @Test
    void calculateSquareMetersEnvironmentType() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        String response = "{\"nameProperty\":\"Test\",\"district\":{\"district_name\":\"Belgrano\",\"district_price\":1100.0},\"environment\":[{\"environment\":{\"enviroment_name\":\"environment1\",\"enviroment_width\":6.0,\"enviroment_length\":2.0,\"squareFeet\":12.0}},{\"environment\":{\"enviroment_name\":\"environment2\",\"enviroment_width\":3.0,\"enviroment_length\":3.0,\"squareFeet\":9.0}},{\"environment\":{\"enviroment_name\":\"environment3\",\"enviroment_width\":2.0,\"enviroment_length\":2.0,\"squareFeet\":4.0}}]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_ENVIRONMENTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    //////////////////////////////////////////////////////
    //Validación de Propiedad
    @Test
    @DisplayName("Valida que el nombre no se nulo ")
    void nameNotBlankException() throws Exception {
        String request = "{\"district\":" +
                getDistrict("elgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El nombre de la propiedad no puede estar vacío."));
    }

    @Test
    @DisplayName("Valida que comience con mayúscula")
    void namePatternException() throws Exception {
        String request = "{\"prop_name\": \"est\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El nombre de la propiedad debe comenzar con mayúscula."));
    }

    @Test
    @DisplayName("Valida la longitud del nombre.")
    void nameSizeException() throws Exception {
        String request = "{\"prop_name\": \"Testasedcgtyuiopmjnhbgvfcdewqazsxcdf\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("La longitud del nombre no puede superar los 30 caracteres."));
    }

    @Test
    @DisplayName("Valida que el barrio no sea nulo.")
    void districtNotNullException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El barrio no puede estar vacío."));
    }

    @Test
    @DisplayName("Valida la longitud del nombre.")
    void environmentsNotNullException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("La lista de ambientes no puede estar vacía."));
    }

    @Test
    @DisplayName("Valida que el nombre del distrito no se nulo ")
    void nameNotBlankDistrictException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrictNullName(1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("Environment1", 6, 2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";

        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El barrio no puede estar vacío."));
    }

    @Test
    @DisplayName("Valida que el precio maximo de metro cuadrado")
    void priceDistrictException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100000d) +
                "," +
                "\"environments\": [" +
                getEnvironments("Environment1", 6, 2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";

        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S."));
    }

    @Test
    @DisplayName("Valida que el precio maximo de metro cuadrado")
    void priceNotNullDistrictException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrictNullPrice("Belgrano") +
                "," +
                "\"environments\": [" +
                getEnvironments("Environment1", 6, 2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";

        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El precio de un barrio no puede estar vacío"));
    }

    @Test
    @DisplayName("Valida que el nombre del ambiente no sea nulo")
    void environmentsNameNotNullException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getNameNullEnvironments(6, 2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El nombre del ambiente no puede estar vacío."));
    }

    @Test
    @DisplayName("Valida los metros maximos de ancho.")
    void environmentsWidthNotNullException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getWidthNullEnvironments("Enviroment1",2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El ancho del ambiente no puede estar vacío."));
    }

    @Test
    @DisplayName("Valida los metros maximos de largo.")
    void environmentsLenghtNotNullException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getLenghtNullEnvironments("Enviroment1",2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El largo del ambiente no puede estar vacío."));
    }

    @Test
    @DisplayName("Valida que el nombre del ambiente no sea nulo")
    void environmentsWidhtMaxException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("Environment3", 26, 2) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El máximo ancho permitido por propiedad es de 25 mts."));
    }
    @Test
    @DisplayName("Valida que el nombre del ambiente no sea nulo")
    void environmentsLengthMaxException() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("Belgrano", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("Environment3", 24, 34) + "," +
                getEnvironments("Environment2", 3, 3) + "," +
                getEnvironments("Environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El máximo largo permitido por propiedad es de 33 mts."));
    }

//////////////////////////////////

    @Test
    @DisplayName("Valida districto")
    void notFoundDistrictName() throws Exception {
        String request = "{\"prop_name\": \"Test\", " +
                "\"district\":" +
                getDistrict("NotFound", 1100d) +
                "," +
                "\"environments\": [" +
                getEnvironments("environment1", 6, 2) + "," +
                getEnvironments("environment2", 3, 3) + "," +
                getEnvironments("environment3", 2, 2) +
                "]}";
        this.mockMvc.perform(
                post(POST_OF_TOTAL_SQUARE_METERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Message").value("El Barrio no existe en la BD"));
    }




    /////////////
    private String getDistrict(String name, Double precio) {
        return "{\"district_name\": \"" + name + "\", \"district_price\": " + precio + "}";
    }
    private String getDistrictNullPrice(String name) {
        return "{\"district_name\": \"" + name + "\" }";
    }

    private String getDistrictNullName(Double precio) {
        return "{\"district_price\": " + precio + "}";
    }

    private String getEnvironments(String name, int width, int length) {
        return "{\"enviroment_name\": \"" + name + "\", \"enviroment_width\": " + width + ", \"enviroment_length\": " + length + "}";
    }

    private String getNameNullEnvironments( int width, int length) {
        return "{\"enviroment_width\": " + width + ", \"enviroment_length\": " + length + "}";
    }
    private String getWidthNullEnvironments(String name, int length) {
        return "{\"enviroment_name\": \"" + name + "\",\"enviroment_length\": " + length + "}";
    }
    private String getLenghtNullEnvironments(String name, int width) {
        return "{\"enviroment_name\": \"" + name + "\",\"enviroment_width\": " + width + "}";
    }
}
