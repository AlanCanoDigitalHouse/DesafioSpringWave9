package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repository.IDistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService implements IDistrictService{

    private final IDistrictRepository districtRepository;

    public DistrictService(IDistrictRepository districtRepository) { this.districtRepository = districtRepository; }

    @Override
    public void existDistrict(DistrictDTO district, String path) throws DistrictNotFoundException {
        districtRepository.findNameDistrict(district.getDistrict_name(), path);
    }
}
