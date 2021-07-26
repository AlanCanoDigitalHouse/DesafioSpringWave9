package com.example.casitas.services;

import com.example.casitas.dtos.EnvironmentDTO;
import com.example.casitas.dtos.EnvironmentResponseDTO;
import com.example.casitas.dtos.HouseDTO;
import com.example.casitas.dtos.HouseResponseDTO;
import com.example.casitas.exceptions.BadRequestException;
import com.example.casitas.exceptions.DistrictNotFoundException;
import com.example.casitas.repositories.DistrictRepository;
import com.example.casitas.utils.HouseTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {

     /*
        BARRIO PALERMO = 50.0
        ("Cocina", 3.0, 4.0) 12
        ("Living", 5.0, 5.0) 25   <---- HABITACION MAS GRANDE
        ("Banio", 2.0, 3.0) 6
        ("Habitacion", 3.0, 3.5) 10,5
        TOTAL METROS CUADRADOS = 53.5

        PRECIO FINAL METROS CUADRADOS * BARRIO = (53.5 * 50.0) 2675
     */
    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    HouseService houseService;

    HouseDTO house = HouseTestUtils.buildHouse();
    EnvironmentDTO environment;

    @Test
    void calculateSquareMetersSuccess() {
        var response = new HouseResponseDTO(house.getPropertyName(), 53.5);

        Assertions.assertEquals(houseService.getSquareMeters(house), response);
    }

    @Test
    void calculateSquareMetersFail() {
        var response = new HouseResponseDTO(house.getPropertyName(), 40.0);

        Assertions.assertNotEquals(houseService.getSquareMeters(house), response);
    }

    @Test
    void getBiggerEnvironmentSuccess() {
        EnvironmentDTO living = new EnvironmentDTO("Living", 5.0, 5.0);

        Assertions.assertEquals(houseService.getBiggerEnvironment(house), living);
    }

    @Test
    void getBiggerEnvironmentWithNOEnvironments() {
        house.setEnvironments(new ArrayList<>());

        Assertions.assertThrows(BadRequestException.class, () -> houseService.getBiggerEnvironment(house));
    }

    @Test
    void getAllSquareMetersByEnvironmentSuccess() {
        List<EnvironmentResponseDTO> environmentResponseDTOList = houseService.getListEnvironments(house);

        for(int i=0; i<environmentResponseDTOList.size(); i++) {
            environment = house.getEnvironments().get(i);

            // precio
            Assertions.assertEquals(environmentResponseDTOList.get(i).getPrice(), environment.getEnvironmentLength() * environment.getEnvironmentWidth() * house.getDistrictPrice());

            // metros cuadrados de cada habitacion
            Assertions.assertEquals(environmentResponseDTOList.get(i).getSquareMeters(), environment.getEnvironmentLength() * environment.getEnvironmentWidth());
        }
    }

    @Test
    void getAllSquareMetersByEnvironmentFail() {
        List<EnvironmentResponseDTO> environmentResponseDTOList = houseService.getListEnvironments(house);

        for(int i=0; i<environmentResponseDTOList.size(); i++) {
            environment = house.getEnvironments().get(i);

            // precio
            Assertions.assertEquals(environmentResponseDTOList.get(i).getPrice(), environment.getEnvironmentLength() * environment.getEnvironmentWidth() * house.getDistrictPrice());

            // metros cuadrados de cada habitacion
            Assertions.assertNotEquals(environmentResponseDTOList.get(i).getSquareMeters(), environment.getEnvironmentLength() * environment.getEnvironmentWidth()+1);
        }
    }

    @Test
    void getPriceSuccess() throws DistrictNotFoundException {
        var houseResponse = houseService.getPrice(house);

        Assertions.assertEquals(houseResponse.getPrice(), 2675.0);
    }

    @Test
    void getPriceFail() throws DistrictNotFoundException {
        var houseResponse = houseService.getPrice(house);

        Assertions.assertNotEquals(houseResponse.getPrice(), 2374.2);
    }
}
