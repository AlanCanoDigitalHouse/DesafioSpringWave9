package com.meli.tasaciones.repository;

import com.meli.tasaciones.exception.LocationNotFoundException;
import com.meli.tasaciones.model.Location;

import java.util.List;

public interface LocationRepository {
  Double getLocationPrice(String location) throws LocationNotFoundException;

  List<Location> getLocations(String location);
}
