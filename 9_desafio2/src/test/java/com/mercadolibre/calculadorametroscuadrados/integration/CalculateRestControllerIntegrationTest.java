package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dtos.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CalculateRestControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    HouseDTO requestDTO = new HouseDTO();
    HouseResponseDTO responseDTO = new HouseResponseDTO();

    @BeforeEach
    void init() {

        ArrayList<EnvironmentDTO> environments = new ArrayList<>();
        DistrictDTO validDistrict = new DistrictDTO("Belgrano", 1100.0);

        EnvironmentDTO environment1 = new EnvironmentDTO("Cocina", 2.0, 2.0, 4.0);
        EnvironmentDTO environment2 = new EnvironmentDTO("Salon", 9.1, 8.0, 72.8);
        EnvironmentDTO environment3 = new EnvironmentDTO("Pieza", 4.0, 4.0, 16.0);

        environments.add(environment1);
        environments.add(environment2);
        environments.add(environment3);

        requestDTO.setProp_name("ValidHouse");
        requestDTO.setDistrict(validDistrict);
        requestDTO.setEnvironments(environments);

        responseDTO.setProp_name("ValidHouse");
        responseDTO.setDistrict(validDistrict);
        responseDTO.setEnvironments(environments);
        responseDTO.setBiggest(environment2);
        responseDTO.setPrice(102080.0);
        responseDTO.setSquareFeet(92.8);
    }

    @Test
    @DisplayName("Valid Request")
    void validRequest() throws Exception {

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
//TODO: resquestDTO and responseDTO contain valid
        String payloadJson = writer.writeValueAsString(requestDTO);
        String responseJson = writer.writeValueAsString(responseDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));

    }

    @Test
    @DisplayName("District Name Not Found")
    void invalidRequestDistrictNotFound() throws Exception {

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        //TODO: responseExceptionDTO contains the response thrown for DistrictNotFounException
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Detail", "Barrio no encontrado");
        ErrorMessage responseExceptionDTO = new ErrorMessage(HttpStatus.NOT_FOUND.value(), "Error en nombre barrio", fields);

        //TODO: setting the requirement to throw the exception
        requestDTO.getDistrict().setDistrict_name("Barrio que no esta en la BD");

        String payloadJson = writer.writeValueAsString(requestDTO);
        String responseJson = writer.writeValueAsString(responseExceptionDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));

    }


    @Test
    @DisplayName("District price not match with repository")
    void invalidRequestDistictPriceNotMatchWithBD() throws Exception {

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        //TODO: responseExceptionDTO contains the response thrown for IncorrectDistrictPriceException
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Detail", "Precio no coincide con la BD");
        ErrorMessage responseExceptionDTO = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Error en precio barrio", fields);

        //TODO: setting the requirement to throw the exception
        requestDTO.getDistrict().setDistrict_price(2999.99);

        String payloadJson = writer.writeValueAsString(requestDTO);
        String responseJson = writer.writeValueAsString(responseExceptionDTO);

        MvcResult mvcResult =
                this.mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(responseJson, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));

    }

}


