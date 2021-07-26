package com.mercadolibre.tucasitatasaciones.unit.services;

import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.exceptions.ErrorMessage;
import com.mercadolibre.tucasitatasaciones.services.IDistrictService;
import com.mercadolibre.tucasitatasaciones.services.implementations.HouseServiceImpl;
import com.mercadolibre.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {

    @Mock
    IDistrictService districtService;

    @InjectMocks
    HouseServiceImpl houseService;

    @BeforeEach
    void init(){
        TestUtilsGenerator.initDataBase();
        TestUtilsGenerator.appendNewDistrict(new District("Palermo", 1000D));
    }

    @Test
    @DisplayName("Well calculated house square meters")
    void wellSquareMetersHouseCalcTest() throws DistrictNotFoundException {
        HouseDTO bodyReq = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getSquareMetersWith3Rooms();
        when(districtService.findByName("Palermo")).thenReturn(new DistrictDTO("Palermo",1000D));

        AssessmentDTO realResponse = houseService.assessmentSquareMeters(bodyReq);

        verify(districtService, times(1)).findByName("Palermo");
        assertEquals(response,realResponse);
    }

    @Test
    @DisplayName("Well calculated house price")
    void wellHousePriceCalcTest() throws DistrictNotFoundException {
        HouseDTO bodyReq = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getPriceWith3Rooms();
        when(districtService.findByName("Palermo")).thenReturn(new DistrictDTO("Palermo",1000D));

        AssessmentDTO realResponse = houseService.assessmentPrice(bodyReq);

        verify(districtService, times(1)).findByName("Palermo");
        assertEquals(response,realResponse);
    }

    @Test
    @DisplayName("Well calculated biggest room")
    void biggestRoomTest() throws DistrictNotFoundException {
        HouseDTO bodyReq = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.getBiggestRoomWith3Rooms();
        when(districtService.findByName("Palermo")).thenReturn(new DistrictDTO("Palermo",1000D));

        AssessmentDTO realResponse = houseService.assessmentBiggestRoom(bodyReq);

        verify(districtService, times(1)).findByName("Palermo");
        assertEquals(response,realResponse);
    }

    @Test
    @DisplayName("Well calculated biggest room")
    void roomWithWellCalculedSquareMetersTest() throws DistrictNotFoundException {
        HouseDTO bodyReq = TestUtilsGenerator.getHouseWith3Rooms();
        AssessmentDTO response = TestUtilsGenerator.get3RoomsWithSquareMeters();
        when(districtService.findByName("Palermo")).thenReturn(new DistrictDTO("Palermo",1000D));

        AssessmentDTO realResponse = houseService.assessmentSquareMetersEachRoom(bodyReq);

        verify(districtService, times(1)).findByName("Palermo");
        assertEquals(response,realResponse);
    }

}
