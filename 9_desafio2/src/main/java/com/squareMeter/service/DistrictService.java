package com.squareMeter.service;

import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.DistrictModel;
import com.squareMeter.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {
    final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public void districtExists(String name) throws DistrictNotExistsException {
        DistrictModel districtModel = districtRepository.findDistrictByName(name);
    }
}
