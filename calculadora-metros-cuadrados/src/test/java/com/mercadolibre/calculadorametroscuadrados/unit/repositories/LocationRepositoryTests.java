package com.mercadolibre.calculadorametroscuadrados.unit.repositories;

import com.mercadolibre.calculadorametroscuadrados.controller.CalculateRestController;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.repositories.LocationRepository;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import com.mercadolibre.calculadorametroscuadrados.utils.HouseRequestInitializer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocationRepositoryTests {

    LocationRepository repo;

    @BeforeEach
    void setUp () {
        this.repo = new LocationRepository();
    }

    @Test
    public void findExistingDistrict() {
        // assert
        assertTrue(repo.locationExists("Recoleta"));
    }

    @Test
    public void nonExistingDistrictReturnsFalse() {
        // assert
        assertFalse(repo.locationExists("Purmamarca"));
    }
}
