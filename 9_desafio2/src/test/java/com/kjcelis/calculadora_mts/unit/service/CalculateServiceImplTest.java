package com.kjcelis.calculadora_mts.unit.service;

import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;
import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;
import com.kjcelis.calculadora_mts.repository.DistrictRepository;
import com.kjcelis.calculadora_mts.services.CalculateServiceImpl;
import com.kjcelis.calculadora_mts.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Map;

@DisplayName("Unit Test - CalculateServiceImpl")
@ExtendWith(MockitoExtension.class)
public class CalculateServiceImplTest {

    @Mock
    DistrictRepository repository;

    @InjectMocks
    CalculateServiceImpl service;

    @DisplayName("Validar el total Mts Cuadrados propiedad")
    @Test
    void totalAmountMtsTst() {

        Double current = service.totalAmountMts(TestUtilGenerator.getEvironmentSet());
        Assertions.assertEquals(783, current);
    }

    @DisplayName("Verificar ambiente mayor tamaño")
    @Test
    void largerEnvironmentTst() {
        String current = service.largerEnvironment(TestUtilGenerator.getEvironmentSet());
        Assertions.assertEquals("Cocina", current);
    }

    @DisplayName("Verificar total de metros cuadrados por ambiente")
    @Test
    void environmentsMtsTst() {

        Map<String, Double> current = service.environmentsMts(TestUtilGenerator.getEvironmentSet());
        Assertions.assertEquals(TestUtilGenerator.squareMetersEnvsSet(), current);
    }


    @DisplayName("Verificar total de metros cuadrados por ambiente")
    @Test
    void calculatePriceTst() throws NotFoundDistricException {
        DistrictDTO districtDTOMock = new DistrictDTO("Mani", 444);
        Mockito.when(repository.findDistrictRepo("Lima", 500)).thenReturn(districtDTOMock);
        double current = service.calculatePrice(355, "Lima", 500);
        Mockito.verify(repository, Mockito.atLeastOnce()).findDistrictRepo("Lima", 500);
        Assertions.assertEquals(157620.0, current);
    }

    @DisplayName("Verificar la creacion del objeto HouseResponseDTO ")
    @Test
    void calculateTst() throws NotFoundDistricException {

        HouseResponseDTO expected = TestUtilGenerator.getResponseDTO();

        DistrictDTO districtDTOMock = new DistrictDTO("Bosa", 500);
        Mockito.when(repository.findDistrictRepo("Bosa", 500)).thenReturn(districtDTOMock);

        HouseResponseDTO current = service.calculate(TestUtilGenerator.getRequestDTO());
        Assertions.assertEquals(expected, current);

    }

}
