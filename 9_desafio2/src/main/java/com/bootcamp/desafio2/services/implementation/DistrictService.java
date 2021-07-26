package com.bootcamp.desafio2.services.implementation;

import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.services.IDistrictService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictService implements IDistrictService {

    IDistrictRepository districtRepository;

    public DistrictService(IDistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public boolean neighborhoodExist(String name, Double price) throws IOException {
        return districtRepository.districtExist(name, price);
    }


}
