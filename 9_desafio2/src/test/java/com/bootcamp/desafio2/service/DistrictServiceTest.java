package com.bootcamp.desafio2.service;

import com.bootcamp.desafio2.dtos.request.DistrictDto;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import com.bootcamp.desafio2.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith({MockitoExtension.class})
public class DistrictServiceTest {

    @Mock
    IDistrictRepository districtRepository;

    @InjectMocks
    DistrictService districtService;

    @BeforeEach
    void init() {
        TestUtilGenerator.emptyPricesFile();
        TestUtilGenerator.appendNewDistrict(new DistrictDto("Narvarte", 1000D));
    }

    @Test
    void exceptionTest () throws IOException {
        Mockito.when(districtRepository.districtExist(Mockito.anyString(), Mockito.anyDouble()))
                .thenThrow(IOException.class);

        Assertions.assertThrows(IOException.class,
                () -> districtService.neighborhoodExist("Narvarte", 1000D));
    }

    @Test
    void districtExists () throws IOException {
        Mockito.when(districtRepository.districtExist("Narvarte", 1000D))
                .thenReturn(true);

        Boolean exists = districtService.neighborhoodExist("Narvarte", 1000D);

        Assertions.assertEquals(true, exists);

    }

    @Test
    void districtDontExists () throws IOException {
        Mockito.when(districtRepository.districtExist("Roma", 1000D))
                .thenReturn(false);

        Boolean exists = districtService.neighborhoodExist("Roma", 1000D);

        Assertions.assertEquals(false, exists);
    }
}
