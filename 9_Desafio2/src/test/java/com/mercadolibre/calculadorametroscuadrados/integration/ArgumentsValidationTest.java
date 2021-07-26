package com.mercadolibre.calculadorametroscuadrados.integration;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictsListDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnviromentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DistrictDoesntExistException;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.service.ICalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArgumentsValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ICalculateService service;

    @InjectMocks
    CalculateRestController controller;

    @BeforeEach
    public void initDistricts() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("demo");
        DistrictsListDTO districts = new DistrictsListDTO(list);
        controller.loadDistricts(districts);
    }

    // Aniadir una casa con nombre que empieza en minuscula
    @Test
    void tryToAddHouseWithLowerCaseName() throws Exception {

        String request = "{\"name\": \"oficina\", \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 3) +
                "]}";

        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.name").value("El nombre de la propiedad debe comenzar con mayuscula."));
    }

    // Aniadir una casa sin nombre
    @Test
    void tryToAddHouseWithEmptyName() throws Exception {

        String request = "{ \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 3) +
                "]}";

        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.name").value("El nombre de la propiedad no puede estar vacio."));
    }

    // Aniadir una casa con nombre muy largo
    @Test
    void tryToAddHouseWithTooLongName() throws DistrictDoesntExistException, Exception {

        String request = "{\"name\": \"Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\", \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 3) +
                "]}";

        this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validations Error"))
                .andExpect(jsonPath("$.message.name").value("La longitud del nombre no puede superar los 30 caracteres."));
    }

    // Aniadir una casa con nombre de distrito muy largo
    @Test
    void tryToAddHouseWithTooLongDistrictName() throws Exception {

        String request = "{\"name\": \"Oficina\", \"district\":" +
                getDistrict("demoooooooooooooooooooooooooooooooooooooooooooooooo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 3) +
                "]}";

        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"district.name\":\""+
                "La longitud del barrio no puede superar los 45 caracteres."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con nombre de distrito vacio
    @Test
    void tryToAddHouseWithEmptyDistrictName() throws Exception {

        String request = "{\"name\": \"Oficina\", \"district\":" +
                "{\"price\": 10 }" + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 3) +
                "]}";

        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"district.name\":\""+
                "El barrio no puede estar vacio."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con precio de distrito muy alto
    @Test
    void tryToAddHouseWithDistrictPriceMuchHigher() throws  Exception {

        String request = "{\"name\": \"Oficina\", \"district\":" +
                getDistrict("demo", 5000 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 3) +
                "]}";

        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"district.price\":\""+
                "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S"+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con nombre de ambiente que empieza con minuscula
    @Test
    void tryToAddHouseWithLowerCaseEnviromentName() throws Exception {

        String request = "{\"name\": \"Oficina\", \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("espacio abierto", 3, 3) +
                "]}";

        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"enviroments[0].name\":\""+
                "El nombre del ambiente debe comenzar con mayuscula."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con nombre de ambiente vacio
    @Test
    void tryToAddHouseWithEmptyEnviromentName() throws Exception {

        String request = "{\"name\": \"Oficina\" , \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                "{ \"width\": 3, \"length\": 3} "+
                "]}";

        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"enviroments[0].name\":\""+
                "El nombre del ambiente no puede estar vacio."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con nombre de ambiente muy largo
    @Test
    void tryToAddHouseWithTooLongEnviromentName() throws Exception {
        String request = "{\"name\": \"Oficina\", \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 3, 3) + "]}";
        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"enviroments[0].name\":\""+
                "La longitud del nombre no puede superar los 30 caracteres."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con ancho de ambiente demasiado grande
    @Test
    void tryToAddHouseWithMuchHigherEnviromentWidth() throws Exception {
        String request = "{\"name\": \"Oficina\", \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 26, 3) + "]}";
        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"enviroments[0].width\":\""+
                "El maximo ancho permitido por propiedad es de 25 mts."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    // Aniadir una casa con largo de ambiente demasiado grande
    @Test
    void tryToAddHouseWithMuchHigherEnviromentLength() throws Exception {
        String request = "{\"name\": \"Oficina\", \"district\":" +
                getDistrict("demo", 500 ) + "," +
                "\"enviroments\": [" +
                getRoom("Espacio abierto", 3, 34) + "]}";
        String expected = "{\"status\":400,\"error\":\"Validations Error\",\"message\":{" + "\"enviroments[0].length\":\""+
                "El maximo largo permitido por propiedad es de 33 mts."+"\""  +"}}";

        MvcResult mvcResult = this.mockMvc.perform(
                post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertEquals(expected, mvcResult.getResponse().getContentAsString());
    }

    private String getRoom(String name, int width, int length) {
        return "{\"name\": \""+name+"\", \"width\": "+width+", \"length\": "+length+"}";
    }

    private String getDistrict(String name, int price) {
        return "{\"name\": \""+name+"\", \"price\": "+price+"}";
    }
}
