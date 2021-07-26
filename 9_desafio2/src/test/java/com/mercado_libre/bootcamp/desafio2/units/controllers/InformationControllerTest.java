package com.mercado_libre.bootcamp.desafio2.units.controllers;

import com.mercado_libre.bootcamp.desafio2.controllers.InformationController;
import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.NeighborhoodService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InformationControllerTest {

    @InjectMocks
    private InformationController informationController;

    @Mock
    private NeighborhoodService neighborhoodService;

    @Test
    @DisplayName("GET - /information/neighborhoods - 200")
    void calculate_ok() {
        ResponseEntity<List<Neighborhood>> response = informationController.getNeighborhoods();

        verify(neighborhoodService, atMostOnce()).getNeighborhoods();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
