package com.example.desafiotesting.unit.service;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.PropertyResponseDTO;
import com.example.desafiotesting.services.CalculateService;
import com.example.desafiotesting.services.DistrictService;
import com.example.desafiotesting.unit.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    DistrictService districtService;

    @InjectMocks
    CalculateService service;

    @Test
    @DisplayName("Calculate test")
    void calculateTest() throws Exception {
        //arrange
        PropertyDTO payload = TestUtilsGenerator.getValidProperty();

        //mocks
        DistrictDTO districtMock = new DistrictDTO(
                "Belgrano",
                1100.0
        );
        PropertyResponseDTO expected = new PropertyResponseDTO(payload);
        expected.setBiggest(
                new EnvironmentDTO(
                        "Kitchen",
                        7.0,
                        5.0
                )
        );
        expected.setSquareMetter(119.0);
        expected.setPrice(130900.0);
        when(districtService.getDistrictByName("Belgrano")).thenReturn(districtMock);

        //act
        PropertyResponseDTO current = service.calculate(payload);

        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Calculate Error Invalid Price Test")
    void calculateErrorInvalidPriceTest() throws Exception {
        //arrange
        PropertyDTO payload = TestUtilsGenerator.getValidProperty();
        //Setting an invalid price (Price doesn't have to be higher than 4500.0)
        payload.getDistrict().setDistrict_price(7777.0);
        //mocks
        DistrictDTO districtMock = new DistrictDTO(
                "Belgrano",
                1100.0
        );
        when(districtService.getDistrictByName("Belgrano")).thenReturn(districtMock);
        //act
        Assertions.assertThrows(Exception.class, () -> service.calculate(payload));
    }
}
