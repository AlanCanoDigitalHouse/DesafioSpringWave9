package com.mercado_libre.bootcamp.desafio2.unittest.repositories.neighborhood;

import com.mercado_libre.bootcamp.desafio2.exceptions.UnexistingNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.neighborhood.implementation.NeighborhoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NeighborhoodRepositoryTests {
    NeighborhoodRepository repository;

    @BeforeEach
    private void setUp() {
        repository = new NeighborhoodRepository();
        repository.addNewNeighborhood(new Neighborhood("Tres de Febrero",30D));
    }

    @Test
    public void searchingUnexistingDistrictThrowsException() {
        Assertions.assertThrows(UnexistingNeighborhoodException.class,() -> repository.findNeighborhoodByName("Caseros"));
    }

    @Test
    public void searchingExistingDistrictReturnsDistrict() {
        Neighborhood neighborhoodResult = repository.findNeighborhoodByName("Tres de Febrero");
        Neighborhood neighborhoodExpected = new Neighborhood("Tres de Febrero",40D);
        Assertions.assertEquals(neighborhoodExpected.getDistrict_name(), neighborhoodResult.getDistrict_name());
    }

    @Test
    public void createExistentDistrict() {
        Neighborhood neighborhood = new Neighborhood("Haedo",50D);
        repository.addNewNeighborhood(neighborhood);
        Assertions.assertTrue(repository.isNeighborhoodValid(neighborhood,50D));
    }

}
