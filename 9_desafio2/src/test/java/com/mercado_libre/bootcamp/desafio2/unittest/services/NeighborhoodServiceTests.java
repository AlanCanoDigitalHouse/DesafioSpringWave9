package com.mercado_libre.bootcamp.desafio2.unittest.services;

import com.mercado_libre.bootcamp.desafio2.exceptions.InvalidNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.neighborhood.implementation.NeighborhoodRepository;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.implementation.NeighborhoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NeighborhoodServiceTests {

    @Mock
    NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    NeighborhoodService neighborhoodService;

    Neighborhood validNeighborhood;

    Neighborhood invalidNeighborhood;


    @BeforeEach
    public void setUp() {
        validNeighborhood = new Neighborhood("Caseros", 40D);
        invalidNeighborhood = new Neighborhood("Haedo", 50D);

    }

    @Test
    public void itReturnsTrueIfNeighborhoodIsValid() {
        when(neighborhoodRepository.isNeighborhoodValid(validNeighborhood,40D)).thenReturn(true);
        Assertions.assertTrue(neighborhoodService.isNeighborhoodValid(validNeighborhood,40D));
        verify(neighborhoodRepository).isNeighborhoodValid(validNeighborhood,40D);
    }

    @Test
    public void findValidNeighborhooodIfExists() {
        when(neighborhoodRepository.findNeighborhoodByName("Caseros")).thenReturn(new Neighborhood("Caseros", 40D));
        Assertions.assertDoesNotThrow(() -> neighborhoodService.findNeighborhoodByName("Caseros"));
        verify(neighborhoodRepository).findNeighborhoodByName("Caseros");
    }

    @Test
    public void throwExceptionIfNeighborhoodIsntValid() {
        when(neighborhoodRepository.findNeighborhoodByName("Caseros")).thenReturn(null);
        Assertions.assertThrows(InvalidNeighborhoodException.class, () -> neighborhoodService.checkIfNeighborhoodIsValid("Caseros", 40D));
        verify(neighborhoodRepository).findNeighborhoodByName("Caseros");
    }


}
