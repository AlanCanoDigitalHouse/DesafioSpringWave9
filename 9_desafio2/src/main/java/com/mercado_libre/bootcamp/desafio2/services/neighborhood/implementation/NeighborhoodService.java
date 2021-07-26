package com.mercado_libre.bootcamp.desafio2.services.neighborhood.implementation;

import com.mercado_libre.bootcamp.desafio2.exceptions.InvalidNeighborhoodException;
import com.mercado_libre.bootcamp.desafio2.models.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.repositories.neighborhood.implementation.NeighborhoodRepository;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.NeighborhoodServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeighborhoodService implements NeighborhoodServiceI {

    @Autowired
    NeighborhoodRepository repository;

    @Override
    public boolean isNeighborhoodValid(Neighborhood neighborhood, double price) {
        return repository.isNeighborhoodValid(neighborhood, price);
    }

    @Override
    public Neighborhood findNeighborhoodByName(String name) {
        return repository.findNeighborhoodByName(name);
    }

    @Override
    public void checkIfNeighborhoodIsValid(String name, Double price) {
        Neighborhood neighborhood = findNeighborhoodByName(name);
        if (!isNeighborhoodValid(neighborhood,price))
            throw new InvalidNeighborhoodException("El precio del districto no es correcto");
    }

}
