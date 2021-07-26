package com.example.demo.Unit.Controllers;

import com.example.demo.Controllers.PropertyController;
import com.example.demo.DTOs.*;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Services.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Assert;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    PropertyService service;

    @InjectMocks
    PropertyController controller;

    private String districtName;
    private DistrictDTO districtDto;
    private PropertyDetailsDTO expected;
    private PropertyDTO estateDto;

    @BeforeEach
    public void initialize() {
        // Names
        String name = "Mansion";
        districtName = "Broadway";
        String environmentName1 = "Biggest";
        String environmentName2 = "Medium";
        String environmentName3 = "Smaller";
        // New district
        districtDto = new DistrictDTO(districtName, 2.0);
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
        List<EnvironmentResponseDTO> environmentResDtosList = new ArrayList();
        environmentResDtosList.add(environmentResponse1);
        environmentResDtosList.add(environmentResponse2);
        environmentResDtosList.add(environmentResponse3);
        // New property details to compare
        expected = new PropertyDetailsDTO(name, 7.0, 14, environmentResponse1, environmentResDtosList);
        estateDto = new PropertyDTO(name, districtName, environmentList);
    }

    @Test
    public void shouldCallDetailsAndReturnRightResponse() throws CustomExceptionHandler {
        when(service.getDetails(estateDto)).thenReturn(expected);
        ResponseEntity<PropertyDetailsDTO> received = controller.getAssessment(estateDto);
        verify(service, times(1)).getDetails(estateDto);
        Assert.assertEquals(HttpStatus.OK, received.getStatusCode());
        Assert.assertEquals(expected, received.getBody());
    }

}
