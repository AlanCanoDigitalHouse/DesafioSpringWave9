package com.tucasitatasaciones.integration;

import com.tucasitatasaciones.globalconstants.Message;
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
public class CalculateServiceTestIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test para /calculate - Entrada correcta - Se espera status 200 - Se valida los metros cuadrados respondidos")
    void calculate() throws Exception {
        String request =
                "{ \"prop_name\": \"Residencia Santo Tome\"," +
                "    \"district\": {" +
                "            \"district_name\":\"Palermo\"," +
                "            \"district_price\" : 1000" +
                "        }," +
                "    \"environments\": " +
                "       [ " +
                "       {" +
                "       \"environment_name\": \"Salon principal\"," +
                "       \"environment_width\": 12," +
                "       \"environment_length\": 30" +
                "       }" +
                "       ]" +
                "}";
        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value("360.0"));
    }

    @Test
    @DisplayName("Test para /calculate - Entrada incorrecta - Nombre propiedad no valido- Se espera status 400 - ")
    void calculateError() throws Exception {
        String request =
                "{ \"prop_name\": \"##wrong name###\"," +
                        "    \"district\": {" +
                        "            \"district_name\":\"Palermo\"," +
                        "            \"district_price\" : 1000" +
                        "        }," +
                        "    \"environments\": " +
                        "       [ " +
                        "       {" +
                        "       \"environment_name\": \"Estancia\"," +
                        "       \"environment_width\": 20," +
                        "       \"environment_length\": 30" +
                        "       }" +
                        "       ]" +
                        "}";
        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message.prop_name")
                        .value(Message.PATTERN_PROPERTY_NAME));
    }

    @Test
    @DisplayName("Test para /calculate - Entrada incorrecta - Precio no acorde al barrio - Se espera status 400 - ")
    void calculateErrorWrongPrice1() throws Exception {
        String request =
                "{ \"prop_name\": \"Nombre Correcto\"," +
                        "    \"district\": {" +
                        "            \"district_name\":\"Palermo\"," +
                        "            \"district_price\" : 4000" +
                        "        }," +
                        "    \"environments\": " +
                        "       [ " +
                        "       {" +
                        "       \"environment_name\": \"Estancia\"," +
                        "       \"environment_width\": 20," +
                        "       \"environment_length\": 30" +
                        "       }" +
                        "       ]" +
                        "}";
        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message.DistrictBadRequestException")
                        .value(Message.DISTRICT_WRONG_PRICE));
    }


}