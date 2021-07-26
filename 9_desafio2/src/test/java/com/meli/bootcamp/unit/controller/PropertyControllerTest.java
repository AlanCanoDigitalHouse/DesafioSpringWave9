package com.meli.bootcamp.unit.controller;

import com.meli.bootcamp.controllers.PropertyRestController;
import com.meli.bootcamp.dto.EnvironmentDetailsDTO;
import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import com.meli.bootcamp.model.Property;
import com.meli.bootcamp.services.PropertyServicesImp;
import com.meli.bootcamp.unit.utilsTest.TestGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @Mock
    PropertyServicesImp propertyServices;
    @InjectMocks
    PropertyRestController propertyController;

    @Test
    @DisplayName("Property Area - Controller")
    public void AreaPropertyTest() throws DistrictException {
        Double expectedArea = 815.0;
        PropertyDTO property = TestGenerator.PropertyDTOTest();

        when(propertyServices.valuation(property)).thenReturn(TestGenerator.valuationDTO());
        ValuationDTO result = propertyController.valuation(property);

        Assertions.assertEquals(expectedArea, result.getProperty_area());

    }


    @Test
    @DisplayName("Area for each environment - Controller")
    public void AreaEnvironmentTest() throws DistrictException {
        List<EnvironmentDetailsDTO> expected = TestGenerator.valuationDTO().getEnvironmentDetails();
        PropertyDTO property = TestGenerator.PropertyDTOTest();
        when(propertyServices.valuation(property)).thenReturn(TestGenerator.valuationDTO());
        ValuationDTO result = propertyController.valuation(property);

        Assertions.assertTrue(CollectionUtils.isEqualCollection(expected, result.getEnvironmentDetails()));
    }

    @Test
    @DisplayName("Biggest Environment - Controller")
    public void BiggestEnvironmentTest() throws DistrictException {
        PropertyDTO property = TestGenerator.PropertyDTOTest();
        Property property1 = new Property(property);
        when(propertyServices.valuation(property)).thenReturn(TestGenerator.valuationDTO());
        ValuationDTO result = propertyController.valuation(property);
        Assertions.assertEquals(new EnvironmentDetailsDTO(property1.biggestEnvironment()), result.getBiggest_environment());

    }

    @Test
    @DisplayName("Property value - Controller")
    public void ValuationPropertyTest() throws DistrictException {
        Double expectedArea = 1267325.0;
        PropertyDTO property = TestGenerator.PropertyDTOTest();
        when(propertyServices.valuation(property)).thenReturn(TestGenerator.valuationDTO());
        ValuationDTO result = propertyController.valuation(property);
        Assertions.assertEquals(expectedArea, result.getProperty_valuation());

    }

}
