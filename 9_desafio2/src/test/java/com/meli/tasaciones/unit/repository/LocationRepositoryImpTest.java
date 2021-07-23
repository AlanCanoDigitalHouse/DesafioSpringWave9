package com.meli.tasaciones.unit.repository;

import com.meli.tasaciones.exception.LocationNotFoundException;
import com.meli.tasaciones.model.Location;
import com.meli.tasaciones.repository.LocationRepository;
import com.meli.tasaciones.repository.LocationRepositoryImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class LocationRepositoryImpTest {
  LocationRepository repo;

  @BeforeEach
  void setUp() {
    repo = new LocationRepositoryImp();
  }

  @Test
  void givenValidLocationWhenGetPriceThenValidPrice() throws LocationNotFoundException {
    Double price = repo.getLocationPrice("Palermo");
    Assertions.assertEquals(1000.0, price);
  }

  @Test
  void givenInvalidLocationWhenGetPriceThenExceptionThrown() throws LocationNotFoundException {
    Assertions.assertThrows(LocationNotFoundException.class,
            () -> repo.getLocationPrice("Invalid"));
  }

  @Test
  void whenGetLocationsThenNotEmpty() {
    List<Location> locations = repo.getLocations();
    Assertions.assertFalse(locations.isEmpty());
  }
}