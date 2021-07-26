package com.bootcamp.desafio2.service;

import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @InjectMocks
    DistrictService service;

    @Mock
    IDistrictRepository districtRepository;

    @Test
    @DisplayName("Validacion de precio - Precio ingresado coincide con el de BD.")
    void findLocationWithCorrectPriceTest() throws DistrictNotExistsException, PriceNotMatchException {

        //arrange
        District district = new District("Belgrano", 1100D);
        District districtMock = new District("Belgrano", 1100D);

        when(districtRepository.findPriceByLocation("Belgrano")).thenReturn(districtMock);
        double current = service.findPriceByLocation(district.getLocation(), district.getPrice());


        //assert
        verify(districtRepository, atLeastOnce()).findPriceByLocation(Mockito.any());
        assertEquals(1100, current);

    }

    @Test
    @DisplayName("Validacion de precio - Precio ingresado NO coincide con el de BD.")
    void findLocationWithIncorrectPriceTest() throws DistrictNotExistsException, PriceNotMatchException {

        //arrange
        District district = new District("Belgrano", 400D);
        District districtMock = new District("Belgrano", 1100D);

        when(districtRepository.findPriceByLocation("Belgrano")).thenReturn(districtMock);

        //assert
        Assertions.assertThrows(PriceNotMatchException.class, () -> service.findPriceByLocation(district.getLocation(), district.getPrice()));
    }

}
