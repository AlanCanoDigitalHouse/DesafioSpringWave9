package com.mercado_libre.bootcamp.desafio2.unittest.repositories.neighborhood;

import com.mercado_libre.bootcamp.desafio2.TestUtilsGenerator;
import com.mercado_libre.bootcamp.desafio2.dtos.request.NeighborhoodRequestDTO;
import com.mercado_libre.bootcamp.desafio2.exceptions.UnableToAddDuplicatedNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.exceptions.UnexistingNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.neighborhood.implementation.NeighborhoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NeighborhoodRepositoryTests {
    NeighborhoodRepository repository;

    @BeforeEach
    private void setUp() {
        TestUtilsGenerator.emptyDistrictsFile();
        NeighborhoodRequestDTO neighborhood = new NeighborhoodRequestDTO("Caseros", 40D);
        TestUtilsGenerator.appendNewNeighborhood(neighborhood);
        repository = new NeighborhoodRepository();
        repository.addNewNeighborhood(new Neighborhood("Tres de Febrero",30D));
    }

    @Test
    public void searchingUnexistingDistrictThrowsException() throws FileNotFoundException {
        Assertions.assertThrows(UnexistingNeighborhoodException.class,() -> repository.findNeighborhoodByName("Caseros"));
    }

    @Test
    public void searchingExistingDistrictReturnsDistrict() {
        Neighborhood neighborhoodResult = repository.findNeighborhoodByName("Tres de Febrero");
        Neighborhood neighborhoodExpected = new Neighborhood("Tres de Febrero",40D);
        Assertions.assertEquals(neighborhoodExpected.getDistrict_name(), neighborhoodResult.getDistrict_name());
    }

    @Test
    public void createExistingDistrict() {
        Neighborhood neighborhood = new Neighborhood("Haedo",50D);
        repository.addNewNeighborhood(neighborhood);
        Assertions.assertTrue(repository.isNeighborhoodValid(neighborhood,50D));
    }

    @Test
    public void createExistingDistrictThrowException() {
        Neighborhood neighborhood = new Neighborhood("Haedo",50D);
        repository.addNewNeighborhood(neighborhood);
        Assertions.assertThrows(UnableToAddDuplicatedNeighborhoodException.class,()-> repository.addNewNeighborhood(neighborhood));
    }

    @Test
    public void loadingDataFromFileGetsTwoDistricts() throws IOException {
       repository.loadDistricts();
       Assertions.assertEquals(2,repository.sizeOfNeighborhood());
    }

}
