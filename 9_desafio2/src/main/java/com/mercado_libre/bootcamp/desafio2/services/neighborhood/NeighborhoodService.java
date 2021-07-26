package com.mercado_libre.bootcamp.desafio2.services.neighborhood;

import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;

import java.util.List;

public interface NeighborhoodService {

    void checkIfNeighborhoodExists(DistrictDTO district);

    List<Neighborhood> getNeighborhoods();
}
