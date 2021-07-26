package com.mercadolibre.calculadorametroscuadrados.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.service.HouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.mercadolibre.calculadorametroscuadrados.utils.ToolsVarias.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    HouseService houseService;


    @Test
    @DisplayName("Endpoint: US-0001")
    public void calculateAreaTest() throws Exception {
        // Start
        /* Payload*/
        HouseTotalSizeRequestDTO payloadDTO = returnHouseDTO();
        /* Response */
        HouseResponseDTO responseDTO = new HouseResponseDTO("Casita", 50D);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseDTOJson = writer.writeValueAsString(responseDTO);


        // Act
        MvcResult mvcResult =
                this.mockMvc.perform(get("/challenge/calculate-area")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        // Assert
        Assertions.assertEquals(responseDTOJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Endpoint: US-0002")
    public void calculatePriceTest() throws Exception {
        // Start
        /* Payload*/
        HousePriceRequestDTO payloadDTO = returnHousePriceDTO();
        /* Response */
        HousePriceResponseDTO responseDTO = new HousePriceResponseDTO("Casita", 50000D);
        houseService.start();


        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseDTOJson = writer.writeValueAsString(responseDTO);


        // Act
        MvcResult mvcResult =
                this.mockMvc.perform(get("/challenge/house-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        // Assert
        Assertions.assertEquals(responseDTOJson, mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("Endpoint: US-0003")
    public void calculateBiggestTest() throws Exception {
        // Start
        /* Payload*/
        HouseDTO payloadDTO = returnSingleHouseDTO();
        /* Response */
        RoomResponseBiggestDTO responseDTO = new RoomResponseBiggestDTO("Comedor", 200D);

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseDTOJson = writer.writeValueAsString(responseDTO);


        // Act
        MvcResult mvcResult =
                this.mockMvc.perform(get("/challenge//biggest-room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        // Assert
        Assertions.assertEquals(responseDTOJson, mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("Endpoint: US-0004")
    public void calculateAllTest() throws Exception {
        // Start
        /* Payload*/
        HouseDTO payloadDTO = returnSingleHouseDTO();
        /* Response */
        HouseSizesResponseDTO responseDTO = returnAllDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(payloadDTO);
        String responseDTOJson = writer.writeValueAsString(responseDTO);


        // Act
        MvcResult mvcResult =
                this.mockMvc.perform(get("/challenge/count-size")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        // Assert
        Assertions.assertEquals(responseDTOJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Endpoint: start")
    public void startTest() throws Exception{
        // Start
        /* Response */
        String responseDTO = "Barrios cargados: Centro, Chacarita, Valle Chico, Villa Reyes y Terrazas del Valle.";

        // Act
        MvcResult mvcResult =
                this.mockMvc.perform(post("/challenge/start"))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        // Assert
        Assertions.assertEquals(responseDTO, mvcResult.getResponse().getContentAsString());
    }

}
