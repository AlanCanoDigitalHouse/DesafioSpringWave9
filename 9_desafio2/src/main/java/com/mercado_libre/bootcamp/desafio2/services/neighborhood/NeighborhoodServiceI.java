package com.mercado_libre.bootcamp.desafio2.services.neighborhood;

import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;

public interface NeighborhoodServiceI {

    boolean isNeighborhoodValid(Neighborhood neighborhood, double price);

    Neighborhood findNeighborhoodByName(String name);

    void checkIfNeighborhoodIsValid(String name, Double price);

}
