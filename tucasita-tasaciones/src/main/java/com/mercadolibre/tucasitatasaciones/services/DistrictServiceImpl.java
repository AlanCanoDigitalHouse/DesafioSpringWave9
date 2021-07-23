package com.mercadolibre.tucasitatasaciones.services;

import com.mercadolibre.tucasitatasaciones.dtos.req.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.interfaces.DistrictRepository;
import com.mercadolibre.tucasitatasaciones.services.interfaces.DistrictService;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public DistrictDTO findDistrictBy(String name) throws DistrictNotFoundException {
        return districtRepository.findDistrictBy(name);
    }

}
