package com.bootcamp.desafio2.units.service;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.repositories.implementation.DistrictRepository;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import com.bootcamp.desafio2.services.implementation.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTests {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    DistrictService districtService;

    @Test
    void ioExceptionTest() throws IOException {
        // arrange
        Mockito.when(districtRepository.districtExist(Mockito.anyString(), Mockito.anyDouble())).thenThrow(IOException.class);

        //assert
        Assertions.assertThrows(IOException.class,() ->  districtService.neighborhoodExist("Villa la Angostura", 5000D));
    }

}
