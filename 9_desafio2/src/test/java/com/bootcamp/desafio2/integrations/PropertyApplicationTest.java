package com.bootcamp.desafio2.integrations;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ErrorMessageDto;
import com.bootcamp.desafio2.util.TestUtilGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void init() {
        TestUtilGenerator.emptyPricesFile();
    }

    //Validate happy path
    @Test
    @DisplayName("Test Calculate Area")
    void testCalculateArea() throws Exception{

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House");
        DistrictDto districtDto = new DistrictDto("Palermo", 1000.0);
        TestUtilGenerator.appendNewDistrict(districtDto);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Validate when district not exists on database
    @Test
    @DisplayName("Test Calculate Area with Errors")
    void testCalculateAreaErrors() throws Exception{

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House");
        DistrictDto districtDto = new DistrictDto("Narvarte", 1000.0);
        TestUtilGenerator.appendNewDistrict(districtDto);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    //Validate not empty name
    @Test
    @DisplayName("Test DistrictDto property districtName")
    void testDto() throws Exception{

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRoomsWithoutName("House");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorMessageDto errorMessageDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ErrorMessageDto.class);

        Assertions.assertTrue(errorMessageDto.getFields().containsValue("El barrio no puede estar vacÃ\u00ADo"));
    }

    //Validate District Price
    @Test
    @DisplayName("Test DistrictDto property districtPrice")
    void testDtoPrice() throws Exception{

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRoomsWithoutPrice("House");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorMessageDto errorMessageDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ErrorMessageDto.class);

        Assertions.assertTrue(errorMessageDto.getFields().containsValue("El precio de un barrio no puede estar vacÃ\u00ADo"));
    }

    @Test
    @DisplayName("Test Exception District Not Found")
    void testNotFoundDistrict() throws Exception {

        PropertyDto propertyDto = TestUtilGenerator.getHouseWithRooms("House");
        TestUtilGenerator.appendNewDistrict(new DistrictDto("Peru",1000D));

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("No existe un barrio que corresponda a los datos enviados"));

    }

    @Test
    @DisplayName("Test Exception District Price")
    void testDistrictPrice() throws Exception {

        PropertyDto propertyDto = TestUtilGenerator.getBadPrice("House");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorMessageDto errorMessageDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ErrorMessageDto.class);

        Assertions.assertTrue(errorMessageDto.getFields().containsValue("El precio mÃ¡ximo permitido por metro cuadrado no puede superar los 4000 U$S"));

    }

    @Test
    @DisplayName("Test Enviroments Empty")
    void testEnviromentsEmpty() throws Exception {

        PropertyDto propertyDto = TestUtilGenerator.getEmptyEnviroments("House");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorMessageDto errorMessageDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ErrorMessageDto.class);

        Assertions.assertTrue(errorMessageDto.getFields().containsValue("La casa debe tener almenos una habitacion"));

    }

    @Test
    @DisplayName("Test Enviroments Size")
    void testEnviromentSize() throws Exception {

        PropertyDto propertyDto = TestUtilGenerator.getWrongEnviroment("House");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJSON = writer.writeValueAsString(propertyDto);

        MvcResult mvcResult = this.mockMvc.perform(post("/house/calculateMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorMessageDto errorMessageDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ErrorMessageDto.class);

        Assertions.assertTrue(errorMessageDto.getFields().containsValue("El mÃ¡ximo largo permitido por propiedad es de 33mts"));

    }


}
