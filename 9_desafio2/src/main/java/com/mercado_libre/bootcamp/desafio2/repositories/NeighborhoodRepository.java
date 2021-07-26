package com.mercado_libre.bootcamp.desafio2.repositories;

import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;

import java.util.List;

public interface NeighborhoodRepository {

    List<Neighborhood> getNeighborhoods();

    Neighborhood findNeighborhoodByName(String name);
}
