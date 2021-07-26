package com.bootcamp.desafio2.integration;

import com.bootcamp.desafio2.dtos.request.DistrictDTO;
import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.util.TestUtilsGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PropertyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Validación propiedad - Largo de nombre - Supera 30 caracteres.")
    void lengthPropertyNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        request.setProp_name("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error"))
                .andExpect(jsonPath("$.message.prop_name")
                        .value("La longitud del nombre no puede superar los 30 caracteres."));
    }

    @Test
    @DisplayName("Validación propiedad - Largo de nombre del nombre - Igual a 30 caracteres.")
    void lengthPropertyNameBorderTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        request.setProp_name("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Validación propiedad - Inicio de nombre con maysucula.")
    void capitalPropertyNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        request.setProp_name("testName");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error"))
                .andExpect(jsonPath("$.message.prop_name")
                        .value("El nombre de la propiedad debe comenzar con mayúscula."));
    }

    @Test
    @DisplayName("Validación propiedad - Datos obtenidos")
    void housePriceAndAreaTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalArea").value(61D))
                .andExpect(jsonPath("$.housePrice").value(67100D));

    }


    @Test
    @DisplayName("Validación nombre del distrito vacio.")
    void emptyDistrictNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("", 1100D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El barrio no puede estar vacio."));
    }

    @Test
    @DisplayName("Validación distrito no existe en BD")
    void districtNotFoundTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouseIncorrectDistrict();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("El distrito no existe en la base."));


    }

    @Test
    @DisplayName("Validación precio del distrito ingresado distinto al precio en BD.")
    void priceNotMatchTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouseIncorrectPrice();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error")
                        .value("El precio ingresado no coincide con el que se encuentra en la bd."));
    }

    @Test
    @DisplayName("Validación nombre del distrito - Supera 45 caracteres.")
    void lengthDistrictNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1100D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("La longitud del barrio no puede superar los 45 caracteres."));
    }

    @Test
    @DisplayName("Validación nombre del distrito - Igual a 45 caracteres.")
    void lengthDistrictNameBorderTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Test nombre distrito test nombre distrito abc", 1100D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Validación precio del distrito - No puede estar vacio.")
    void emptyDistrictPrice() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Belgrano", 0);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("El precio ingresado no coincide con el que se encuentra en la bd.")).andReturn();

    }

    @Test
    @DisplayName("Validación precio del distrito - Supera los 4000.")
    void priceMore4000Test() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Belgrano", 4001D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400D))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S."));

    }

    @Test
    @DisplayName("Validación precio del distrito - Igual a 4000.")
    void priceBorder4000Test() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO("Puerto Madero", 4000D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Validación distrito - Distrito sin nombre")
    void nullDistrictNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        DistrictDTO district = new DistrictDTO(null, 4000D);
        request.setDistrict(district);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El barrio no puede estar vacio."));
    }

    @Test
    @DisplayName("Validación ambiente - Ambiente sin nombre")
    void emptyEnvironmentNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("", 10D, 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El nombre del ambiente no puede estar vacio."));
    }

    @Test
    @DisplayName("Validación ambiente - Ambiente con nombre nulo")
    void nullEnvironmentNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO(null, 10D, 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El nombre del ambiente no puede estar vacio."));
    }


    @Test
    @DisplayName("Validación ambiente - Ambiente sin mayuscula")
    void capitalEnvironmentNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("testName", 10D, 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);


        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result =  mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El nombre del ambiente debe comenzar con mayuscula."));
    }

    @Test
    @DisplayName("Validación ambiente - Largo nombre de ambiente - Supera los 30 caracteres")
    void lengthEnvironmentNameTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 10D, 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result =  mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("La longitud del nombre no puede superar los 30 caracteres."));
    }

    @Test
    @DisplayName("Validación ambiente - Largo nombre de ambiente - Igual a 30 caracteres")
    void sizeEnvironmentNameBorderTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 10D, 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Validación ambiente - Ancho ambiente nulo.")
    void emptyEnvironmentWidthTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("Test", 10D, null, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);


        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El ancho del ambiente no puede estar vacio."));

    }
    @Test
    @DisplayName("Validación ambiente - Ancho de ambiente supera el maximo")
    void EnvironmentWidthTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("Test", 10D, 26D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result =  mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El maximo ancho permitido por propiedad es de 25 mts."));
    }

    @Test
    @DisplayName("Validación ambiente - Largo ambiente vacio")
    void emptyEnvironmentLengthTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("Test", null , 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result =  this.mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El largo del ambiente no puede estar vacio."));
    }


    @Test
    @DisplayName("Validación largo del ambiente - Largo ambiente nulo.")
    void EnvironmentLengthTest() throws Exception {

        HouseDTO request = TestUtilsGenerator.getDefaultHouse();
        EnvironmentDTO room = new EnvironmentDTO("Test", null, 10D, 100D);
        List<EnvironmentDTO> listRooms = new ArrayList<>();
        listRooms.add(room);
        request.setRooms(listRooms);
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(request);
        MvcResult result =  mockMvc.perform(post("/home/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Validations error")).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ErrorMessage error = mapper.readValue(result.getResponse().getContentAsString(), ErrorMessage.class);
        assertTrue(error.getMessage().containsValue("El largo del ambiente no puede estar vacio."));
    }


}
