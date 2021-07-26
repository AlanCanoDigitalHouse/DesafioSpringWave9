package com.mercadolibre.tucasitaTasaciones.unitTests.services;

import com.mercadolibre.tucasitaTasaciones.dto.EnvironmentDTO;
import com.mercadolibre.tucasitaTasaciones.dto.PropertyDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.tucasitaTasaciones.dto.response.PropertyResponseDTO;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationNotFound;
import com.mercadolibre.tucasitaTasaciones.exceptions.ExceptionLocationPriceIncorrect;
import com.mercadolibre.tucasitaTasaciones.repositories.CalculateRepository;
import com.mercadolibre.tucasitaTasaciones.service.CalculateServiceImpl;
import com.mercadolibre.tucasitaTasaciones.util.TestUtilsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTests {

    @Mock
    CalculateRepository calculateRepository;

    @InjectMocks
    CalculateServiceImpl service;

    @Test
    @DisplayName("Tests validar metros de propiedad")
    public void squareFeetWellCalculated() throws ExceptionLocationNotFound, ExceptionLocationPriceIncorrect {
        // arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWith3Environments("CasaOk");
        when(calculateRepository.findPriceLocation(property.getDistrict().getDistrict_name())).thenReturn(property.getDistrict());

        // act
        PropertyResponseDTO propertyResponseDTO = service.calculate(property);

        // assert
        verify(calculateRepository, atLeastOnce()).findPriceLocation(property.getDistrict().getDistrict_name());
        assertEquals(617.5, propertyResponseDTO.getSquareFeet());
    }

    @Test
    @DisplayName("Tests validar biggest env")
    public void biggestWellCalculated() throws ExceptionLocationNotFound, ExceptionLocationPriceIncorrect {
        // arrange
        EnvironmentDTO biggest = new EnvironmentDTO("Cocina", 20.0, 20.0);
        PropertyDTO property = TestUtilsGenerator.getPropertyWith3Environments("CasaOk");
        when(calculateRepository.findPriceLocation(property.getDistrict().getDistrict_name())).thenReturn(property.getDistrict());

        // act
        PropertyResponseDTO propertyResponseDTO = service.calculate(property);

        // assert
        verify(calculateRepository, atLeastOnce()).findPriceLocation(property.getDistrict().getDistrict_name());
        assertEquals(biggest, propertyResponseDTO.getBiggest());
    }

    @Test
    @DisplayName("Tests validar metros env")
    public void squareFeetEnvWellCalculated() throws ExceptionLocationNotFound, ExceptionLocationPriceIncorrect {
        // arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWith3Environments("CasaOk");
        when(calculateRepository.findPriceLocation(property.getDistrict().getDistrict_name())).thenReturn(property.getDistrict());

        // act
        PropertyResponseDTO propertyResponseDTO = service.calculate(property);
        EnvironmentResponseDTO environmentResponseDTO = propertyResponseDTO.getSquareFeetEnvironments().get(0);
        // assert
        verify(calculateRepository, atLeastOnce()).findPriceLocation(property.getDistrict().getDistrict_name());
        assertEquals(400.0, environmentResponseDTO.getSquareFeet());
    }
}
