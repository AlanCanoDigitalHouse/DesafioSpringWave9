package com.mercadolibre.calculadorametroscuadrados.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertyPriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertySquareFeetDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.repository.NotFoundException;
import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateServiceImpl;
import com.mercadolibre.calculadorametroscuadrados.util.UtilPropertyGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceImplTest {

    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    CalculateServiceImpl calculateService;


    @Test
    void shouldCalculateSquareFeetInOneObject() throws NotFoundException {

        Double totalSquareFeet = 300D;
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3EnvironmentsSameDetails("NewProperty",
                "Down Town",
                4000D,
                10D,
                10D);

        DistrictDAO district = new DistrictDAO("Down Town", 4000D);
        when(districtRepository.findByName(district.getDistrict_name())).thenReturn(district);

        HouseResponseDTO response = calculateService.calculate(houseDTO);
        verify(districtRepository, atLeastOnce()).findByName(district.getDistrict_name());

        assertEquals(totalSquareFeet, response.getSquareFeet());
    }

    @Test
    void shouldCalculateSquareFeet() {

        Double totalSquareFeet = 300D;
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3EnvironmentsSameDetails("NewProperty",
                "Down Town",
                4000D,
                10D,
                10D);

        PropertySquareFeetDTO response = calculateService.calculatePropertySquareFeet(houseDTO);

        assertEquals(totalSquareFeet, response.getSquareFeet());
    }

    @Test
    void shouldCalculateBiggestEnvironment() {

        RoomDTO environment = new RoomDTO("Environment3", 50D, 50D);
        Double expectedSquareFeet = 2500D;
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3Environments("NewProperty", "Down Town", 4000D);

        RoomResponseDTO response = calculateService.getBiggestEnvironment(houseDTO);

        assertEquals(environment, response.getEnvironment());
        assertEquals(expectedSquareFeet, response.getSquare_feet());
    }

    @Test
    void shouldCalculatePrice() throws NotFoundException {

        // Expected price
        Double expectedPrice = 12000000D;
        // Total square feet are 300
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3Environments("NewProperty", "Down Town", 4000D);

        DistrictDAO district = new DistrictDAO("Down Town", 4000D);
        when(districtRepository.findByName(district.getDistrict_name())).thenReturn(district);

        PropertyPriceDTO response = calculateService.calculatePropertyPrice(houseDTO);
        verify(districtRepository, atLeastOnce()).findByName(district.getDistrict_name());

        assertEquals(houseDTO, response.getProperty());
        assertEquals(expectedPrice, response.getPrice());
    }

    @Test
    void shouldCalculateSquareFeetPerEnvironment() {

        RoomResponseDTO environment1 = new RoomResponseDTO();
        environment1.setEnvironment(new RoomDTO("Environment1", 10D, 10D));
        environment1.setSquare_feet(100D);
        RoomResponseDTO environment2 = new RoomResponseDTO();
        environment2.setEnvironment(new RoomDTO("Environment2", 20D, 20D));
        environment2.setSquare_feet(400D);
        RoomResponseDTO environment3 = new RoomResponseDTO();
        environment3.setEnvironment(new RoomDTO("Environment3", 25D, 25D));
        environment3.setSquare_feet(625D);

        List<RoomDTO> environments = new ArrayList<>();
        environments.add(environment1.getEnvironment());
        environments.add(environment2.getEnvironment());
        environments.add(environment3.getEnvironment());

        List<RoomResponseDTO> expectedEnvironments = new ArrayList<>();
        expectedEnvironments.add(environment1);
        expectedEnvironments.add(environment2);
        expectedEnvironments.add(environment3);

        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrict_name("Test");
        districtDTO.setDistrict_price(220D);

        HouseDTO property = new HouseDTO();
        property.setEnvironments(environments);
        property.setProp_name("Test");
        property.setDistrict(districtDTO);

        List<RoomResponseDTO> response = calculateService.calculateSquareFeetPerEnvironment(property);

        assertEquals(expectedEnvironments, response);
    }

    @Test
    void shouldCalculateOneEnvironment() throws NotFoundException {

        Double expectedPrice = 400000D;
        Double expectedSquareFeet = 100D;
        RoomDTO environment1 = new RoomDTO("Environment", 10D, 10D);

        List<RoomDTO> environments = new ArrayList<>();
        environments.add(environment1);

        DistrictDAO district = new DistrictDAO("Down Town", 4000D);
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setDistrict_name("Down Town");
        districtDTO.setDistrict_price(4000D);

        HouseDTO property = new HouseDTO();
        property.setEnvironments(environments);
        property.setProp_name("New property");
        property.setDistrict(districtDTO);

        when(districtRepository.findByName(district.getDistrict_name())).thenReturn(district);

        HouseResponseDTO response = calculateService.calculate(property);
        verify(districtRepository, atLeastOnce()).findByName(district.getDistrict_name());

        assertEquals(environment1, response.getBiggest());
        assertEquals(expectedSquareFeet, response.getSquareFeet());
        assertEquals(expectedPrice, response.getPrice());
    }

    @Test
    void shouldCalculate3Environments() throws NotFoundException {

        RoomDTO biggest = new RoomDTO("Environment3", 50D, 50D);
        HouseDTO houseDTO = UtilPropertyGenerator.getPropertyWith3Environments(
                "NewProperty",
                "Mid Town",
                4000D);
        double expectedSquareFeet = 300000D;
        double expectedPrice = 371400000D;

        DistrictDAO district = new DistrictDAO("Mid Town", 1238D);
        when(districtRepository.findByName(district.getDistrict_name())).thenReturn(district);

        HouseResponseDTO response = calculateService.calculate(houseDTO);
        verify(districtRepository, atLeastOnce()).findByName(district.getDistrict_name());

        assertEquals(biggest, response.getBiggest());
        assertEquals(3000D, response.getSquareFeet());
        assertEquals(3714000D, response.getPrice());
    }
}
