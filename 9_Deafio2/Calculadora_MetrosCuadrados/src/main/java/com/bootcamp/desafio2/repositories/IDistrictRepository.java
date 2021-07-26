package com.bootcamp.desafio2.repositories;


import com.bootcamp.desafio2.entities.District;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;

public interface IDistrictRepository {

    District findPriceByLocation(String location) throws DistrictNotExistsException, PriceNotMatchException;

}
