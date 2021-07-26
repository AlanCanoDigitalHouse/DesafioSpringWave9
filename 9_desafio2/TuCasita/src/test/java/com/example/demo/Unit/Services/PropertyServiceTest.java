package com.example.demo.Unit.Services;

import com.example.demo.DTOs.*;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Models.District;
import com.example.demo.Models.Property;
import com.example.demo.Repositories.IDistrictRepository;
import com.example.demo.Services.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @Mock
    IDistrictRepository repository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PropertyService service;

    private ModelMapper localMapper;
    private String districtName;
    private DistrictDTO districtDto;
    private PropertyDetailsDTO expected;
    private PropertyDTO estateDto;


    @BeforeEach
    public void init(){
        localMapper = new ModelMapper();
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
    public void shouldCalculateCorrectly() throws CustomExceptionHandler {

        // Arrange
        District district = localMapper.map(districtDto, District.class);
        when(modelMapper.map(estateDto, Property.class)).thenReturn(localMapper.map(estateDto, Property.class));
        when(repository.findDistrictByName(districtName)).thenReturn(district);

        // Act
        PropertyDetailsDTO received = service.getDetails(estateDto);

        // Assert
        Assertions.assertEquals(expected, received);
    }

    @Test
    public void shouldThrowExceptionWhenDistrictNameIsNotFound() throws CustomExceptionHandler {

        // Arrange
        when(repository.findDistrictByName(districtName)).thenReturn(null);

        when(modelMapper.map(estateDto, Property.class)).thenReturn(localMapper.map(estateDto, Property.class));
        // Act
        assertThrows(NotFoundException.class, () -> service.getDetails(estateDto));
    }

}

