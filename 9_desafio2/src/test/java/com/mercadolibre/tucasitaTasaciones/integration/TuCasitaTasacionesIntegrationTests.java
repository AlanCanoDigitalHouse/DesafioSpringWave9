package com.mercadolibre.tucasitaTasaciones.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.PropertyResponseDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ErrorMessage;
import com.mercadolibre.tucasitaTasaciones.exceptions.ErrorMessageException;
import com.mercadolibre.tucasitaTasaciones.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TuCasitaTasacionesIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test Validar Post calculate")
    public void testCalculate() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        PropertyDTO payloadDTO = TestUtilsGenerator.getPropertyWith3Environments("CasaOk");

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        PropertyResponseDTO responseDTO = TestUtilsGenerator.getPropertyResponseWith3Environments("CasaOk");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test mensaje excepción notFoundDistrict")
    public void testExceptionNotFound() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        PropertyDTO payloadDTO = TestUtilsGenerator.getPropertyNotExist();

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        ErrorMessageException responseDTO = new ErrorMessageException(HttpStatus.NOT_FOUND.value(), "El barrio " + payloadDTO.getDistrict().getDistrict_name() + " no existe.");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is4xxClientError())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Test mensaje excepción wrongPrice")
    public void testExceptionWrongPrice() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        PropertyDTO payloadDTO = TestUtilsGenerator.getPropertyPriceWrong();

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        ErrorMessageException responseDTO = new ErrorMessageException(HttpStatus.BAD_REQUEST.value(), "El precio por metro cuadradro del barrio es incorrecto.");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is4xxClientError())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("Test mensaje valid")
    public void testExceptionValid() throws Exception {
        /* TODO: OBJETO QUE SE ENVIA EN EL PAYLOAD*/
        PropertyDTO payloadDTO = TestUtilsGenerator.getPropertyWrong();

        /* TODO: OBJETO QUE SE COMPARAR EN EL RESPONSE*/
        HashMap<String, String> fields = new HashMap<>();
        fields.put("prop_name", "El nombre de la propiedad no puede estar vacío.");
        ErrorMessage responseDTO = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validation error", fields);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is4xxClientError())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

}
