package com.mercadolibre.calculadorametroscuadrados.unit.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFound;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictPriceNotMatch;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import util.UtilGenerator;

@ExtendWith(MockitoExtension.class)
@DisplayName("Service Unit Test")
public class CalculateServiceTest {

    @Mock
    DistrictRepositoryImpl repository;

    @InjectMocks
    CalculateService service;

    @Test
    public void calculatePerfectCase() throws DistrictNotFound, DistrictPriceNotMatch {
        HouseDTO house = UtilGenerator.genHousePerfectCase();
        HouseResponseDTO expected = UtilGenerator.genHouseResponseExpected(house);

        Mockito.when(repository.findDistrictPrice(house.getDistrict_name())).thenReturn(3000.0);
        HouseResponseDTO result = service.calculate(house);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void districtPriceNotMatchDistrictDb() throws DistrictNotFound{
        HouseDTO house = UtilGenerator.genHousePerfectCase();

        Mockito.when(repository.findDistrictPrice(house.getDistrict_name())).thenReturn(2000.0);

        Assertions.assertThrows(DistrictPriceNotMatch.class, ()-> service.calculate(house));
    }

    @Test
    public void propertyPrice() throws DistrictNotFound, DistrictPriceNotMatch {
        HouseDTO house = UtilGenerator.genHousePerfectCase();
        HouseResponseDTO expected = UtilGenerator.genHouseResponseExpected(house);

        Mockito.when(repository.findDistrictPrice(house.getDistrict_name())).thenReturn(3000.0);
        HouseResponseDTO result = service.calculate(house);

        Assertions.assertEquals(expected.getPrice(), result.getPrice());
    }

    @Test
    public void propertyBiggestRoom() throws DistrictNotFound, DistrictPriceNotMatch {
        HouseDTO house = UtilGenerator.genHousePerfectCase();
        HouseResponseDTO expected = UtilGenerator.genHouseResponseExpected(house);

        Mockito.when(repository.findDistrictPrice(house.getDistrict_name())).thenReturn(3000.0);
        HouseResponseDTO result = service.calculate(house);

        Assertions.assertEquals(expected.getBiggest(), result.getBiggest());
    }

    @Test
    public void propertySquareFeetRooms() throws DistrictNotFound, DistrictPriceNotMatch {
        HouseDTO house = UtilGenerator.genHousePerfectCase();
        HouseResponseDTO expected = UtilGenerator.genHouseResponseExpected(house);

        Mockito.when(repository.findDistrictPrice(house.getDistrict_name())).thenReturn(3000.0);
        HouseResponseDTO result = service.calculate(house);

        Assertions.assertEquals(expected.getEnviroments(), result.getEnviroments());
    }
}
