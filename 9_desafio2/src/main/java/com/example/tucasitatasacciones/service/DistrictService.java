package com.example.tucasitatasacciones.service;

import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;
import com.example.tucasitatasacciones.model.DistrictModel;
import com.example.tucasitatasacciones.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService {
    final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public void districtExists(String name) throws DistrictNotExistsException {
        DistrictModel districtModel = districtRepository.findDistrictByName(name);
    }
}
