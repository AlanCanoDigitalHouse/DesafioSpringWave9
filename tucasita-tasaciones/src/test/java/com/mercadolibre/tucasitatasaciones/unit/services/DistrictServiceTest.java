package com.mercadolibre.tucasitatasaciones.unit.services;

import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.IDistrictRepository;
import com.mercadolibre.tucasitatasaciones.services.implementations.DistrictServiceImpl;
import com.mercadolibre.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @Mock
    IDistrictRepository districtRepository;

    @InjectMocks
    DistrictServiceImpl districtService;

    @Test
    @DisplayName("District exists")
    void districtExistsTest() throws DistrictNotFoundException {
        when(districtRepository.findByName("Palermo")).thenReturn(new District("Palermo",1000D));

        DistrictDTO districtExpected = new DistrictDTO("Palermo", 1000D);
        DistrictDTO districtReal = districtService.findByName("Palermo");

        verify(districtRepository, times(1)).findByName("Palermo");
        Assertions.assertEquals(districtExpected,districtReal);
    }

    //Cuando falle la base
    //ThenThrow

    @Test
    @DisplayName("District NOT exists")
    void districtNotExistsTest() throws DistrictNotFoundException {
        DistrictDTO districtReal = districtService.findByName("Don Torcuato");

        verify(districtRepository, times(1)).findByName("Don Torcuato");
        Assertions.assertNull(districtReal);
    }

    @Test
    @DisplayName("District with invalid parameter")
    void districtWithInvalidParameter() throws DistrictNotFoundException {
        when(districtRepository.findByName(anyString())).thenThrow(DistrictNotFoundException.class);

        Assertions.assertThrows(DistrictNotFoundException.class,() ->  districtService.findByName("Don Torcuato"));
    }

}
