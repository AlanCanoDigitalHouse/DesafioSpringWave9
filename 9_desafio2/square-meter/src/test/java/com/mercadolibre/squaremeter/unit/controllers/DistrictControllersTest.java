package com.mercadolibre.squaremeter.unit.controllers;

import com.mercadolibre.squaremeter.controllers.DistrictControllers;
import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import com.mercadolibre.squaremeter.dtos.request.Home;
import com.mercadolibre.squaremeter.dtos.response.InfoHome;
import com.mercadolibre.squaremeter.exceptions.DistrictNotExist;
import com.mercadolibre.squaremeter.services.DistrictServiceImp;
import com.mercadolibre.squaremeter.unit.utils.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DistrictControllersTest {

    @Mock
    DistrictServiceImp service;

    @InjectMocks
    DistrictControllers controller;

    @BeforeEach
    @AfterEach
    void init() {
        TestUtilsGenerator.initDataBase();

    }

    @Test
    @DisplayName("Test Controller District - getAllDistrict Ok")
    void testGetAllDistrict() {
        List<DistrictDTO> expected = new ArrayList<DistrictDTO>();
        Mockito.when(service.getAllDistrict()).thenReturn(expected);
        ResponseEntity<List<DistrictDTO>> current = controller.getAllDistrict();
        verify(service, atLeastOnce()).getAllDistrict();
        Assertions.assertEquals(expected, current.getBody());
    }

    @Test
    @DisplayName("Test Controller District - PostHouseReport Ok")
    void testPostHouseReport() throws DistrictNotExist {
        Home home = TestUtilsGenerator.getRequestOk();
        InfoHome expected = TestUtilsGenerator.getResultOkReport();
        Mockito.when(service.postHouseReport(home)).thenReturn(expected);
        controller.postHouseReport(home);
        verify(service, atLeastOnce()).postHouseReport(home);

    }

}