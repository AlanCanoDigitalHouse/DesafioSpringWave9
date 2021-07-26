package com.mercado_libre.bootcamp.desafio2.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.EnvironmentDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /calculate/house - Name with lowercoase returns 400")
    void nameWithLowercase_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("una casa en minuscula");

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['name']").value("El nombre de la propiedad debe comenzar con mayúscula."))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - Name with more than 30 characters returns 400")
    void nameWithMoreThan30Characters_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Una casa con un nombre muy largo para poder probar la validación");

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['name']").value("El nombre de la propiedad no puede superar los 30 caracteres."))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - District null returns 400")
    void districtNull_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Una casa");

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['district']").value("El barrio no puede ser nulo."))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - District with more than 45 characters returns 400")
    void districtWithMoreThan45Characters_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Una casa");
        houseRequest.setDistrict(new DistrictDTO("Un barrio con un nombre muy extenso para poder probar la validación", 2000.00));

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['district.districtName']").value("La longitud del barrio no puede superar los 45 caracteres."))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - District with negative price returns 400")
    void districtWithNegativePrice_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Una casa");
        houseRequest.setDistrict(new DistrictDTO("Barrio", -2000.00));

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['district.districtPrice']").value("El valor minimo de una propiedad debe ser 0.1"))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - District with a price higher than 4000 returns 400")
    void districtWithPriceHigherThan4000_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Una casa");
        houseRequest.setDistrict(new DistrictDTO("Barrio", 5000.00));

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['district.districtPrice']").value("El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S."))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - Environment with null name returns 400")
    void environmentWithNullName_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Una casa");
        houseRequest.setDistrict(new DistrictDTO("Barrio", 2000.00));
        houseRequest.setEnvironments(Collections.singletonList(new EnvironmentDTO("", 5.0, 5.0)));

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages['environments[0].name']").value("El nombre del ambiente no puede estar vacío."))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - Neighborhood not found in repository returns 400")
    void neighborhoodNotFoundInRepository_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Casa no existente");
        houseRequest.setDistrict(new DistrictDTO("Barrio no existente", 2000.00));
        houseRequest.setEnvironments(Collections.singletonList(new EnvironmentDTO("Room", 5.0, 5.0)));

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("No se encontró el barrio [Barrio no existente]"))
                .andExpect(jsonPath("$.message").value("Por favor, ingresá un barrio válido"))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - Bad JSON content returns 400")
    void httpMessageNotReadableException_badRequest() throws Exception {

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{'name':12}]"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("El formato del Body ingresado no es correcto"))
                .andReturn();
    }

    @Test
    @DisplayName("GET /calculate/house - Bad price returns 400")
    void neighborhoodNotFoundException_badRequest() throws Exception {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setName("Casa no existente");
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));
        houseRequest.setEnvironments(Collections.singletonList(new EnvironmentDTO("Room", 5.0, 5.0)));

        String payloadJSON = getObjectWriter().writeValueAsString(houseRequest);

        this.mockMvc.perform(get("/calculate/house")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Por favor, ingresá un precio válido"))
                .andExpect(jsonPath("$.error").value("El precio ingresado [2000.0] no coincide con el precio almacenado en la base de datos."))
                .andReturn();
    }

    private ObjectWriter getObjectWriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }
}
