package com.mercadolibre.desafiotesting.units;

import com.mercadolibre.desafiotesting.dto.HouseRequestDto;
import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.RoomDto;
import com.mercadolibre.desafiotesting.repositories.DistrictRepository;
import com.mercadolibre.desafiotesting.services.HouseServicesImpl;
import com.mercadolibre.desafiotesting.utils.HouseTestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HouseServiceTests {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    HouseServicesImpl houseService;

    @SneakyThrows
    @Test
    @DisplayName("Total meters house test")
    void testTotalMetersHouse() {
        HouseRequestDto request = HouseTestUtils.getRequestHouse(3000.0);

        HouseResponseDto houseResponseDto = houseService.calculate(request);
        Assertions.assertDoesNotThrow(() -> houseService.calculate(request));

        verify(districtRepository, atLeastOnce()).findDistrictByName(request.getHouseDto().getDistrict_name());

        assert houseResponseDto != null;
        Assertions.assertEquals(220.0, houseResponseDto.getSquareFeet());
        Assertions.assertEquals(660000.0, houseResponseDto.getPrice());
        Assertions.assertEquals(request.getHouseDto().getDistrict_name(), houseResponseDto.getDistrict_name());

    }

    @SneakyThrows
    @Test
    @DisplayName("Biggest meters house test")
    void testBiggestHouse() {
        HouseRequestDto request = HouseTestUtils.getRequestHouse(3000.0);
        RoomDto biggest = new RoomDto("Patio", 12.0, 12.0);
        request.getHouseDto().getRooms().add(biggest);

        HouseResponseDto houseResponseDto = houseService.calculate(request);
        Assertions.assertDoesNotThrow(() -> houseService.calculate(request));

        verify(districtRepository, atLeastOnce()).findDistrictByName(request.getHouseDto().getDistrict_name());

        assert houseResponseDto != null;
        Assertions.assertEquals(biggest, houseResponseDto.getBiggest());

    }

    @SneakyThrows
    @Test
    @DisplayName("Meters house rooms test")
    void testMetersRoom() {
        HouseRequestDto request = HouseTestUtils.getRequestHouse(3000.0);

        HouseResponseDto houseResponseDto = houseService.calculate(request);

        Assertions.assertDoesNotThrow(() -> houseService.calculate(request));
        verify(districtRepository, atLeastOnce()).findDistrictByName(request.getHouseDto().getDistrict_name());

        assert houseResponseDto != null;
        Assertions.assertEquals(100, houseResponseDto.getRooms().get(0).getSquareFeet());
        Assertions.assertEquals(120, houseResponseDto.getRooms().get(1).getSquareFeet());
        Assertions.assertEquals(2, houseResponseDto.getRooms().size());

    }

}
