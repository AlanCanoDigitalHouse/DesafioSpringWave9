package com.mercado_libre.bootcamp.desafio2.repositories.neighborhood;

import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;

public interface NeighborhoodRepositoryI {

    boolean isNeighborhoodValid(Neighborhood neighborhood, double price);

    Neighborhood findNeighborhoodByName(String name);

    void addNewNeighborhood(Neighborhood neighborhood);
}
