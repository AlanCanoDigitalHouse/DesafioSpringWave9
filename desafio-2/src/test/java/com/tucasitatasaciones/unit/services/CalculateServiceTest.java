package com.tucasitatasaciones.unit.services;

import com.tucasitatasaciones.DTOs.PriceDTO;
import com.tucasitatasaciones.DTOs.PropertyDTO;
import com.tucasitatasaciones.DTOs.PropertyResponseDTO;
import com.tucasitatasaciones.exceptions.DistrictBadRequestException;
import com.tucasitatasaciones.globalconstants.Message;
import com.tucasitatasaciones.repositories.PriceRepositoryImpl;
import com.tucasitatasaciones.services.CalculateService;
import com.tucasitatasaciones.utils.TestGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    PriceRepositoryImpl priceRepository;

    @InjectMocks
    CalculateService service;

    // target : Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto.
    // output: Devuelve el cálculo correcto del total de metros cuadrados de una propiedad.
    @Test
    public void squareCalculatedTest() {
        PropertyDTO requestProperty = TestGenerator.getRequestPropertyDTO();
        PropertyResponseDTO expectedPropertyResponse = TestGenerator.getResponsePropertyDTO();
        PriceDTO expectedDistrict = new PriceDTO("Recoleta", 900.0);
        when(priceRepository.findPriceLocation("Recoleta")).thenReturn(expectedDistrict);
        PropertyResponseDTO currentPropertyResponse = service.calculate(requestProperty);
        verify(priceRepository, atLeast(1)).findPriceLocation("Recoleta");
        assertThat(currentPropertyResponse.getSquareFeet()).isEqualTo(expectedPropertyResponse.getSquareFeet());
    }

    // target: Verificar que efectivamente se devuelva el ambiente con mayor tamaño.
    // output: Devuelve el ambiente con mayor tamaño. No existe ninguno que lo supere.
    @Test
    public void getBiggestEnvironmentTest() {
        PropertyDTO requestProperty = TestGenerator.getRequestPropertyDTO();
        PropertyResponseDTO expectedPropertyResponse = TestGenerator.getResponsePropertyDTO();
        PriceDTO expectedDistrict = new PriceDTO("Recoleta", 900.0);
        when(priceRepository.findPriceLocation("Recoleta")).thenReturn(expectedDistrict);
        PropertyResponseDTO currentPropertyResponse = service.calculate(requestProperty);
        verify(priceRepository, atLeast(1)).findPriceLocation("Recoleta");
        assertThat(currentPropertyResponse.getBiggest()).isEqualTo(expectedPropertyResponse.getBiggest());
    }

    // target: Verificar que efectivamente el total de metros cuadrados por ambiente sea el correcto.
    // output: Devuelve el cálculo correcto del total de metros cuadrados de un ambiente.
    @Test
    public void getSquareEnvironmentTest() {
        PropertyDTO requestProperty = TestGenerator.getRequestPropertyDTO();
        PropertyResponseDTO expectedPropertyResponse = TestGenerator.getResponsePropertyDTO();
        PriceDTO expectedDistrict = new PriceDTO("Recoleta", 900.0);
        when(priceRepository.findPriceLocation("Recoleta")).thenReturn(expectedDistrict);
        PropertyResponseDTO currentPropertyResponse = service.calculate(requestProperty);
        verify(priceRepository, atLeast(1)).findPriceLocation("Recoleta");
        double[] currentSquares = new double[currentPropertyResponse.getEnvironments().size()];
        double[] expectedSquares = TestGenerator.getSquaresEnvironments();
        // act
        for (int i = 0; i < currentSquares.length; i++)
            currentSquares[i] = currentPropertyResponse.getEnvironments().get(i).getSquareFeet();
        assertThat(currentSquares).isEqualTo(expectedSquares);
    }

    // target: Verificar que efectivamente el distrito con el que se quiere calcular no existe
    // output: Excepcion de no encuentra el distrito
    @Test
    public void tryCalculateSquareWrongDistinct1() {
        PropertyDTO requestProperty = TestGenerator.getRequestNotExistPropertyDTO();
        DistrictBadRequestException expectedException = new DistrictBadRequestException(Message.DISTRICT_NOT_EXISTS);
        DistrictBadRequestException currentException = new DistrictBadRequestException("");
        try {
            service.calculate(requestProperty);
        } catch (DistrictBadRequestException e) {
            currentException = e;
        }
        assertThat(currentException.getMessage()).isEqualTo(expectedException.getMessage());
    }

    // target: Verificar que efectivamente el distrito con el que se quiere calcular no existe con esos datos
    // output: Excepcion distrito con precio por metro erroneo
    @Test
    public void tryCalculateSquareWrongDistinct2() {
        PropertyDTO requestProperty = TestGenerator.getRequestPropertyDTO();
        DistrictBadRequestException currentException = new DistrictBadRequestException("");
        PriceDTO expectedDistrict = new PriceDTO("Recoleta", -1);
        when(priceRepository.findPriceLocation("Recoleta")).thenReturn(expectedDistrict);
        try {
            PropertyResponseDTO currentPropertyResponse = service.calculate(requestProperty);
            verify(priceRepository, atLeast(1)).findPriceLocation("Recoleta");
        } catch (DistrictBadRequestException e) {
            currentException = e;
        }
        assertThat(currentException.getMessage()).isEqualTo(Message.DISTRICT_WRONG_PRICE);
    }

}

