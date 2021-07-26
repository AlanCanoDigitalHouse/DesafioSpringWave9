package com.mercadolibre.calculadorametroscuadrados.integrations;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import util.UtilGenerator;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integrations Test")
public class CalculadoraMetrosCuadradosTest {

    private final ObjectWriter writer;

    @Autowired
    private MockMvc mockMvc;

    public CalculadoraMetrosCuadradosTest() {
        this.writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }


    @Test
    public void perfectCaseTest() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHousePerfectCase();
        HouseResponseDTO responseDTO = UtilGenerator.genHouseResponseExpected(payloadDTO);

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String responseJSON = writer.writeValueAsString(responseDTO);

        MvcResult  mvcResult = this.mockMvc.perform(post("/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payloadJSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andReturn();

        Assertions.assertEquals(responseJSON,  mvcResult.getResponse().getContentAsString());
    }

    //Validation input test
    @Test
    public void namePropertyEmpty() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseNameEmpty();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "El nombre de la propiedad no puede estar vacío.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.prop_name").value(messageExpected));
    }

    @Test
    public void namePropertyCapitalize() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseNameNonCapitalize();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "El nombre de la propiedad debe comenzar con mayúscula.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.prop_name").value(messageExpected));
    }

    @Test
    public void namePropertySizeGT30() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseNameSizeGT30();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "La longitud del nombre no puede superar los 30 caracteres.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.prop_name").value(messageExpected));
    }

    @Test
    public void districtNameEmpty() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseAddressEmpty();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "El barrio no puede estar vacío.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.district_name").value(messageExpected));
    }

    @Test
    public void districtNameSizeGT45() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseAddressSizeGT45();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "La longitud del barrio no puede superar los 45 caracteres.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.district_name").value(messageExpected));
    }

    @Test
    public void districtPriceGT4000() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseDistrictPriceGT4000();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "El precio máximo permitido por metro cuadrado no puede superar los 4000 USD";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.district_price").value(messageExpected));
    }

    @Test
    public void districtPriceNotMatchDb() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseDistrictPriceNotMatchDb();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "EL precio ingresado no coincide con el precio de la Db.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message.district_price").value(messageExpected));
    }

    @Test
    public void districtPriceNull() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseNullDistrictPrice();

        String payloadJSON = writer.writeValueAsString(payloadDTO);
        String messageExpected = "El precio de un barrio no puede estar vacío.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest() )
                .andExpect(jsonPath("$.message.district_price").value(messageExpected));
    }


    @Test
    public void emptyEnviroments() throws Exception {
        HouseDTO payloadDTO = UtilGenerator.genHouseEmptyRooms();

        String payloadJSON = writer.writeValueAsString(payloadDTO);

        String messageExpected = "La propiedad debe tener al menos 1 habitación.";

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message.enviroments").value(messageExpected));
    }


}
