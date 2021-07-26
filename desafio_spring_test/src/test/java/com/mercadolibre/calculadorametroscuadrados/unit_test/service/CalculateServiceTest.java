package com.mercadolibre.calculadorametroscuadrados.unit_test.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import com.mercadolibre.calculadorametroscuadrados.repositories.ICalculateRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {
    @Mock
    ICalculateRepository repository;

    @InjectMocks
    CalculateService service;

    /* TODO: Test 1/1
    *   Verificar que pregunte en el repo si existe
    *   el nombre del distrito(barrio).
    *   Verificar que guarde la casa en la db*/
    @Test
    public void calculateHouse() throws DataNotFound{
        //arrange
        HouseDTO house = TestUtilsGenerator.getHouseDTO("My casa");
        when(repository.ifDistrictAreaExist(house.getDistrict_name())).thenReturn(true);

        // act
        service.calculateHome(house);

        //assert
        verify(repository, atLeastOnce()).ifDistrictAreaExist(any());
        verify(repository, atLeastOnce()).saveHouse(any(),any());
    }

}
