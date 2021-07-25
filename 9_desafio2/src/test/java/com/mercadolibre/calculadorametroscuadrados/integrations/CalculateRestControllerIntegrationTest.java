package com.mercadolibre.calculadorametroscuadrados.integrations;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.units.util.UtilTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Test - CalculateRestController")
public class CalculateRestControllerIntegrationTest {

    @InjectMocks
    CalculateRestController calculateRestController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test Validar Hello World")
    public void testHellowWorldOutput() throws Exception{
        HouseDTO payloadDTO = UtilTest.loadHouseDTO("Test","Belgrano");
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);


        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isOk());


//        Assertions.assertEquals("application/json" , mvcResult.getResponse().getContentType());
    }

}
