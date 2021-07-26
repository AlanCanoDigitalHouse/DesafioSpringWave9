package com.example.tucasitatasacciones.IntegrationTest;

import com.example.tucasitatasacciones.Utils.GenerateProperty;
import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertyValueDTO;
import com.example.tucasitatasacciones.service.PropertyService;
import com.example.tucasitatasacciones.utils.RunAtStart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestTwo {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PropertyService propertyService;

    @BeforeEach
    public void resetDB() {
        RunAtStart.refresh();
    }
    @Test
    @DisplayName("Se prueba que coincida el valor del cuarto mas grande")
    public void BiggestRoomTestIntegration() throws Exception {
        EnvironmentResponseDTO expected = EnvironmentResponseDTO.builder().environment_name("Kitchen").environment_width(25.0).environment_length(25.0).build();
        PropertyRequestDTO data = GenerateProperty.getValidProperty();
        data.setEnvironments(GenerateProperty.getKitchenIsBiggerRoom());
        String result = mockMvc.perform(post("/calculate/biggerEnvironment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }

}
