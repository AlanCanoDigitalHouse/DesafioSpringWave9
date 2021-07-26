package com.meli.tucasita.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.tucasita.InfoTestGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Tet - Tu casita Service Test")
public class TuCasitaControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper;

    @Test
    public void testGetInfoHouse() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String casaPeticionCorrecta =ow.writeValueAsString(InfoTestGenerator.generatesCorrectTestInfo());
        String expectedResponse = ow.writeValueAsString(InfoTestGenerator.generateCorrectResponse());

        mvc.perform(post("/casa/calcular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(casaPeticionCorrecta))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}
