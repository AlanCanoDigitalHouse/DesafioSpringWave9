package com.mercadolibre.calculadorametroscuadrados.integrations;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.units.util.UtilTest;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
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
@DisplayName("Integration Test - endpoint: /calculate - Validations environment CalculateRestController")
public class CalculateRestControllerValidationEnvironmentIntegrationTest {



    @Autowired
    private MockMvc mockMvc;

    List<EnvironmentDTO> environmentDTOS;
    String error = "Method argument not valid error.";

    @BeforeEach
    @AfterEach
    void restartListEnviroments(){
        environmentDTOS = new ArrayList<>();
    }


    @Test
    @DisplayName("IT 16 - environments not null")
    public void calculate_prop_mame_Upper() throws Exception{
        // prepare payload
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",500.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Belgrano",districtDTO,null);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andExpect(jsonPath("$.message.environments").value("El parámetro 'environments' no puede estar vacío."));

    }


    @Test
    @DisplayName("IT 17 - environment_name environment_length environment_width null")
    public void calculate_environment_all_null() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO(null,null,null));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",4000.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" +
                UtilTest.getValidation("environments[0].environment_name","El nombre del ambiente no puede estar vacÃ\u00ADo.")+ ","+
                UtilTest.getValidation("environments[0].environment_length","El largo del ambiente no puede estar vacÃ\u00ADo.")+ ","+
                UtilTest.getValidation("environments[0].environment_width","El ancho del ambiente no puede estar vacÃ\u00ADo.")
                +"}}";
        // action
        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andReturn();
        // assert
        Assertions.assertEquals(expected,mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("IT 18 - environment_name environment_length environment_width null - 2")
    public void calculate_environment_all_null_2() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO(null,12.8,15.87));
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",null,15.8));
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab2",18.0,null));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",4000.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" +
                UtilTest.getValidation("environments[0].environment_name","El nombre del ambiente no puede estar vacÃ\u00ADo.")+ ","+
                UtilTest.getValidation("environments[1].environment_width","El ancho del ambiente no puede estar vacÃ\u00ADo.")+ ","+
                UtilTest.getValidation("environments[2].environment_length","El largo del ambiente no puede estar vacÃ\u00ADo.")
                +"}}";
        // action
        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andReturn();
        // assert
        Assertions.assertEquals(expected,mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("IT 19 - environment_name environment_length environment_width longer")
    public void calculate_environment_all_longer() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest",30.0,40.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",4000.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" +
                UtilTest.getValidation("environments[0].environment_name","La longitud del nombre no puede superar los 30 caracteres.")+ ","+
                UtilTest.getValidation("environments[0].environment_length","El mÃ¡ximo largo permitido por propiedad es de 33 mts.")+ ","+
                UtilTest.getValidation("environments[0].environment_width","El mÃ¡ximo ancho permitido por propiedad es de 25 mts.")
                +"}}";
        // action
        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andReturn();
        // assert
        Assertions.assertEquals(expected,mvcResult.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("IT 20 - environment_name environment_length environment_width longer")
    public void calculate_environment_name_Upper() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("hab1",15.0,20.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",4000.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" +
                UtilTest.getValidation("environments[0].environment_name","El nombre del ambiente debe comenzar con mayÃºscula.")
                +"}}";
        // action
        MvcResult mvcResult = this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andReturn();
        // assert
        Assertions.assertEquals(expected,mvcResult.getResponse().getContentAsString());
    }

}


