package com.mercadolibre.tucasitatasaciones.unit.services;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.DistrictRepositoryImpl;
import com.mercadolibre.tucasitatasaciones.services.DistrictServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class DistrictServiceImplTest {

    @Mock
    private DistrictRepositoryImpl districtRepository;

    @InjectMocks
    private DistrictServiceImpl districtService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testDistrictFound() throws DistrictNotFoundException {
        String expected = "Palermo";
        Mockito.when(districtRepository.findDistrictBy(Mockito.anyString())).thenReturn(new DistrictDTO("Palermo", 1000D));

        DistrictDTO current = districtService.findDistrictBy(expected);

        Assertions.assertEquals(expected, current.getDistrictName());
    }

    @Test
    void testDistrictNotFound() throws DistrictNotFoundException {
        Mockito.when(districtRepository.findDistrictBy(Mockito.anyString())).thenThrow(DistrictNotFoundException.class);

        Assertions.assertThrows(DistrictNotFoundException.class, () -> districtService.findDistrictBy("Recoleta"));
    }

}