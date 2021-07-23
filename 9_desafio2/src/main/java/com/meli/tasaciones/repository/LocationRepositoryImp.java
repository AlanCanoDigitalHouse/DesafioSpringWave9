package com.meli.tasaciones.repository;

import com.meli.tasaciones.exception.LocationNotFoundException;
import com.meli.tasaciones.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class LocationRepositoryImp implements LocationRepository {

  private final Map<String, Location> locations;

  public LocationRepositoryImp(Map<String, Location> locations) {
    this.locations = locations;
  }

  public LocationRepositoryImp() {
    this.locations = loadData();
  }

  private Map<String, Location> loadData() {
    HashMap<String, Location> map = new HashMap<>();
    map.put("Palermo", new Location("Palermo", 1000.0));
    map.put("Belgrano", new Location("Belgrano", 1100.0));
    map.put("Recoleta", new Location("Recoleta", 900.0));
    map.put("Puerto Madero", new Location("Puerto Madero", 2000.0));
    return map;
  }

  @Override
  public Double getLocationPrice(String locationName) throws LocationNotFoundException {
    Location location = locations.get(locationName);
    if (location == null) {
      throw new LocationNotFoundException("El barrio " + locationName + " no existe");
    }
    return location.getPrice();
  }

  @Override
  public List<Location> getLocations(String location) {
    return new ArrayList<>(locations.values());
  }

}
