package com.mercadolibre.calculadorametroscuadrados.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.DefaultPropertiesPersister;

import java.io.*;
import java.util.HashMap;

import java.util.Map;
import java.util.Properties;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test Calculate House Properties")
    public void calculateHouseProperties() throws Exception {

        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        HouseDTO response = TestUtilGenerator.getHouseToResponseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();


        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prop_name").value(response.getName()))
                .andExpect(jsonPath("$.district_name").value(response.getDistrictName()))
                .andExpect(jsonPath("$.district_price").value(response.getDistrictPrice()))
                .andExpect(jsonPath("$.total_square_meter").value(response.getTotalSquareMeter()))
                .andExpect(jsonPath("$.price").value(response.getPrice()))

                .andExpect(jsonPath("$.biggest.environment_name").value(response.getBiggest().getName()))
                .andExpect(jsonPath("$.biggest.environment_square_meter").value(response.getBiggest().getSquareMeter()))
                .andExpect(jsonPath("$.biggest.environment_width").value(response.getBiggest().getWidth()))
                .andExpect(jsonPath("$.biggest.environment_length").value(response.getBiggest().getLength()))

                .andExpect(jsonPath("$.environments[0].environment_name").value(response.getEnvironments().get(0).getName()))
                .andExpect(jsonPath("$.environments[0].environment_square_meter").value(response.getEnvironments().get(0).getSquareMeter()))
                .andExpect(jsonPath("$.environments[0].environment_width").value(response.getEnvironments().get(0).getWidth()))
                .andExpect(jsonPath("$.environments[0].environment_length").value(response.getEnvironments().get(0).getLength()))
                .andExpect(jsonPath("$.environments[1].environment_name").value(response.getEnvironments().get(1).getName()))
                .andExpect(jsonPath("$.environments[1].environment_square_meter").value(response.getEnvironments().get(1).getSquareMeter()))
                .andExpect(jsonPath("$.environments[1].environment_width").value(response.getEnvironments().get(1).getWidth()))
                .andExpect(jsonPath("$.environments[1].environment_length").value(response.getEnvironments().get(1).getLength()))
                .andExpect(jsonPath("$.environments[2].environment_name").value(response.getEnvironments().get(2).getName()))
                .andExpect(jsonPath("$.environments[2].environment_square_meter").value(response.getEnvironments().get(2).getSquareMeter()))
                .andExpect(jsonPath("$.environments[2].environment_width").value(response.getEnvironments().get(2).getWidth()))
                .andExpect(jsonPath("$.environments[2].environment_length").value(response.getEnvironments().get(2).getLength()))

                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());


    }

    @Test
    @DisplayName("Test Null House Name")
    public void nullHouseName() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setName(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("name", "El nombre de la propiedad no puede estar vacío.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.name").value(response.getMessage().get("name")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Size House Name")
    public void badSizeHouseName() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Map<String, String> errors = new HashMap<>();
        errors.put("name", "La longitud del nombre no puede superar los 30 caracteres.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.name").value(response.getMessage().get("name")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Pattern House Name")
    public void badPatternHouseName() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setName("aaaaaaaaaa");
        Map<String, String> errors = new HashMap<>();
        errors.put("name", "El nombre de la propiedad debe comenzar con mayúscula.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.name").value(response.getMessage().get("name")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Null House District")
    public void nullHouseDistrict() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setDistrictName(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("districtName", "El barrio no puede estar vacío.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.districtName").value(response.getMessage().get("districtName")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Size House District")
    public void badSizeHouseDistrict() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setDistrictName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Map<String, String> errors = new HashMap<>();
        errors.put("districtName", "La longitud del barrio no puede superar los 45 caracteres.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.districtName").value(response.getMessage().get("districtName")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Null House District Price")
    public void nullHouseDistrictPrice() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setDistrictPrice(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("districtPrice", "El precio de un barrio no puede estar vacío.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.districtPrice").value(response.getMessage().get("districtPrice")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Max House District Price")
    public void maxHouseDistrictPrice() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setDistrictPrice(4500.0);
        Map<String, String> errors = new HashMap<>();
        errors.put("districtPrice", "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.districtPrice").value(response.getMessage().get("districtPrice")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Min House District Price")
    public void minHouseDistrictPrice() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setDistrictPrice(-1.0);
        Map<String, String> errors = new HashMap<>();
        errors.put("districtPrice", "El precio mínimo permitido por metro cuadrado no puede ser menor a 0 U$S.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.districtPrice").value(response.getMessage().get("districtPrice")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Almost one Environment")
    public void almostOneEnvironment() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.setEnvironments(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments", "La propiedad debe tener al menos un ambiente.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.environments").value(response.getMessage().get("environments")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Null Environment Name")
    public void nullEnvironmentName() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setName(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].name", "El nombre del ambiente no puede estar vacío.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].name']").value(response.getMessage().get("environments[0].name")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Pattern Environment Name")
    public void badPatternEnvironmentName() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setName("aaaaaaaa");
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].name", "El nombre del ambiente debe comenzar con mayúscula.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].name']").value(response.getMessage().get("environments[0].name")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Size Environment Name")
    public void badSizeEnvironmentName() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].name", "La longitud del nombre no puede superar los 30 caracteres.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].name']").value(response.getMessage().get("environments[0].name")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Max Environment Width")
    public void maxEnvironmentWidth() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setWidth(26.0);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].width", "El máximo ancho permitido por propiedad es de 25 mts.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].width']").value(response.getMessage().get("environments[0].width")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Min Environment Width")
    public void minEnvironmentWidth() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setWidth(0.0);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].width", "El mínimo ancho permitido por propiedad es de 0.1 mts.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].width']").value(response.getMessage().get("environments[0].width")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Null Environment Width")
    public void nullEnvironmentWidth() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setWidth(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].width", "El ancho del ambiente no puede estar vacío.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].width']").value(response.getMessage().get("environments[0].width")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Max Environment Length")
    public void maxEnvironmentLength() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setLength(34.0);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].length", "El máximo largo permitido por propiedad es de 33 mts.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].length']").value(response.getMessage().get("environments[0].length")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Min Environment Length")
    public void minEnvironmentLength() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setLength(0.0);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].length", "El mínimo largo permitido por propiedad es de 0.1 mts.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].length']").value(response.getMessage().get("environments[0].length")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Null Environment Length")
    public void nullEnvironmentLength() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        request.getEnvironments().get(0).setLength(null);
        Map<String, String> errors = new HashMap<>();
        errors.put("environments[0].length", "El largo del ambiente no puede estar vacío.");
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validations Error", errors);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andExpect(jsonPath("$.message.['environments[0].length']").value(response.getMessage().get("environments[0].length")))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Request Format")
    public void badRequestFormat() throws Exception {
        // arrange
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Bad Request Format", new HashMap<>());

        String payloadJson = "{\"environments\":\"holaa\",\"price\":85000,\"prop_name\":\"Prop\",\"district_name\":\"Palermo\",\"district_price\":1000}";

        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Test Bad Method")
    public void badMethod() throws Exception {
        // arrange
        ErrorDTO response = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Bad Method GET", new HashMap<>());

        MvcResult mvcResult = this.mockMvc.perform(get("/calculate"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }


    @Test
    @DisplayName("Test Bad Resource Not Found")
    public void badResourceNotFound() throws Exception {
        // arrange
        HouseDTO request = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();
        ErrorDTO response = new ErrorDTO(HttpStatus.NOT_FOUND.value(), "Not Found " + "/sarasa", new HashMap<>());

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String payloadJson = writer.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/sarasa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(response.getStatus()))
                .andExpect(jsonPath("$.error").value(response.getError()))
                .andReturn();

        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @BeforeTestClass
    public void setUpInternalServerError() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            properties.setProperty("api.scope", "prueba");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // get or create the file
        File f = new File("application.properties");
        OutputStream out = null;
        try {
            out = new FileOutputStream( f );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // write into it
        DefaultPropertiesPersister p = new DefaultPropertiesPersister();
        try {
            p.store(properties, out, "Header COmment");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
