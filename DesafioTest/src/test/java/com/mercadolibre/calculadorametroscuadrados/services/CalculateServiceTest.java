package com.mercadolibre.calculadorametroscuadrados.services;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    DistrictRepository repo;

    @InjectMocks
    CalculateServiceImp service;

    @Test
    @DisplayName("Test Calculate House Properties")
    void calculateHouseProperties() {
        //arrange
        HouseDTO houseDTO = TestUtilGenerator.getHouseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();

        HouseDTO expected = TestUtilGenerator.getHouseToResponseWith3EnvLivingBiggestTotalPrice85000TotalMeter85();

        //mocks
        DistrictDTO returnRepo = new DistrictDTO();
        returnRepo.setDistrictName("Palermo");

        when(repo.findDistrictByDistrictName("Palermo")).thenReturn(returnRepo);

        //act
        HouseDTO current = service.calculate(houseDTO);

        //assert
        Mockito.verify(repo, Mockito.atLeast(1)).findDistrictByDistrictName(Mockito.anyString());
        Assertions.assertEquals(current, expected);
    }
}
