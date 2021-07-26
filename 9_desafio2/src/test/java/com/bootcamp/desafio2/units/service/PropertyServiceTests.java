package com.bootcamp.desafio2.units.service;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import com.bootcamp.desafio2.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTests {

    @Mock
    DistrictService districtService;

    @InjectMocks
    PropertyService propertyService;

    @Test()
    @DisplayName("Calculate total area with default property")
    public void calculateTotalAreaDefaultPropertyTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        Mockito.when(districtService.neighborhoodExist(Mockito.anyString(), Mockito.anyDouble())).thenReturn(true);

        // act
        ResponseDto result = propertyService.calculatePrice(propertyDto);

        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(Mockito.anyString(), Mockito.anyDouble());
        assertEquals(result.getTotalArea(), 25.0);
    }

    @Test()
    @DisplayName("Disctrict not fount exception in calculate price with bad property")
    public void calculateBadPropertyTest() throws DistrictNotFoundException, IOException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getBadPropertyRequest();
        Mockito.when(districtService.neighborhoodExist(Mockito.anyString(), Mockito.anyDouble())).thenReturn(false);

        // assert
        Assertions.assertThrows(DistrictNotFoundException.class,() ->  propertyService.calculatePrice(propertyDto));
    }

    @Test()
    @DisplayName("Calculate with default property")
    public void calculateDefaultPropertyTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        Mockito.when(districtService.neighborhoodExist(Mockito.anyString(), Mockito.anyDouble())).thenReturn(true);

        // act
        ResponseDto result = propertyService.calculatePrice(propertyDto);

        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(Mockito.anyString(), Mockito.anyDouble());
        assertEquals(result.getPropertyPrice(), 27500.0);
    }

    @Test()
    @DisplayName("ErrorMessage in calculate with bad environment property")
    public void calculateErrorMessageTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        propertyDto.getEnvironments().get(0).setEnvironment_width(null);
        Mockito.when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrict_name(), propertyDto.getDistrict().getDistrict_price())).thenReturn(true);

        // assert
        Assertions.assertThrows(ErrorMessage.class, () -> propertyService.calculatePrice(propertyDto));
    }

    @Test()
    @DisplayName("IOException in calculate with default property")
    public void calculateIOExceptionTest() throws IOException {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getBadPropertyRequest();
        Mockito.when(districtService.neighborhoodExist(Mockito.anyString(), Mockito.anyDouble())).thenThrow(IOException.class);

        // assert
        Assertions.assertThrows(IOException.class,() ->  propertyService.calculatePrice(propertyDto));
    }

    @Test()
    @DisplayName("Calculate bigger environment")
    public void calculateBiggerEnvironmentTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        ResponseDto responseDto = TestUtilsGenerator.getDefaultPropertyResponse();

        String expextedEnvironment = propertyDto.getEnvironments().stream().max(
                                    Comparator.comparingDouble(o -> (o.getEnvironment_width()*o.getEnvironment_length())))
                                    .get().getEnvironment_name();

        Mockito.when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrict_name(), propertyDto.getDistrict().getDistrict_price())).thenReturn(true);

        // act
        ResponseDto result = propertyService.calculatePrice(propertyDto);

        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(propertyDto.getDistrict().getDistrict_name(), propertyDto.getDistrict().getDistrict_price());
        Assertions.assertAll(() -> assertEquals(responseDto, result),
                             () -> assertEquals(expextedEnvironment, result.getBiggerEnvironment()));
    }

    @Test()
    @DisplayName("Calculate square meters for each environment")
    public void calculateSquareMetersForEachEnvironmentTest() throws IOException, DistrictNotFoundException, ErrorMessage {
        // arrange
        PropertyDto propertyDto = TestUtilsGenerator.getDefaultPropertyRequest();
        Double squareMeters1 = propertyDto.getEnvironments().get(0).getSquareMeters();
        Double squareMeters2 = propertyDto.getEnvironments().get(1).getSquareMeters();
        Double squareMeters3 = propertyDto.getEnvironments().get(2).getSquareMeters();

        Mockito.when(districtService.neighborhoodExist(propertyDto.getDistrict().getDistrict_name(), propertyDto.getDistrict().getDistrict_price())).thenReturn(true);

        // act
        ResponseDto result = propertyService.calculatePrice(propertyDto);

        // assert
        verify(districtService, atLeastOnce()).neighborhoodExist(propertyDto.getDistrict().getDistrict_name(), propertyDto.getDistrict().getDistrict_price());
        Assertions.assertAll(() -> assertEquals(squareMeters1, result.getEnvironments().get(0).getSquareMeters()),
                            () -> assertEquals(squareMeters2, result.getEnvironments().get(1).getSquareMeters()),
                            () -> assertEquals(squareMeters3, result.getEnvironments().get(2).getSquareMeters()));
    }
}
