package com.mercadolibre.squaremeter.unit.services;

import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import com.mercadolibre.squaremeter.dtos.request.Home;
import com.mercadolibre.squaremeter.dtos.response.InfoHome;
import com.mercadolibre.squaremeter.exceptions.DistrictNotExist;
import com.mercadolibre.squaremeter.repository.DistrictRepository;
import com.mercadolibre.squaremeter.services.DistrictServiceImp;
import com.mercadolibre.squaremeter.unit.utils.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DistrictServiceImpTest {

    @Mock
    DistrictRepository repo;

    @InjectMocks
    DistrictServiceImp service;

    @BeforeEach
    @AfterEach
    void init() {
        TestUtilsGenerator.initDataBase();

    }

    @Test
    @DisplayName("Test service postReport -Ok")
    void testPostHouseReport() throws Exception {
        Home home = TestUtilsGenerator.getRequestOk();
        InfoHome expected = TestUtilsGenerator.getResultOkReport();
        Mockito.when(repo.findDistrictByName("Palermo")).thenReturn(true);
        InfoHome current = service.postHouseReport(home);
        Mockito.verify(repo, Mockito.atLeast(1)).findDistrictByName(Mockito.anyString());
        Assertions.assertEquals(expected, current);

    }

    @Test
    @DisplayName("Test Service District - DistrictNotExist in postHouseReport")
    void testThrowPostHouseRepost() {
        DistrictNotExist dNeThrow = assertThrows(DistrictNotExist.class, () -> {
            Home home = TestUtilsGenerator.getRequestNotOk();
            Mockito.when(repo.findDistrictByName("Sarasa")).thenReturn(false);
            service.postHouseReport(home);
            Mockito.verify(repo, Mockito.atLeast(1)).findDistrictByName(Mockito.anyString());

        });
        Assertions.assertTrue(dNeThrow.getError().contains("The District not exist"));

    }


    @Test
    @DisplayName("Test service District - getAllDistrict ")
    void testGetAllDistrict() {
        List<DistrictDTO> expected = new ArrayList<>();
        Mockito.when(repo.findDistrictAll()).thenReturn(expected);
        List<DistrictDTO> current = service.getAllDistrict();
        Mockito.verify(repo, Mockito.atLeast(1)).findDistrictAll();
        Assertions.assertEquals(expected, current);
    }

}