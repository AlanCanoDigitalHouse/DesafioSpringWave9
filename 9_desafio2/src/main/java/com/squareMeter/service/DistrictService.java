package com.squareMeter.service;

import com.squareMeter.exception.exception.DistrictNotExistsException;
import com.squareMeter.model.DistrictModel;
import com.squareMeter.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService {
    final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    //Validate if a district exists, for what nothing is returned?, if a district not exists a exception going be handled, in other case
    //this method don't make nothing
    @Override
    public void districtExists(String name) throws DistrictNotExistsException {
        DistrictModel districtModel = districtRepository.findDistrictByName(name);
    }
}
