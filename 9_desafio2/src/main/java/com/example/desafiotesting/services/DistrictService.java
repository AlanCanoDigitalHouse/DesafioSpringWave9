package com.example.desafiotesting.services;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;
import com.example.desafiotesting.models.District;
import com.example.desafiotesting.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService {
    DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public DistrictDTO getDistrictByName(String name) throws DistrictException, FileException {
        District district = this.districtRepository.findByName(name);
        return new DistrictDTO(district.getLocation(), district.getPrice());
    }
}
