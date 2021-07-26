package com.mercadolibre.calculadorametroscuadrados.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoadDistrictsTest {

    @Autowired
    private MockMvc mockMvc;

    //se carga una lista con un distrito por payload y se responde un mensaje de OK
    @Test
    void loadOneDistrict() throws Exception {
        String request = "{ \"districts\" : [ " +
                " \"demo\" " +
                "]}";

        this.mockMvc.perform(
                post("/loadDistricts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Lista de distritos cargada con exito"));
    }

    //se carga una lista con multiples distritos por payload y se responde un mensaje de OK
    @Test
    void loadMultipleDistricts() throws Exception {
        String request = "{ \"districts\" : [ " +
                " \"demo\" , \"demo2\" , \"demo3\" " +
                "]}";

        this.mockMvc.perform(
                post("/loadDistricts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Lista de distritos cargada con exito"));
    }
}
