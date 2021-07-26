package com.mercadolibre.calculadorametroscuadrados;

import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test - API calculadora metros cuadrados ")
class CalculadoraMetrosCuadradosApplicationTests {

    @InjectMocks
    CalculadoraMetrosCuadradosApplication application;

    @Test
    @DisplayName("App main")
    void main() {
//        CalculadoraMetrosCuadradosApplication.main(new String[] {});
    }
}
