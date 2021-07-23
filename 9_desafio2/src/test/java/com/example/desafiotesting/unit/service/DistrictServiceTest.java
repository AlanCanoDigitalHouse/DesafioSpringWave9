package com.example.desafiotesting.unit.service;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.models.District;
import com.example.desafiotesting.repository.DistrictRepository;
import com.example.desafiotesting.services.DistrictService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    DistrictService service;

    @Test
    @DisplayName("Get district by name test")
    void getDistrictByNameTest() throws Exception {
        //arrange
        String districtName = "Palermo";
        DistrictDTO expected = new DistrictDTO(
                "Palermo",
                1000.0
        );

        //mocks
        District districtMock = new District(
                1,
                "Palermo",
                1000.0
        );

        when(districtRepository.findByName("Palermo")).thenReturn(districtMock);
        //act
        DistrictDTO current = service.getDistrictByName(districtName);

        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Not found district by name")
    void getDistrictByNameNotFoundTest() throws Exception {
        //arrange
        //Setting a non valid district name
        String districtName = "Brooklyn";

        when(districtRepository.findByName(districtName)).thenThrow(new DistrictException(DistrictException.NAME_NOT_FOUND));

        assertThrows(DistrictException.class, () -> service.getDistrictByName(districtName));
    }
}
