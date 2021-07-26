package com.example.demo.Integration;

import com.example.demo.DTOs.*;
import com.example.demo.Models.District;
import com.example.demo.Repositories.IDistrictRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerTest {

    private String districtName;
    private DistrictDTO districtDTO;
    private District district;
    private PropertyDetailsDTO expected;
    private PropertyDTO propertyDTO;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IDistrictRepository repository;

    @BeforeEach
    public void initialize(){
        // Names
        String prop_name = "Mansion";
        districtName = "Broadway";
        String environmentName1 = "Biggest";
        String environmentName2 = "Medium";
        String environmentName3 = "Smaller";
        // New district
        districtDTO = new DistrictDTO(districtName, 2.0);
        district = new District(districtName, 2.0);
        // New environments
        EnvironmentDTO environment1 = new EnvironmentDTO(environmentName1, 2.0, 2.0);
        EnvironmentDTO environment2 = new EnvironmentDTO(environmentName2, 2.0, 1.0);
        EnvironmentDTO environment3 = new EnvironmentDTO(environmentName3, 1.0, 1.0);
        // New list of environments
        List<EnvironmentDTO> environmentList = new ArrayList();
        environmentList.add(environment1);
        environmentList.add(environment2);
        environmentList.add(environment3);
        // New environments square meters list
        EnvironmentResponseDTO environmentResponse1 = new EnvironmentResponseDTO(environmentName1, 4.0);
        EnvironmentResponseDTO environmentResponse2 = new EnvironmentResponseDTO(environmentName2, 2.0);
        EnvironmentResponseDTO environmentResponse3 = new EnvironmentResponseDTO(environmentName3, 1.0);
        List<EnvironmentResponseDTO> environmentResponseList = new ArrayList();
        environmentResponseList.add(environmentResponse1);
        environmentResponseList.add(environmentResponse2);
        environmentResponseList.add(environmentResponse3);
        // New property details to compare
        expected = new PropertyDetailsDTO(prop_name, 7.0, 14, environmentResponse1, environmentResponseList);
        propertyDTO = new PropertyDTO(prop_name, districtName, environmentList);
    }

    @Test
    public void shouldReturnDetails() throws Exception {
        ObjectWriter writer =
                new ObjectMapper()
                        .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                        .writer()
                        .withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(propertyDTO);
        when(repository.findDistrictByName(districtName)).thenReturn(district);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/property/details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Mansion"))
                .andExpect(MockMvcResultMatchers.jsonPath("totalSquareMeters").value(7.0))
                .andExpect(MockMvcResultMatchers.jsonPath("totalPrice").value(14.0))
                .andExpect(MockMvcResultMatchers.jsonPath("biggestEnvironment.name").value("Biggest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environmentsSqrMeters[0].name").value("Biggest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environmentsSqrMeters[1].name").value("Medium"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.environmentsSqrMeters[2].name").value("Smaller"));
    }

    @Test
    public void shouldReturnExceptionWhenDistrictDoesNotExist() throws Exception {

        ObjectWriter writer =
                new ObjectMapper()
                        .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                        .writer()
                        .withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(propertyDTO);
        when(repository.findDistrictByName(districtName)).thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/property/details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value(400));
    }

    @Test
    public void shouldReturnExceptionWhenFieldsAreNull() throws Exception {
        propertyDTO.setProp_name(null);
        propertyDTO.setDistrict_name(null);
        propertyDTO.setEnvironments(null);
        ObjectWriter writer =
                new ObjectMapper()
                        .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                        .writer()
                        .withDefaultPrettyPrinter();
        String payloadJson = writer.writeValueAsString(propertyDTO);
        when(repository.findDistrictByName(districtName)).thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/property/details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("prop_name").value("Property name field can not be empty."))
                .andExpect(MockMvcResultMatchers.jsonPath("district_name").value("District name field in Property can not be empty."))
                .andExpect(MockMvcResultMatchers.jsonPath("environments").value("Each property must have at least 1 environment."));
    }

}
