package com.meli.tasaciones.integration;

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
public class ControllerIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void givenInvalidPayloadWhenCalcularThenExpectErrorMessage() throws Exception {
    mockMvc.perform(post("/metroscuadrados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidPayload()))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void givenValidPayloadWhenCalcularThenCorrect() throws Exception {
    mockMvc.perform(post("/metroscuadrados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(validPayload()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.metrosTotales").value(16))
            .andExpect(jsonPath("$.valorDeLaCasa").value(16000.0))
            .andExpect(jsonPath("$.habitacionMasGrande.environment_width").value(3.0))
            .andExpect(jsonPath("$.habitacionMasGrande.environment_length").value(2.0))
            .andExpect(jsonPath("$.metrosCuadradosPorHabitacion.Sala").value(4.0))
            .andExpect(jsonPath("$.metrosCuadradosPorHabitacion.Comedor").value(6.0))
            .andExpect(jsonPath("$.metrosCuadradosPorHabitacion.Recamara").value(6.0));
  }

  @Test
  void givenCasaSinHabitacionesPayloadWhenCalcularThenExceptionThrown() throws Exception {
    mockMvc.perform(post("/metroscuadrados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payloadSinCuartos()))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void givenNonExistingBarrioWhenCalcularThenExceptionThrown() throws Exception {
    mockMvc.perform(post("/metroscuadrados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidBarrioPayload()))
            .andDo(print())
            .andExpect(jsonPath("$.errorMessage").value("El barrio NoExiste no existe"))
            .andExpect(status().isBadRequest());
  }

  @Test
  void givenInvalidBarrioPriceWhenCalcularThenExceptionThrown() throws Exception {
    mockMvc.perform(post("/metroscuadrados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidBarrioPricePayload()))
            .andDo(print())
            .andExpect(jsonPath("errorMessage").value("El precio del barrio no coincide con el del repositorio"))
            .andExpect(status().isBadRequest());
  }

  @Test
  void givenInvalidValueTypeWhenCalcularThenExceptionThrown() throws Exception {
    mockMvc.perform(post("/metroscuadrados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidValueTypePayload()))
            .andDo(print())
            .andExpect(jsonPath("$.errorMessage").exists())
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(status().isBadRequest());
  }

  public String validPayload() {
    return "{\n" +
            "  \"prop_name\": \"Casa valida\",\n" +
            "  \"district_name\": \"Palermo\",\n" +
            "  \"district_price\": 1000.0,\n" +
            "  \"environments\": [\n" +
            "    {\n" +
            "      \"environment_name\": \"Sala\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"Comedor\",\n" +
            "      \"environment_width\": 3.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"Recamara\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 3.0\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

  public String invalidBarrioPayload() {
    return "{\n" +
            "  \"prop_name\": \"Casa valida\",\n" +
            "  \"district_name\": \"NoExiste\",\n" +
            "  \"district_price\": 1000.0,\n" +
            "  \"environments\": [\n" +
            "    {\n" +
            "      \"environment_name\": \"Sala\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"Comedor\",\n" +
            "      \"environment_width\": 3.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"Recamara\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 3.0\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

  public String invalidBarrioPricePayload() {
    return "{\n" +
            "  \"prop_name\": \"Casa valida\",\n" +
            "  \"district_name\": \"Palermo\",\n" +
            "  \"district_price\": 50.0,\n" +
            "  \"environments\": [\n" +
            "    {\n" +
            "      \"environment_name\": \"Sala\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"Comedor\",\n" +
            "      \"environment_width\": 3.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"Recamara\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 3.0\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

  public String invalidPayload() {
    return "{\n" +
            "  \"prop_name\": \"casa valida                                                           \",\n" +
            "  \"district_name\": \"palermo\",\n" +
            "  \"district_price\": 4000.1,\n" +
            "  \"environments\": [\n" +
            "    {\n" +
            "      \"environment_name\": \"sala\",\n" +
            "      \"environment_width\": 0.0,\n" +
            "      \"environment_length\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"comedor\",\n" +
            "      \"environment_width\": 3.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"recamara\",\n" +
            "      \"environment_width\": -2.0,\n" +
            "      \"environment_length\": 3.0\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

  public String invalidValueTypePayload() {
    return "{\n" +
            "  \"prop_name\": \"casa valida                                                           \",\n" +
            "  \"district_name\": \"palermo\",\n" +
            "  \"district_price\": 4000.1,\n" +
            "  \"environments\": [\n" +
            "    {\n" +
            "      \"environment_name\": \"sala\",\n" +
            "      \"environment_width\": \"hola\",\n" +
            "      \"environment_length\": 3.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"comedor\",\n" +
            "      \"environment_width\": 3.0,\n" +
            "      \"environment_length\": 2.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"environment_name\": \"recamara\",\n" +
            "      \"environment_width\": 2.0,\n" +
            "      \"environment_length\": 3.0\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

  private String payloadSinCuartos() {
    return "{\n" +
            "  \"prop_name\": \"Casa valida\",\n" +
            "  \"district_name\": \"Palermo\",\n" +
            "  \"district_price\": 1000.0,\n" +
            "  \"environments\": []\n" +
            "}";
  }
}
