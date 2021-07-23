package com.mercadolibre.calculadorametroscuadrados.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import util.UtilGenerator;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
    public void testPerfectCase() throws Exception {
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
}
