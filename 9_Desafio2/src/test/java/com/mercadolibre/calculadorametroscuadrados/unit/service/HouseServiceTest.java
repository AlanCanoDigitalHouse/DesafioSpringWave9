package com.mercadolibre.calculadorametroscuadrados.unit.service;

import com.mercadolibre.calculadorametroscuadrados.dto.*;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictsRepository;
import com.mercadolibre.calculadorametroscuadrados.service.HouseService;

import static com.mercadolibre.calculadorametroscuadrados.utils.ToolsVarias.loadOneRoom;
import static com.mercadolibre.calculadorametroscuadrados.utils.ToolsVarias.loadTwoRooms;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


public class HouseServiceTest {

    @Mock
    DistrictsRepository repository;

    @Test
    @DisplayName("Verificar que el total de metros cuadrados totales calculados por propiedad sea el correcto.")
    public void verifyTotalArea(){
        // Start
        HouseTotalSizeRequestDTO houseTotalSizeRequestDTO = new HouseTotalSizeRequestDTO();
        houseTotalSizeRequestDTO.setRooms(loadOneRoom());
        HouseService houseService = new HouseService();

        // Act
        var result = houseService.calculate(houseTotalSizeRequestDTO);

        // Assert
        assertEquals(50D, result.getArea().doubleValue());
    }


    @Test
    @DisplayName("Verificar precio de la propiedad.")
    public void price(){
        // Start
        DistrictsRepository repository = new DistrictsRepository();
        repository.loadDistricts();
        HouseService houseService = new HouseService(repository);

        HousePriceRequestDTO housePrice = new HousePriceRequestDTO();
        housePrice.setDistrict_price(100D);
        housePrice.setRooms(loadOneRoom());
        housePrice.setDistrict_name("Centro");

        // Act
        var aux =  houseService.totalPrice(housePrice);

        // Assert
        assertEquals(5000D, aux.getPrice());
    }

    @Test
    @DisplayName("Verificar el ambiente más grande.")
    public void biggestRoom(){
        // Start
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setRooms(loadTwoRooms());
        HouseService houseService = new HouseService();

        // Act
        var aux = houseService.biggestRoom(houseDTO);

        // Assert
        assertEquals("Comedor", aux.getEnvironment_name());
    }

    @Test
    @DisplayName("Verificar dimensiones de cada ambiente.")
    public void sizes(){
        // Start
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setRooms(loadTwoRooms());
        HouseService houseService = new HouseService();

        // Act
        var aux = houseService.countSizes(houseDTO);

        // Assert
        assertEquals("Living", aux.getEnvironment_sizes().get(0).getEnvironment_name());
        assertEquals(50D, aux.getEnvironment_sizes().get(0).getSize());
        assertEquals("Comedor", aux.getEnvironment_sizes().get(1).getEnvironment_name());
        assertEquals(200D, aux.getEnvironment_sizes().get(1).getSize());
    }

    @Test
    @DisplayName("Verificar la carga de datos de muestra en el repositorio.")
    public void loadDistricts(){
        // Start
        DistrictsRepository repository = new DistrictsRepository();
        HouseService houseService = new HouseService(repository);
        String aux = "Barrios cargados: Centro, Chacarita, Valle Chico, Villa Reyes y Terrazas del Valle.";
        // Act
        var result = houseService.start();

        // Assert
        assertEquals(aux, result);
    }

}
