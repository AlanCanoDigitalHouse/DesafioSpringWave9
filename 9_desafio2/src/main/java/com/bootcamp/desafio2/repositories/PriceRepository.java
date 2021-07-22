package com.bootcamp.desafio2.repositories;


import com.bootcamp.desafio2.entities.Neighborhood;

public interface PriceRepository {

    Neighborhood encontrarPrecio(String location);

}
