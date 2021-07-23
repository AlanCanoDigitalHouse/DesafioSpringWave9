package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void calculateInfoHouseSuccessfully() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 20d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    //Validaciones de Mensajes de error:

    @Test
    void messageErrorWidthBigger() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 25.001d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El máximo ancho permitido por propiedad es de 25 mts."));
    }

    @Test
    void messageErrorWidthSmaller() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 0.1d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El mimino ancho permitido por propiedad es de 1.5 mts"));
    }

    @Test
    void messageErrorWidthNull() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", null, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El ancho del ambiente no puede estar vacío."));
    }

    @Test
    void messageErrorLengthBigger() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 25d, 33.1d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El máximo largo permitido por propiedad es de 33 mts."));
    }

    @Test
    void messageErrorLengthSmaller() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 0.1d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El mimino largo permitido por propiedad es de 1.5 mts"));
    }

    @Test
    void messageErrorLengthNull() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, null, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El largo del ambiente no puede estar vacío."));
    }

    @Test
    void messageErrorNameRoomUpperCase() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El nombre del ambiente debe comenzar con mayúscula."));
    }

    @Test
    void messageErrorNameRoomNull() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO(null, 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El nombre del ambiente no puede estar vacío."));
    }

    @Test
    void messageErrorNameRoomLength() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("En este campo mayor 30 caracteres", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("La longitud del nombre no puede superar los 30 caracteres."));
    }

    @Test
    void messageErrorNotRoom() throws Exception {
        List<RoomDTO> environments = new ArrayList<>();
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("La lista de ambientes no puede estar vacía."));
    }

    @Test
    void messageErrorNullRoom() throws Exception {
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, null);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("La lista de ambientes no puede estar vacía."));
    }

    @Test
    void messageErrorDistrictPriceNull() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", null, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El precio de un barrio no puede estar vacío."));
    }

    @Test
    void messageErrorDistrictPriceSmaller() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 0.9d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El precio minimo permitido por metro cuadrado no puede ser inferiror a 1 U$S."));
    }

    @Test
    void messageErrorDistrictPriceBigger() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 4000.01d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S."));
    }

    @Test
    void messageErrorDistrictNameNull() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", null, 40d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El barrio no puede estar vacío."));
    }

    @Test
    void messageErrorDistrictNameBlank() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "", 40d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El barrio no puede estar vacío."));
    }

    @Test
    void messageErrorDistrictNameBigger() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Este campo tiene que poseer menos de 45 carac.",
                40d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("La longitud del barrio no puede superar los 45 caracteres."));
    }

    @Test
    void messageErrorNameNull() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO(null, "Palermo", 40d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El nombre de la propiedad no puede estar vacío."));
    }

    @Test
    void messageErrorNameBigger() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("Este campo posee 31 caracteres.", "Palermo",
                40d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("La longitud del nombre no puede superar los 30 caracteres."));
    }

    @Test
    void messageErrorNameUpperCase() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("house", "Palermo",
                40d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description").value("El nombre de la propiedad debe comenzar con mayúscula."));
    }

    @Test
    void calculateInfoHouseDistrictDontFound() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 10d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "San Antonio de Areco", 400d, environments);

        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("NotFoundLocation"))
                .andExpect(jsonPath("$.description").value("No existe registro para la locacion: San Antonio de Areco."));
    }


    @Test
    void calculateBadRequest() throws Exception {
        String badPayLoad = "hola1";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(badPayLoad))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("HttpMessageNotReadableException"));
    }

    @Test
    void calculateInfoHouseSuccessfullyAndEvaluateAnswer() throws Exception {
        List<RoomDTO> environments = Arrays.asList(new RoomDTO("Comedor", 20d, 10d, null),
                new RoomDTO("Living", 20d, 30d, null));
        HouseDTO houseDTO = new HouseDTO("House", "Palermo", 400d, environments);

        HouseResponseDTO houseResponseExpected = new HouseResponseDTO(houseDTO);
        houseResponseExpected.getEnvironment().forEach(roomDTO ->
                roomDTO.setSquareFeet(roomDTO.getWidth() * roomDTO.getLength()));
        houseResponseExpected.setSquareFeet(800d);
        houseResponseExpected.setBiggest(environments.get(1));
        houseResponseExpected.setPrice(320000d);


        ObjectWriter objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String housePayLoad = objectWriter.writeValueAsString(houseDTO);
        String houseExpected = objectWriter.writeValueAsString(houseResponseExpected);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(housePayLoad))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(houseExpected, mvcResult.getResponse().getContentAsString());
    }


}
