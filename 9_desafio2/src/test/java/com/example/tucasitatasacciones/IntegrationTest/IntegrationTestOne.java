package com.example.tucasitatasacciones.IntegrationTest;

import com.example.tucasitatasacciones.Utils.GenerateProperty;
import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertySquareMetersResponseDTO;
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
public class IntegrationTestOne {
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
    @DisplayName("Integration Test For Square Meters")
    public void SquareMetersIntegration() throws Exception {
        PropertySquareMetersResponseDTO expected = new PropertySquareMetersResponseDTO(300);
        PropertyRequestDTO property = GenerateProperty.getValidProperty();
        property.setEnvironments(GenerateProperty.getEnvironmentsSquareMeters());
        String result = mockMvc.perform(post("/calculate/squareMeters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(property)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertThat(result).isEqualTo(objectMapper.writeValueAsString(expected));
    }
}