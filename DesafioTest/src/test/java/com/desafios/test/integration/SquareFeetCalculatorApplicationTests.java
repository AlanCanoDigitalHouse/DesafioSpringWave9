package com.desafios.test.integration;

import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.dtos.EnvironmentDTO;
import com.desafios.test.dtos.request.HouseRequestDTO;
import com.desafios.test.dtos.response.HouseResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SquareFeetCalculatorApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectWriter writer = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer();

    @Test
    @DisplayName("Integration - Test with no district parameter")
    public void noDistrictParametersTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        house.setDistrict(null);
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(post("/calculate/squareFeet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message.district").value("El distrito no puede ser nulo."));
    }

    @Test
    @DisplayName("Integration - Test with no existing district parameter")
    public void noExistingDistrictParametersTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        house.getDistrict().setDistrict_name("Barrio inexistente");
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(post("/calculate/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value("El distrito no existe"));
    }

    @Test
    @DisplayName("Integration - Test with no environments parameter")
    public void noDEnvironmentsParametersTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(post("/calculate/squareFeet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message.environments").value("La lista de habitaciones no puede estar vacía."));
    }

    @Test
    @DisplayName("Integration - Test calculating total square feet for a house with one room")
    public void calculateTotalHouseSquareFeetForHouseWithOneRoomTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(post("/calculate/squareFeet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.squareFeet").value("30.0"));
    }

    @Test
    @DisplayName("Integration - Test calculating total square feet for a house with multiple rooms")
    public void calculateTotalHouseSquareFeetForHouseWithMultipleRoomTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addFourTestRooms(house);
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(
                post("/calculate/squareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(jsonPath("$.squareFeet").value("59.0"));
    }

    @Test
    @DisplayName("Integration - Test calculating house price for a district")
    public void calculateTotalHousePriceTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addFourTestRooms(house);
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(
                post("/calculate/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(jsonPath("$.price").value(59000));
    }

    @Test
    @DisplayName("Integration - Test calculating bigger room in a house with one room")
    public void calculateBiggestRoomForHouseWithOneRoomTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        String payloadJson = this.writer.writeValueAsString(house);
        HouseResponseDTO houseResponse = new HouseResponseDTO();
        EnvironmentDTO biggestRoom = house.getEnvironments().get(0);
        houseResponse.setBiggest(biggestRoom);
        //act & assert
        this.mockMvc.perform(post("/calculate/biggestRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.biggest.environment_name").value(biggestRoom.getEnvironment_name()));
    }

    @Test
    @DisplayName("Integration - Test calculating bigger room in a house with multiple rooms")
    public void calculateBiggestRoomForHouseWithMultipleRoomsTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addFourTestRooms(house);
        String payloadJson = this.writer.writeValueAsString(house);
        HouseResponseDTO houseResponse = new HouseResponseDTO();
        EnvironmentDTO biggestRoom = house.getEnvironments().get(0);
        houseResponse.setBiggest(biggestRoom);
        //act & assert
        this.mockMvc.perform(post("/calculate/biggestRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.biggest.environment_name").value(biggestRoom.getEnvironment_name()));
    }

    @Test
    @DisplayName("Integration - Test calculating square feet for all rooms in a house with one room")
    public void calculateRoomsSquareFeetForHouseWithOneRoomTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addTestRoom(house, "Monoambiente", 6.0, 5.0);
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(
                post("/calculate/roomSquareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(jsonPath("$.environments[0].squareFeet").value(30.0));
    }

    @Test
    @DisplayName("Integration - Test calculating square feet for all rooms in a house with multiple rooms")
    public void calculateRoomsSquareFeetForHouseWithMultipleRoomsTest() throws Exception {
        //arrange
        HouseRequestDTO house = setTestHouse();
        addFourTestRooms(house);
        String payloadJson = this.writer.writeValueAsString(house);
        //act & assert
        this.mockMvc.perform(
                post("/calculate/roomSquareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.environments[0].squareFeet").value(25.0))
                .andExpect(jsonPath("$.environments[1].squareFeet").value(14.0))
                .andExpect(jsonPath("$.environments[2].squareFeet").value(15.0))
                .andExpect(jsonPath("$.environments[3].squareFeet").value(5.0));
    }

    private HouseRequestDTO setTestHouse() {
        return new HouseRequestDTO("La campesina", new DistrictDTO("Palermo", 1000.0), new ArrayList<>());
    }

    private void addFourTestRooms(HouseRequestDTO house) {
        addTestRoom(house, "Dormitorio principal", 5.0, 5.0);
        addTestRoom(house, "Cocina", 3.5, 4.0);
        addTestRoom(house, "Living comerdor", 5.0, 3.0);
        addTestRoom(house, "Escritorio", 2.0, 2.5);
    }

    private void addTestRoom(HouseRequestDTO house, String name, Double width, Double length) {
        List<EnvironmentDTO> environments = house.getEnvironments();
        environments.add(new EnvironmentDTO(name, width, length));
        house.setEnvironments(environments);
    }
}
