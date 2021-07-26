package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertyPriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertySquareFeetDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;
import com.mercadolibre.calculadorametroscuadrados.util.UtilPropertyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Test for calculate principal endpoint. All calculations in 1 response.
    @Test
    void shouldSendEnvironmentsWithNotAllowedDimensions() throws Exception {
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3Environments(
                "NewProperty",
                "Cheap",
                100D);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJSON = writer.writeValueAsString(houseDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/property/squareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void shouldSearchANonExistentDistrictAndFail() throws Exception {
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3EnvironmentsSameDetails(
                "NewProperty",
                "NonExistent",
                100D,
                10D,
                10D);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJSON = writer.writeValueAsString(houseDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void shouldSendWrongPayload() throws Exception {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        String requestJSON = writer.writeValueAsString(new RoomResponseDTO());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/property/squareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void shouldSendEmptyPayloadAndFail() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/property/squareFeet"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    // Calculate property total square feet
    @Test
    void calculatePropertySquareFeet() throws Exception {
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3EnvironmentsSameDetails(
                "NewProperty",
                "Cheap",
                100D,
                10D,
                10D);
        Double expectedSquareFeet = 300D;

        final PropertySquareFeetDTO expectedResponse = new PropertySquareFeetDTO();
        expectedResponse.setSquareFeet(expectedSquareFeet);
        expectedResponse.setProperty(houseDTO);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJSON = writer.writeValueAsString(houseDTO);
        String expectedJSON = writer.writeValueAsString(expectedResponse);

        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/property/squareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        assertEquals(expectedJSON, response.getResponse().getContentAsString());
    }

    // Calculate price endpoint
    @Test
    void calculateHousePrice() throws Exception {
        // Total square feet = 300
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3EnvironmentsSameDetails(
                "NewProperty",
                "Cheap",
                100D,
                10D,
                10D);
        Double expectedPrice = 30000D;

        final PropertyPriceDTO expectedResponse = new PropertyPriceDTO();
        expectedResponse.setPrice(expectedPrice);
        expectedResponse.setProperty(houseDTO);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJSON = writer.writeValueAsString(houseDTO);
        String expectedJSON = writer.writeValueAsString(expectedResponse);

        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/property/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        assertEquals(expectedJSON, response.getResponse().getContentAsString());
    }

    // Get biggest environment endpoint
    @Test
    void calculateBiggestEnvironment() throws Exception {
        // Total square feet = 325
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3SmallEnvironments(
                "NewProperty",
                "Cheap",
                100D);
        final RoomDTO environment = new RoomDTO("Environment3", 15D, 15D);
        double expectedSquareFeet = 225D;
        final RoomResponseDTO expectedEnvironment = new RoomResponseDTO();
        expectedEnvironment.setEnvironment(environment);
        expectedEnvironment.setSquare_feet(expectedSquareFeet);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJSON = writer.writeValueAsString(houseDTO);
        String expectedJSON = writer.writeValueAsString(expectedEnvironment);

        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/property/biggest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        assertEquals(expectedJSON, response.getResponse().getContentAsString());
    }

    // Calculate environments square feet
    @Test
    void calculateRoomsSquareFeet() throws Exception {
        RoomResponseDTO environment1 = new RoomResponseDTO();
        environment1.setEnvironment(new RoomDTO("Environment1", 10D, 10D));
        environment1.setSquare_feet(100D);
        RoomResponseDTO environment2 = new RoomResponseDTO();
        environment2.setEnvironment(new RoomDTO("Environment2", 20D, 20D));
        environment2.setSquare_feet(400D);
        RoomResponseDTO environment3 = new RoomResponseDTO();
        environment3.setEnvironment(new RoomDTO("Environment3", 25D, 25D));
        environment3.setSquare_feet(625D);

        List<RoomDTO> environments = new ArrayList<>();
        environments.add(environment1.getEnvironment());
        environments.add(environment2.getEnvironment());
        environments.add(environment3.getEnvironment());

        List<RoomResponseDTO> expectedEnvironments = new ArrayList<>();
        expectedEnvironments.add(environment1);
        expectedEnvironments.add(environment2);
        expectedEnvironments.add(environment3);

        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrict_name("Test");
        districtDTO.setDistrict_price(220D);

        HouseDTO property = new HouseDTO();
        property.setEnvironments(environments);
        property.setProp_name("Test");
        property.setDistrict(districtDTO);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String requestJSON = writer.writeValueAsString(property);
        String expectedJSON = writer.writeValueAsString(expectedEnvironments);

        MvcResult response = mockMvc.perform(
                MockMvcRequestBuilders.post("/calculate/environment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        assertEquals(expectedJSON, response.getResponse().getContentAsString());
    }
}
