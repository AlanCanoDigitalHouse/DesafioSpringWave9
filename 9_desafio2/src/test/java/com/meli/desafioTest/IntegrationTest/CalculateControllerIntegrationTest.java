package com.meli.desafioTest.IntegrationTest;

import com.meli.desafioTest.Dtos.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafioTest.TestDataUtilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateControllerIntegrationTest {

    private final String NULLNAMEMESSAGE = "El nombre de la propiedad no puede estar vacío.";
    private final String BADDISTRICTPRICE = "El precio minimo permitido por metro cuadrado no puede ser inferior a los 100 U$S.";
    private final String BADENVIRONMENTNAME = "La longitud del nombre no puede superar los 30 caracteres.";

    @Autowired
    private MockMvc mockMvc;

    //Valida que el metodo calculate en el controlador se ejecute correctamente
    // y traiga datos calculados
    @Test
    public void SuccessCalculate() throws Exception {
        HouseDTO input = TestDataUtilities.getHouseWithAllAndCorrectData();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String inputJson = writer.writeValueAsString(input);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.prop_name").value(input.getProp_name()))
                .andExpect(jsonPath("$.biggest").isNotEmpty());
    }

    //Valida que si los datos de entrada de la casa no son correctos
    // se dispare la excepcion correspondiente
    @Test
    public void CalculateDistrictNullException() throws Exception {
        HouseDTO input = TestDataUtilities.getHouseWithNullName();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String inputJson = writer.writeValueAsString(input);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value(NULLNAMEMESSAGE));
    }

    //Valida que si los datos de entrada del barrio no son correctos
    // se dispare la excepcion correspondiente
    @Test
    public void CalculateDistrictPriceException() throws Exception {
        HouseDTO input = TestDataUtilities.getHouseWithoutDistrictPrice();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String inputJson = writer.writeValueAsString(input);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value(BADDISTRICTPRICE));
    }

    ////Valida que si los datos de entrada de la habitacion no son correctos
    // se dispare la excepcion correspondiente
    @Test
    public void CalculateEnveronmentNameException() throws Exception {
        HouseDTO input = TestDataUtilities.getHouseWithBadEnvironmentName();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        String inputJson = writer.writeValueAsString(input);

        this.mockMvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value(BADENVIRONMENTNAME));
    }


}
