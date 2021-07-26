package com.bootcamp.desafio2.services.implementation;

import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.services.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService {

    @Autowired
    IDistrictRepository districtRepository;

    public double findPriceByLocation(String districtName, double price) throws DistrictNotExistsException, PriceNotMatchException {

        double vPrice = districtRepository.findPriceByLocation(districtName).getPrice();

        if (!(vPrice == price))
            throw new PriceNotMatchException("El precio ingresado no coincide con el que se encuentra en la bd.");
        else
            return vPrice;
    }

}
