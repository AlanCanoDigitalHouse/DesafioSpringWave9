package com.mercadolibre.TuCasitaTasacionesDesafioTesting.integrationTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.HouseRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.RoomRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TuCasitaTasacionesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Save data house - correct case - integration test")
    void saveData() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casa Nueva", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("The property name cannot be null - bad request - integration test")
    void propNameNull() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto(null, "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The property name is empty - bad request - integration test")
    void propNameEmpty() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The property name must start with a capital letter - bad request - integration test")
    void propNameCapitalLetter() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The length of the name cannot exceed 30 characters.- bad request - integration test")
    void propNameMax30Characters() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Esta es mi nueva casa que se llama Azul", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The district name cannot be null- bad request - integration test")
    void districtNameNull() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", null,
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The district name cannot be empty- bad request - integration test")
    void districtNameEmpty() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The district name must start with a capital letter- bad request - integration test")
    void districtNameCapitalLetter() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The length of the district name cannot exceed 45 characters.- bad request - integration test")
    void districtNameMax45Characters() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo Hollywood direccion Guemes 4500 CP 1416",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The environment name cannot be null- bad request - integration test")
    void environmentNameNull() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto(null, 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The environment name is empty - bad request - integration test")
    void environmentNameEmpty() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The environment name must start with a capital letter - bad request - integration test")
    void environmentNameCapitalLetter() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The length of the environment name cannot exceed 30 characters.- bad request - integration test")
    void environmentNameMax30Characters() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Este es el ambiente que se llama cocina", 7.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The length cannot be greater than 33 mts- bad request - integration test")
    void lengthMax33Mts() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 34.0, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The length cannot be less than 0.5 mts- bad request - integration test")
    void lengthMinHalfMetre() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 0.3, 3.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The width cannot be greater than 25 mts- bad request - integration test")
    void widthMax25Mts() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 26.0));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("The width cannot be less than 0.5 mts- bad request - integration test")
    void widthMinHalfMetre() throws Exception {
        HouseRequestDto payloadDto = new HouseRequestDto("Casita", "Palermo",
                new ArrayList<>() {{
                    add(new RoomRequestDto("Cocina", 7.0, 0.3));
                    add(new RoomRequestDto("Living", 3.0, 3.0));
                }});
        String payloadJson = getAsJSON(payloadDto);
        getResultActions(payloadJson).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Endpoint /houseArea integration test ")
    void calculateHouseArea() throws Exception {
        saveData();
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/TuCasitaTasaciones/houseArea"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.houseArea").value(30));
    }

    @Test
    @DisplayName("Endpoint /priceByLocation integration test ")
    void calculatePriceByLocation() throws Exception {
        saveData();
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/TuCasitaTasaciones/priceByLocation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prop_price").value(90000));
    }

    @Test
    @DisplayName("Endpoint /biggestRoom integration test ")
    void calculateBiggestRoom() throws Exception {
        saveData();
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/TuCasitaTasaciones/biggestRoom"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomArea").value(21));
    }

    @Test
    @DisplayName("Endpoint /roomArea integration test ")
    void calculateRoomArea() throws Exception {
        saveData();
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/TuCasitaTasaciones/roomArea"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rooms[0].roomArea").value(21))
                .andExpect(jsonPath("$.rooms[1].roomArea").value(9));
    }

    private String getAsJSON(HouseRequestDto payloadDto) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(payloadDto);
    }

    private ResultActions getResultActions(String payloadJSON) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/TuCasitaTasaciones/saveData")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print());
    }
}
