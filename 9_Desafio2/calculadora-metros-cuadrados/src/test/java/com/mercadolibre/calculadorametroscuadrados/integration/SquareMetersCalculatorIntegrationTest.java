package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.mercadolibre.calculadorametroscuadrados.utils.testUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class SquareMetersCalculatorIntegrationTest {
    @MockBean
    PriceRepositoryImpl repository;

    @Autowired
    CalculateService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCalculateAndReturnResponseFor1Room() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();

        HouseResponseDTO expectedResponse = service.calculate(houseDTO);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String jsonPayload = writer.writeValueAsString(houseDTO);
        String expectedResponseJson = writer.writeValueAsString(expectedResponse);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPayload)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        Assertions.assertEquals(expectedResponseJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void shouldReturnBadRequestForNullHouse() throws Exception {
        ErrorDTO error = new ErrorDTO("HttpMessageNotReadableException", "Required request body is missing");
        shouldReturnBadRequest(null, error);
    }

    @Test
    void shouldReturnBadRequestForEmptyHouseName() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre de la propiedad no puede estar vacío");
        houseDTO.setName("");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForNullHouseName() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre de la propiedad no puede estar vacío");
        houseDTO.setName(null);
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForOver30CharsName() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre de la propiedad no debe superar los 30 caracteres");
        houseDTO.setName("A" + "123456".repeat(5));
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForLowerCaseHouseNameStart() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre de la propiedad debe comenzar con mayúsculas");
        houseDTO.setName("alejo");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForEmptyRoomName() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("", 10, 10);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre del ambiente no puede estar vacío");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForNullRoomName() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO(null, 10, 10);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre del ambiente no puede estar vacío");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForLowerCaseRoomNameStart() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("alejopieza", 10, 10);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El nombre del ambiente debe comenzar con mayúscula");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForOver30CharsRoomName() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("A" + "123456".repeat(5), 10, 10);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "La longitud del nombre del ambiente no puede superar los 30 caracteres");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForNullRoomWidth() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("Alejo", null, 10);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El ancho del ambiente no debe estar vacío");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForOver25RoomWidth() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("Alejo", 26, 10);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El máximo ancho de un ambiente permitido es de 25 mts");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForEmptyRoomLength() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("Alejo", 20, null);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El largo del ambiente no debe estar vacío");
        shouldReturnBadRequest(houseDTO, error);
    }

    @Test
    void shouldReturnBadRequestForOver33RoomLength() throws Exception {
        HouseDTO houseDTO = testUtils.generateValidHouse();
        RoomDTO roomDTO = new RoomDTO("Alejo", 20, 34);
        houseDTO.getRooms().add(roomDTO);
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", "El máximo largo de un ambiente permitido es de 33 mts");
        shouldReturnBadRequest(houseDTO, error);
    }

    private void shouldReturnBadRequest(HouseDTO houseDTO, ErrorDTO error) throws Exception {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String jsonPayload = writer.writeValueAsString(houseDTO);
        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPayload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(
                        result -> Assertions.assertTrue(
                                (result.getResolvedException() instanceof MethodArgumentNotValidException)
                                        || result.getResolvedException() instanceof HttpMessageNotReadableException)
                ).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertTrue(jsonResult.contains(error.getError()) && jsonResult.contains(error.getStatus()));
    }
}
