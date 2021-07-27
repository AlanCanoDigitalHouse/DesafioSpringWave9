package com.mercadolibre.tucasita.controller;

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
public class PropertyInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Given good property return ok")
    void getPropertyInfoTest() throws Exception {
        String request = "{\n" + "    \"prop_name\":\"Chalet\",\n" + "    \"district\": \n" + "        {\n" +
                "            \"district_name\":\"Recoleta\",\n" + "            \"district_price\": 80.0\n" +
                "        },\n" + "    \"environments\":[\n" + "        {\n" +
                "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                "            \"environment_name\":\"Dormitorio\",\n" + "            \"environment_width\": 4.5,\n" +
                "            \"environment_length\": 4.0\n" + "        }\n" + "    ]\n" + "}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.totalSquareFeet").value(48));
    }

    @Test
    @DisplayName("Given property with low case capital letter returns bad request")
    void getPropertyInfoBadPropertyNameTest() throws Exception {
        String request = "{\n" + "    \"prop_name\":\"chalet\",\n" + "    \"district\": \n" + "        {\n" +
                "            \"district_name\":\"Recoleta\",\n" + "            \"district_price\": 80.0\n" +
                "        },\n" + "    \"environments\":[\n" + "        {\n" +
                "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                "            \"environment_name\":\"Dormitorio\",\n" + "            \"environment_width\": 4.5,\n" +
                "            \"environment_length\": 4.0\n" + "        }\n" + "    ]\n" + "}";

        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"prop_name\":\"El nombre de la propiedad debe comenzar con mayÃºscula.\"}}"));

    }

    @Test
    @DisplayName("Given bad json return bad request")
    void getPropertyInfoBadJsonTest() throws Exception {
        String request = "{,}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Error found parsing some of the request arguments. Please check the fields\",\"message\":{\"Detail\":\"JSON parse error: Unexpected character (',' (code 44)): was expecting double-quote to start field name; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character (',' (code 44)): was expecting double-quote to start field name\\n at [Source: (PushbackInputStream); line: 1, column: 3]\"}}"));
    }

    @Test
    @DisplayName("Given property without name returns bad request")
    void getPropertyInfoNoNameTest() throws Exception {
        String request = "{\n" + "    \"district\": \n" + "        {\n" +
                "            \"district_name\":\"recoleta\"\n" + "        },\n" + "    \"environments\":[\n" +
                "        {\n" + "            \"environment_name\":\"Cocina\",\n" +
                "            \"environment_width\": 6.0,\n" + "            \"environment_length\": 5.0\n" +
                "        },\n" + "        {\n" + "            \"environment_name\":\"Dormitorio\",\n" +
                "            \"environment_width\": 4.5,\n" + "            \"environment_length\": 4.0\n" +
                "        }\n" + "    ]\n" + "}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"prop_name\":\"El nombre de la propiedad no puede estar vacÃ\u00ADo.\"}}"));
    }

    @Test
    @DisplayName("Given property with null name returns bad request")
    void getPropertyInfoNullNameTest() throws Exception {
        String request = "{\n" + "    \n" + "    \"district\": \n" + "        {\n" +
                "            \"district_name\":\"recoleta\",\n" + "            \"district_price\": 80.0\n" +
                "        },\n" + "    \"environments\":[\n" + "        {\n" +
                "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                "            \"environment_name\":\"Dormitorio\",\n" + "            \"environment_width\": 4.5,\n" +
                "            \"environment_length\": 4.0\n" + "        }\n" + "    ]\n" + "}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"prop_name\":\"El nombre de la propiedad no puede estar vacÃ\u00ADo.\"}}"));
    }

    @Test
    @DisplayName("Given property with null district returns bad request")
    void getPropertyInfoNullDistrictTest() throws Exception {
        String request =
                "{\n" + "    \"prop_name\":\"Chalet\",\n" + "    \n" + "    \"environments\":[\n" + "        {\n" +
                        "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                        "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                        "            \"environment_name\":\"Dormitorio\",\n" +
                        "            \"environment_width\": 4.5,\n" + "            \"environment_length\": 4.0\n" +
                        "        }\n" + "    ]\n" + "}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"district\":\"Por favor proporcione un barrio para la propiedad\"}}"));
    }

    @Test
    @DisplayName("Given district with null name returns bad request")
    void getPropertyInfoNullDistrictNameTest() throws Exception {
        String request = "{\n" + "    \"prop_name\":\"Chalet\",\n" + "    \"district\": \n" + "        {\n" +
                "            \"district_price\": 4000.0\n" + "        },\n" + "    \"environments\":[\n" + "        " +
                "{\n" + "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                "            \"environment_name\":\"Dormitorio\",\n" + "            \"environment_width\": 4.5,\n" +
                "            \"environment_length\": 4.0\n" + "        }\n" + "    ]\n" + "}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"district.district_name\":\"El barrio no puede estar vacÃ\u00ADo.\"}}"));
    }

    @Test
    @DisplayName("Given district with overrated price returns bad request")
    void getPropertyInfoOverratedDistrictPriceTest() throws Exception {
        String request = "{\n" + "    \"prop_name\":\"Chalet\",\n" + "    \"district\": \n" + "        {\n" +
                "            \"district_name\":\"Recoleta\",\n" + "            \"district_price\": 4001.0\n" +
                "        },\n" + "    \"environments\":[\n" + "        {\n" +
                "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                "            \"environment_name\":\"Dormitorio\",\n" + "            \"environment_width\": 4.5,\n" +
                "            \"environment_length\": 4.0\n" + "        }\n" + "    ]\n" + "}";
        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andExpect(content().string("{\"status\":400,\"error\":\"Validations Error\",\"message\":{\"district.district_price\":\"El precio mÃ¡ximo permitido por metro cuadrado no puede superar los 4000 U$S.\"}}"));
    }

    @Test
    @DisplayName("Given district that doesn't exists returns internal server error")
    void getPropertyInfoDistrictDoesentExistTest() throws Exception {
        String request = "{\n" + "    \"prop_name\":\"Chalet\",\n" + "    \"district\": \n" + "        {\n" +
                "            \"district_name\":\"Caballito\",\n" + "            \"district_price\": 4000.0\n" +
                "        },\n" + "    \"environments\":[\n" + "        {\n" +
                "            \"environment_name\":\"Cocina\",\n" + "            \"environment_width\": 6.0,\n" +
                "            \"environment_length\": 5.0\n" + "        },\n" + "        {\n" +
                "            \"environment_name\":\"Dormitorio\",\n" + "            \"environment_width\": 4.5,\n" +
                "            \"environment_length\": 4.0\n" + "        }\n" + "    ]\n" + "}";

        this.mockMvc.perform(post("/propertyInfo").contentType(MediaType.APPLICATION_JSON).content(request))
                .andDo(print()).andExpect(status().isInternalServerError()).andExpect(content().string("{\"status\":400,\"error\":\"Internal Server Error, please see details\",\"message\":{\"Detail\":\"No se encuentra el barrio en la base de datos\"}}"));
    }

}
