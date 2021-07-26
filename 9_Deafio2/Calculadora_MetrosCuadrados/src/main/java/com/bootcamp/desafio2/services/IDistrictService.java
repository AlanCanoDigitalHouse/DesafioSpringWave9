package com.bootcamp.desafio2.services;

import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;

public interface IDistrictService {

    double findPriceByLocation(String districtName, double price) throws DistrictNotExistsException, PriceNotMatchException;
}
