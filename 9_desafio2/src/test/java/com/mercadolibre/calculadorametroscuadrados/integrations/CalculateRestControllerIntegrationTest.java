package com.mercadolibre.calculadorametroscuadrados.integrations;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.units.util.UtilTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Test - endpoint: /calculate - Valid payload")
public class CalculateRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("IT 1 - prop_name")
    public void calculate_prop_mame() throws Exception{
        // payload load
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        MvcResult mvcResult= this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prop_name").value("Test"))
                .andReturn();
        // assert
        assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("IT 2 - environments[i].squareFeet")
    public void calculate_environments_squareFeet() throws Exception{
        // payload load
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prop_name").value("Test"))
                .andExpect(jsonPath("$.environments[0].squareFeet").value(144.0))
                .andExpect(jsonPath("$.environments[1].squareFeet").value(77.0))
                .andExpect(jsonPath("$.environments[2].squareFeet").value(60.0));
    }

    @Test
    @DisplayName("IT 3 - squareFeet - 1")
    public void calculate_squareFeet() throws Exception{
        // payload load
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value(281.0));

    }
    @Test
    @DisplayName("IT 4 - squareFeet - 2")
    public void calculate_district() throws Exception{
        // payload load
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squareFeet").value(281));
    }

    @Test
    @DisplayName("IT 5 - price")
    public void calculate_price() throws Exception{
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(112400.0));

    }

    @Test
    @DisplayName("IT 6 - biggest")
    public void calculate_biggest() throws Exception{
        // payload load
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.biggest.environment_name").value("Hab1"))
                .andExpect(jsonPath("$.biggest.environment_width").value(12.0))
                .andExpect(jsonPath("$.biggest.environment_length").value(12.0))
                .andExpect(jsonPath("$.biggest.squareFeet").value(144.0));
    }

    @Test
    @DisplayName("IT 7 - all response")
    public void calculate_response() throws Exception{
        // payload load
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // response load
        HouseResponseDTO responseDTO = UtilTest.loadHouseResponseDTO(payloadDTO);
        String responseJSON = UtilTest.writeValueAsStringWhitoutPretty(responseDTO);
        // action
        MvcResult mvcResult =
            this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        // assert
        Assertions.assertEquals(responseJSON,mvcResult.getResponse().getContentAsString());
    }


}
