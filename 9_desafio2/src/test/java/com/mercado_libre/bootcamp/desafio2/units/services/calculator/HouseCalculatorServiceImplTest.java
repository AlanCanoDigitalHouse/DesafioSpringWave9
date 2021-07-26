package com.mercado_libre.bootcamp.desafio2.units.services.calculator;

import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.EnvironmentDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.mercado_libre.bootcamp.desafio2.services.calculator.HouseCalculatorServiceImpl;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.NeighborhoodService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class HouseCalculatorServiceImplTest {

    @InjectMocks
    private HouseCalculatorServiceImpl houseCalculatorService;

    @Mock
    private NeighborhoodService neighborhoodService;

    @Test
    @DisplayName("calculate - Dimension of the house per square meters OK")
    void calculate_squaresMetersOfTheWholeHouse_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setEnvironments(generate3EnvironmentsMock());
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));

        HouseResponseDTO response = houseCalculatorService.calculate(houseRequest);

        assertEquals(42.00, response.getSquaresMeters());
    }

    @Test
    @DisplayName("calculate - Dimension of the one room per square meters OK")
    void calculate_squaresMetersOfOneRoom_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setEnvironments(generate1EnvironmentMock());
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));

        EnvironmentDTO environment = houseCalculatorService.calculate(houseRequest).getRooms().get(0);

        assertEquals(32.00, environment.getSquareMeters());
    }

    @Test
    @DisplayName("calculate - Value of the house per square meters OK")
    void calculate_valueOfTheHouse_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setEnvironments(generate3EnvironmentsMock());
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));

        HouseResponseDTO response = houseCalculatorService.calculate(houseRequest);

        assertEquals(84000.00, response.getValuePerSquaresMeters());
    }

    @Test
    @DisplayName("calculate - Value of the house per square meters when the list is empty")
    void calculate_valueOfAHouseWithNoRooms_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setEnvironments(new ArrayList<>());
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));

        HouseResponseDTO response = houseCalculatorService.calculate(houseRequest);

        assertEquals(0.0, response.getValuePerSquaresMeters());
    }

    @Test
    @DisplayName("calculate - Biggest room of the house OK")
    void calculate_biggestRoomOfTheHouse_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setEnvironments(generate3EnvironmentsMock());
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));
        EnvironmentDTO expectedEnvironment = new EnvironmentDTO("Living room", 5.0, 5.0);

        EnvironmentDTO actualEnvironment = houseCalculatorService.calculate(houseRequest).getBiggestRoom();

        assertEquals(expectedEnvironment, actualEnvironment);
    }

    @Test
    @DisplayName("calculate - Biggest room with no rooms returns null")
    void calculate_biggestRoomWithEmptyList_ok() {
        HouseRequestDTO houseRequest = new HouseRequestDTO();
        houseRequest.setEnvironments(new ArrayList<>());
        houseRequest.setDistrict(new DistrictDTO("Palermo", 2000.00));

        EnvironmentDTO environment = houseCalculatorService.calculate(houseRequest).getBiggestRoom();

        assertNull(environment);
    }

    private List<EnvironmentDTO> generate1EnvironmentMock() {
        List<EnvironmentDTO> environments = new ArrayList<>();
        environments.add(new EnvironmentDTO("Room", 8.0, 4.0));
        return environments;
    }

    private List<EnvironmentDTO> generate3EnvironmentsMock() {
        List<EnvironmentDTO> environments = new ArrayList<>();
        environments.add(new EnvironmentDTO("Living room", 5.0, 5.0));
        environments.add(new EnvironmentDTO("Dining room", 4.0, 2.0));
        environments.add(new EnvironmentDTO("Bedroom", 3.0, 3.0));
        return environments;
    }
}
