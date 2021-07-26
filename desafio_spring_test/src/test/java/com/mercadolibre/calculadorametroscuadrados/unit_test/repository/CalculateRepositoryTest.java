package com.mercadolibre.calculadorametroscuadrados.unit_test.repository;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.CalculateRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateRepositoryTest {


    @InjectMocks
    CalculateRepositoryImpl repository;

    /* TODO: Test 1/2
    *   Verificar que busque el nombre del distrito en la db
    *   y devuelva un booleano*/
    @Test
    public void findDistrictLocationByNameRepositoryTest(){
        // arrange
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("My House");
        Optional<LocationDTO> location = TestUtilsGenerator.getOptionalLocation();
        // act
        repository.ifDistrictAreaExist(houseDTO.getDistrict_name());

        // assert
        Assertions.assertTrue(repository.ifDistrictAreaExist(houseDTO.getDistrict_name()));
        Assertions.assertEquals(repository.findStratumByLocationName(houseDTO.getDistrict_name()).get().getLocation(),location.get().getLocation());
    }

    /* TODO: Test 2/2
    *   verifica los metodos para eliminar, guardar y preguntar
    *   si existe, para una casa en la db*/
    @Test
    public void saveHouse(){
        //Arrange
        HouseDTO houseDTO = TestUtilsGenerator.getHouseDTO("My House");
        HouseResponseDTO houseResponseDTO = TestUtilsGenerator.getHouseResponseDTOfromHouseDTO(houseDTO);

        // ACT
        repository.saveHouse(houseDTO,houseResponseDTO);

        //assert
        Assertions.assertTrue(repository.exists(houseDTO.getProp_name()));
        Assertions.assertTrue(repository.findStratumByLocationName(houseDTO.getDistrict_name()).isPresent());
        Assertions.assertTrue(repository.ifDistrictAreaExist(houseDTO.getDistrict_name()));
        Assertions.assertTrue(repository.delete(houseDTO.getProp_name()));
    }
}
