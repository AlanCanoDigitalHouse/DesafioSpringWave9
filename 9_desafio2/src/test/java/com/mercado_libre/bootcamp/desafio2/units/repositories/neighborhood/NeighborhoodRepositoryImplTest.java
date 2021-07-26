package com.mercado_libre.bootcamp.desafio2.units.repositories.neighborhood;

import com.mercado_libre.bootcamp.desafio2.exceptions.NeighborhoodNotFoundException;
import com.mercado_libre.bootcamp.desafio2.repositories.NeighborhoodRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class NeighborhoodRepositoryImplTest {

    private static NeighborhoodRepositoryImpl neighborhoodRepository;

    @BeforeAll
    public static void setUp() throws IOException {
        neighborhoodRepository = new NeighborhoodRepositoryImpl();
        neighborhoodRepository.loadDatabase();
    }

    @Test
    @DisplayName("getNeighborhoods - Checks if the list size is equals to 3")
    void neighborhoodListSize_ok() {
        Assertions.assertEquals(3, neighborhoodRepository.getNeighborhoods().size());
    }

    @Test
    @DisplayName("findByName - OK")
    void findByName_ok() {
        Assertions.assertDoesNotThrow(() -> neighborhoodRepository.findNeighborhoodByName("Palermo"));
    }

    @Test
    @DisplayName("findByName - NeighborhoodNotFoundException")
    void findByName_NeighborhoodNotFoundException() {
        Assertions.assertThrows(NeighborhoodNotFoundException.class,
                () -> neighborhoodRepository.findNeighborhoodByName("Pompeya"));
    }

}
