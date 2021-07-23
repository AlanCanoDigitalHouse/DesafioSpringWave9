package com.squareMeter.unit.service;

import com.squareMeter.dto.request.property.PropertyEnvironmentRequestDTO;
import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.service.PropertyService;
import com.squareMeter.testUtils.creators.Property;
import com.squareMeter.utils.Mapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PropertyServiceTest {

    private PropertyService propertyService;

    @BeforeEach
    void initUseCase() {
        propertyService = new PropertyService(new Mapper());
    }
    @Test
    @DisplayName("A Result 1: Total square meters is correct")
    public void getHouseTotalSquareMeters(){
        PropertyRequestDTO data = Property.getValidProperty();
        List<PropertyEnvironmentRequestDTO> environments = new ArrayList<>();
        //Total = squareBathroom + squareRoom1 + squareRoom2 = (10*10)+(10*10)+(10*10) = 300
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Bathroom").environment_length(10.0).environment_width(10.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Room 1").environment_length(10.0).environment_width(10.0).build());
        environments.add(PropertyEnvironmentRequestDTO.builder().environment_name("Room 3").environment_length(10.0).environment_width(10.0).build());
        data.setEnvironments(environments);

        PropertySquareMetersResponseDTO totalMetersCalculated = propertyService.getHouseTotalSquareMeters(data);
        Assertions.assertThat(totalMetersCalculated.getSquareMeters()).isEqualTo(300.0);
    }
}
