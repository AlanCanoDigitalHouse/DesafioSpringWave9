package com.squareMeter.unit.service;

import com.squareMeter.dto.request.district.DistrictRequestDTO;
import com.squareMeter.dto.request.property.PropertyEnvironmentRequestDTO;
import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.EnvironmentResponseDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.dto.response.PropertyValueDTO;
import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.service.DistrictService;
import com.squareMeter.service.PropertyService;
import com.squareMeter.testUtils.creators.Property;
import com.squareMeter.utils.Mapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;

/*
Units test of the property service

What is tested?
- Logic of calculation of squeare meters of a property
- Logic of calculation of the of a property
- Logic of finding the bigger environment of a property
 */
@ExtendWith(MockitoExtension.class)
public class PropertyServiceUnitTest {
    @Mock
    private DistrictService districtService;
    private PropertyService propertyService;

    @BeforeEach
    void initUseCase() {
        propertyService = new PropertyService(new Mapper(), districtService);
    }

    @Test
    @DisplayName("A Result 1: Validate logic to calculate square meters of a property")
    public void getHouseTotalSquareMeters() throws DistrictNotExistsException {
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

    @Test
    @DisplayName("A Result 2: Validate logic to calculate the price of a property")
    public void getHouseValue() throws DistrictNotExistsException {
        //Only is important that the db confirm that the name exists
        doNothing().when(districtService).districtExists("Test");
        PropertyRequestDTO data = Property.getValidProperty();
        data.setEnvironments(Property.getEnvironmentsSum300SquareMeters());
        data.setDistrict(DistrictRequestDTO.builder().district_name("Test").district_price(1000.0).build());

        PropertyValueDTO out = propertyService.getHouseValue(data);
        Assertions.assertThat(out.getValue()).isEqualTo(300 * 1000);
    }

    @Test
    @DisplayName("A Result 3: Obtain the correct biggest environment")
    public void getBiggerEnvironment() {
        PropertyRequestDTO data = Property.getValidProperty();
        data.setEnvironments(Property.getEnvironmentsBiggerIsBathroom());
        EnvironmentResponseDTO out = propertyService.getBiggerEnvironment(data);
        Assertions.assertThat(out.getEnvironment_name()).isEqualTo("Bathroom");
    }
}
