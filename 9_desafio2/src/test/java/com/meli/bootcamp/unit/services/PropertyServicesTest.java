package com.meli.bootcamp.unit.services;

import com.meli.bootcamp.dto.EnvironmentDetailsDTO;
import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import com.meli.bootcamp.repositories.DistrictRepositoryImp;
import com.meli.bootcamp.services.PropertyServicesImp;
import com.meli.bootcamp.unit.utilsTest.TestGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServicesTest {

    @Mock
    DistrictRepositoryImp districtRepository;
    @InjectMocks
    PropertyServicesImp propertyServices;

    @Test
    @DisplayName("Biggest Environment - Services")
    public void BiggestEnvironmentTest() throws DistrictException {
        EnvironmentDetailsDTO expected = new EnvironmentDetailsDTO("Habitacion_biggest", 20.0 * 30.0);
        PropertyDTO propertyDTO = TestGenerator.PropertyDTOTest();
        when(districtRepository.validateDistrict(propertyDTO.getDistrict().getDistrict_name())).thenReturn(true);
        ValuationDTO result = propertyServices.valuation(propertyDTO);

        Assertions.assertEquals(expected, result.getBiggest_environment());
    }

    @Test
    @DisplayName("Property Area - Services")
    public void CalculatedAreaTest() throws DistrictException {
        Double expectedArea = 815.0;
        PropertyDTO propertyDTO = TestGenerator.PropertyDTOTest();
        when(districtRepository.validateDistrict(propertyDTO.getDistrict().getDistrict_name())).thenReturn(true);
        ValuationDTO result = propertyServices.valuation(propertyDTO);

        Assertions.assertEquals(expectedArea, result.getProperty_area());

    }

    @Test
    @DisplayName("Invalid District (Exception) - Services")
    public void DistrictInvalidTest() throws DistrictException {
        PropertyDTO propertyDTO = TestGenerator.PropertyDTODistrictVoidTest();
        when(districtRepository.validateDistrict(propertyDTO.getDistrict().getDistrict_name())).thenThrow(DistrictException.class);
        Assertions.assertThrows(DistrictException.class, () -> propertyServices.valuation(propertyDTO));
    }

    @Test
    @DisplayName("Valid District - Services")
    public void DistrictTest() throws DistrictException {
        PropertyDTO propertyDTO = TestGenerator.PropertyDTOTest();
        propertyServices.valuation(propertyDTO);
        verify(districtRepository, atLeastOnce()).validateDistrict(propertyDTO.getDistrict().getDistrict_name());
    }
}

