package com.squareMeter.service;

import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.DistrictModel;
import com.squareMeter.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {
    DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public DistrictModel findDistrictByName(String name) throws DistrictNotExistsException {
        return districtRepository.findDistrictByName(name);
    }
    public boolean districtExists(String name) throws DistrictNotExistsException {
        DistrictModel districtModel = districtRepository.findDistrictByName(name);
        return true;
    }
}
