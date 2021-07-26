package com.desafio2.spring.tucasita.tucasita.unit.service;

import com.desafio2.spring.tucasita.tucasita.dtos.request.DistrictDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.request.HouseDTO;
import com.desafio2.spring.tucasita.tucasita.dtos.response.HouseSizeDTO;
import com.desafio2.spring.tucasita.tucasita.exceptions.DistrictDoesNotExistException;
import com.desafio2.spring.tucasita.tucasita.exceptions.ServiceExceptions;
import com.desafio2.spring.tucasita.tucasita.exceptions.WrongPriceException;
import com.desafio2.spring.tucasita.tucasita.repositories.PriceLocationRepository;
import com.desafio2.spring.tucasita.tucasita.services.HouseService;
import com.desafio2.spring.tucasita.tucasita.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Tet - House Service")
public class HouseServiceTest {

    @Mock
    PriceLocationRepository mockRepo;

    @InjectMocks
    HouseService houseService;

    /** House Size Tests */
    @Test
    @DisplayName("House Size Tests - District doesn't exist")
    void invalidDistrictHouseSizeTest() {
        //arrange
        HouseDTO houseWithInvalidDistrict = TestUtilGenerator.invalidDistrictNameHouse();
        String invalidDistrictName = houseWithInvalidDistrict.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(invalidDistrictName)).thenReturn(null);

        //act && assert
        Assertions.assertThrows(DistrictDoesNotExistException.class, () -> houseService.getHouseSize(houseWithInvalidDistrict));
    }

    @Test
    @DisplayName("House Size Tests - Wrong district price")
    void invalidDistrictPriceSizeTest() {
        //arrange
        HouseDTO houseWithInvalidDistrict = TestUtilGenerator.invalidDistrictPriceHouse();
        String invalidDistrictName = houseWithInvalidDistrict.getDistrict().getDistrict_name();
        DistrictDTO expectedDistrict = new DistrictDTO("Palermo", 1000);
        when(mockRepo.findPriceLocation(invalidDistrictName)).thenReturn(expectedDistrict);

        //act && assert
        Assertions.assertThrows(WrongPriceException.class,
                () -> houseService.getHouseSize(houseWithInvalidDistrict));
    }

    @Test
    @DisplayName("House Size Tests - Correct size")
    void correctSizeTest() throws ServiceExceptions {
        //arrange
        HouseDTO house = TestUtilGenerator.simpleHouseForTestSize33();
        String districtName = house.getDistrict().getDistrict_name();
        when(mockRepo.findPriceLocation(districtName)).thenReturn(house.getDistrict());
        double expectedSize = 33.0;

        //act
        HouseSizeDTO houseSizeDTO = houseService.getHouseSize(house);

        //assert
        Assertions.assertEquals(expectedSize, houseSizeDTO.getTotalSize());
    }
}
