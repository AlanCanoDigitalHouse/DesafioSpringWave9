package com.squareMeter.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareMeter.controller.CalculatorController;
import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.model.ErrorAttributes;
import com.squareMeter.exception.model.ErrorMessage;
import com.squareMeter.service.PropertyService;
import com.squareMeter.testUtils.creators.Property;
import org.apache.coyote.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
This controller will be tested like this:
- The validation of the output data is correct (de serialization)
- Validation of status code
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
    @DisplayName("A Output 1: Correct output US-0001")
    public void getTotalMeters(){
        when(propertyService.getHouseTotalSquareMeters(any())).thenReturn(new PropertySquareMetersResponseDTO(10));
        ResponseEntity<PropertySquareMetersResponseDTO> out = controller.getTotalMeters(Property.getValidProperty());
        Assertions.assertThat(out.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(out.getBody().getSquareMeters()).isEqualTo(10);
    }

}
