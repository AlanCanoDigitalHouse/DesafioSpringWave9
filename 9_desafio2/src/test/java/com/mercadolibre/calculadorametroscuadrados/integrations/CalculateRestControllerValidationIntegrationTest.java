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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Test - endpoint: /calculate - Validations prop_name district")
public class CalculateRestControllerValidationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    List<EnvironmentDTO> environmentDTOS;
    String error = "Method argument not valid error.";

    @BeforeEach @AfterEach
    void restartListEnviroments(){
        environmentDTOS = new ArrayList<>();
    }


    @Test
    @DisplayName(" IT 8 - district not found in repository")
    public void calculate_district_not_found() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("NotFound",500.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("El barrio ingresado no existe en nuestro repositorio"))
                .andReturn();
    }

    @Test
    @DisplayName("IT 9 - prop_name not null")
    public void calculate_prop_mame_null() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",500.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO(null,districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andExpect(jsonPath("$.message.prop_name").value("El nombre de la propiedad no puede estar vacío."))
                .andReturn();

    }

    @Test
    @DisplayName("IT 10 - prop_name capital letter")
    public void calculate_prop_mame_capital_letter() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",500.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("aabcde",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andExpect(jsonPath("$.message.prop_name").value("El nombre de la propiedad debe comenzar con mayúscula."))
                .andReturn();

    }

    @Test
    @DisplayName("IT 11 - prop_name longer")
    public void calculate_prop_mame_long() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("Belgrano",500.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Taumatawhakatangihangak oauauotamateaturipukaka pikimaungahoronukupokaiwhe nua kitanatahu",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andExpect(jsonPath("$.message.prop_name").value("La longitud del nombre no puede superar los 30 caracteres."))
                .andReturn();

    }

    @Test
    @DisplayName("IT 12 - district null")
    public void calculate_district_null() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",null,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // action
        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value(error))
                .andExpect(jsonPath("$.message.district").value("El parámetro 'district' no puede estar estar vacío."));

    }

    @Test
    @DisplayName("IT 13 - district.district_name null district.district_price null")
    public void calculate_district_price_null() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO(null,null);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Taumatawhakatangihangak",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" + UtilTest.getValidation("district.district_price","El precio de un barrio no puede estar vacÃ\u00ADo.")+","+UtilTest.getValidation("district.district_name","El barrio no puede estar vacÃ\u00ADo.")+"}}";
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
    @DisplayName("IT 14 - district.district_price longer")
    public void calculate_district_price_longer() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO(" Belgrano",4000.10);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" + UtilTest.getValidation("district.district_price","El precio mÃ¡ximo permitido por metro cuadrado no puede superar los 4000 U$S.")+"}}";
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
    @DisplayName("IT 15 - district.district_name longer")
    public void calculate_district_name_longer() throws Exception{
        // prepare payload
        environmentDTOS.add(UtilTest.loadBadEnvironmentDTO("Hab1",5.20,6.0));
        DistrictDTO districtDTO = UtilTest.loadBadDistrictDTO("TestTestTestTestTestTestTest Belgrano TestTestTestTestTestTestTestTestTestTestTestTestTestTest",4000.0);
        HouseDTO payloadDTO = UtilTest.loadBadHouseDTO("Test",districtDTO,environmentDTOS);
        String payloadJSON = UtilTest.writeValueAsString(payloadDTO);
        // responseString
        String expected = "{\"status\":400,\"error\":\"Method argument not valid error.\",\"message\":{" + UtilTest.getValidation("district.district_name","La longitud del barrio no puede superar los 45 caracteres.")+"}}";
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
