package com.mercado_libre.bootcamp.desafio2.units.services.neighborhood;

import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.exceptions.NeighborhoodServiceImplException;
import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.NeighborhoodRepository;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.NeighborhoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NeighborhoodServiceImplTest {

    @InjectMocks
    private NeighborhoodServiceImpl neighborhoodService;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @Test
    @DisplayName("checkIfNeighborhoodExists - Happy case")
    void checkIfNeighborhoodExists_ok() {
        when(neighborhoodRepository.findNeighborhoodByName("Palermo"))
                .thenReturn(new Neighborhood("Palermo", 2000.00));

        DistrictDTO district = new DistrictDTO("Palermo", 2000.00);

        Assertions.assertDoesNotThrow(() -> neighborhoodService.checkIfNeighborhoodExists(district));
        verify(neighborhoodRepository, atMostOnce()).findNeighborhoodByName("Palermo");
    }

    @Test
    @DisplayName("checkIfNeighborhoodExists - NeighborhoodServiceImplException")
    void checkIfNeighborhoodExists_neighborhoodServiceImplException() {
        when(neighborhoodRepository.findNeighborhoodByName("Palermo"))
                .thenReturn(new Neighborhood("Palermo", 1000.00));

        DistrictDTO district = new DistrictDTO("Palermo", 2000.00);
        String expectedMessage = "El precio ingresado [" + district.getDistrictPrice() + "] no coincide con el precio almacenado en la base de datos.";

        NeighborhoodServiceImplException exception = assertThrows(NeighborhoodServiceImplException.class, () -> neighborhoodService.checkIfNeighborhoodExists(district));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("getNeighborhoods - Checks if the list size is equals to 3")
    void getNeighborhoods_ok() {
        when(neighborhoodRepository.getNeighborhoods())
                .thenReturn(generateMockList());

        List<Neighborhood> neighborhoods = neighborhoodService.getNeighborhoods();

        assertEquals(3, neighborhoods.size());
        verify(neighborhoodRepository, atMostOnce()).getNeighborhoods();
    }

    private List<Neighborhood> generateMockList() {
        List<Neighborhood> neighborhoods = new ArrayList<>();
        neighborhoods.add(new Neighborhood("Palermo", 2000.00));
        neighborhoods.add(new Neighborhood("Nuñez", 2500.00));
        neighborhoods.add(new Neighborhood("Pompeya", 1000.00));
        return neighborhoods;
    }
}
