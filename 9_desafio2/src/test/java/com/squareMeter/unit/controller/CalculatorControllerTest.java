package com.squareMeter.unit.controller;

import com.squareMeter.controller.CalculatorController;
import com.squareMeter.dto.response.PropertyValueDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.service.PropertyService;
import com.squareMeter.testUtils.creators.Property;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/*
This controller will be tested like this:
- Validation of http codes on success inputs

Note: becouse the context spring is not inicialized @Valid dont work, this validations is in integrations testings
 */

@SuppressWarnings("ALL")
@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {
    @Mock
    PropertyService propertyService;
    @InjectMocks
    CalculatorController controller;

    /**************************************************/
    /****** Data output validation and http codes ******/
    /**************************************************/
    /* Section centered in validation of http codes */

    @Test
    @DisplayName("A Output 1: Validate http code, in success input US-0001")
    public void getTotalMeters() throws DistrictNotExistsException {
        when(propertyService.getHouseTotalSquareMeters(any())).thenReturn(new PropertySquareMetersResponseDTO(10));
        ResponseEntity<PropertySquareMetersResponseDTO> out = controller.getTotalMeters(Property.getValidProperty());
        Assertions.assertThat(out.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(out.getBody().getSquareMeters()).isEqualTo(10);
    }

    @Test
    @DisplayName("A Output 2: Validate http code, in success input US-0002")
    public void getValueProperty() throws DistrictNotExistsException {
        when(propertyService.getHouseValue(any())).thenReturn(PropertyValueDTO.builder().value(10).build());
        ResponseEntity<PropertyValueDTO> out = controller.getPrice(Property.getValidProperty());
        Assertions.assertThat(out.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(out.getBody().getValue()).isEqualTo(10);
    }

}
