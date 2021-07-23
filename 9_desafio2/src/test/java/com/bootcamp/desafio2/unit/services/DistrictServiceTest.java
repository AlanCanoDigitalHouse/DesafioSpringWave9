package com.bootcamp.desafio2.unit.services;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.services.implementation.DistrictService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @Mock
    IDistrictRepository districtRepository;

    @InjectMocks
    DistrictService districtService;

    @Test
    void validDistrictExistTrue() throws IOException {
        String name = "Boita";
        Double price = 3000D;
        when(districtRepository.districtExist(anyString(), anyDouble())).thenReturn(true);

        boolean response = districtService.neighborhoodExist(name, price);

        verify(districtRepository, times(1)).districtExist("Boita", 3000D);
        assertTrue(response);
    }


    @Test
    void validDistrictExistFalse() throws IOException {
        String name = "Chucua";
        Double price = 3000D;
        when(districtRepository.districtExist(anyString(), anyDouble())).thenReturn(true);

        boolean response = districtService.neighborhoodExist(name, price);

        verify(districtRepository, times(1)).districtExist("Chucua", 3000D);
        assertTrue(response);
    }

    @Test
    void ioExceptionTest() throws IOException {
        when(districtRepository.districtExist(anyString(), anyDouble()))
                .thenThrow(IOException.class);

        assertThrows(IOException.class,
                () ->  districtService.neighborhoodExist("Boita", 3000D));
    }

}
