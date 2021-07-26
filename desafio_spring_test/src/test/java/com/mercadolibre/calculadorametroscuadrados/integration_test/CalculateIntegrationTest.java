package com.mercadolibre.calculadorametroscuadrados.integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    /* TODO: Test 1/7
    *   Caso Feliz, no hay errores*/
    @Test
    @DisplayName("Obtener calculos de una casa")
    public void testGetHouseInformationHappyCase() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("Home DTO");

        HouseResponseDTO houseResponseDTO = TestUtilsGenerator.getHouseResponseDTOfromHouseDTO(houseDTO);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);
        String responseJson = writer.writeValueAsString(houseResponseDTO);

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

    /* TODO: Test 2/7
    *  Verificar que el total de metros cuadrados totales calculados por
    * propiedad sea el correcto.
    * Verificar que efectivamente se devuelve el ambiente con mayor tamaño */
    @Test
    @DisplayName("Casos Reto")
    public void testGetHouseCorrectAreaHappyCase() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("Home DTO");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.prop_area").value(21.75))
                .andExpect(jsonPath("$.biggest_environment.environment_name").value("Habitacion Principal"))
                .andReturn();
    }

    /* TODO: Test 3/7
    *   Verificar que el nombre de la casa no lo reciba en minuscula*/
    @Test
    @DisplayName("Enviar nombre de casa en minuscula")
    public void testGetHouseInformationThrowNameHouse() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("home DTO");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value("El nombre de la propiedad debe comenzar con mayuscula."));
    }

    /* TODO: Test 4/7
    *   Verificar que no reciba vacío el parametro de district_name*/
    @Test
    @DisplayName("Enviar vacio nombre del distrito")
    public void testGetHouseInformationThrowNameDistrict() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("Home DTO");
        houseDTO.setDistrict_name("");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value("El barrio no puede estar vacio."));
    }

    /* TODO: Test 5/7
    *   Verificar que el barrio de entrada exista en el
    *   repositorio de barrios.
    *   Caso no feliz, el nombre no esta en la db*/
    @Test
    @DisplayName("Enviar nombre del distrito que no existe en db")
    public void testGetHouseInformationNotExistNameDistrictInDB() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseNoExistDostrictName("Home DTO");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);



        this.mockMvc.perform(post("/calculate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payloadJson))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.error").value("Error en la data de Ingreso"));
    }

    /* TODO: Test 6/7
    *   Verificar no recibir nombres de ambientes en minusculas */
    @Test
    @DisplayName("Enviar nombre del un ambiente en minuscula")
    public void testGetHouseInformationEnvironmentNameNotCapitalize() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseEnvironmentNameError("Home DTO");
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);



        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value("El nombre del ambiente debe comenzar con mayuscula."));
    }

    /* TODO: Test 7/7*/
    @Test
    @DisplayName("Metodo incorrecto en la petición")
    public void testGetHouseBadMethod() throws Exception{
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("Home DTO");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String payloadJson = writer.writeValueAsString(houseDTO);

        this.mockMvc.perform(put("/calculate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(payloadJson))
            .andDo(print())
            .andExpect(status().is(405));
    }
}
