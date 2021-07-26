package com.mercadolibre.tucasitatasaciones.services.implementations;

import com.mercadolibre.tucasitatasaciones.dtos.request.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.repositories.IDistrictRepository;
import com.mercadolibre.tucasitatasaciones.services.IDistrictService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("districtService")
public class DistrictServiceImpl implements IDistrictService {

    private final IDistrictRepository iDistrictRepository;

    public DistrictServiceImpl(@Qualifier("districtRepository") IDistrictRepository iDistrictRepository) {
        this.iDistrictRepository = iDistrictRepository;
    }


    @Override
    public DistrictDTO findByName(String location) throws DistrictNotFoundException {
        District district = this.iDistrictRepository.findByName(location);
        DistrictDTO districtDTO;
        if (Objects.nonNull(district)){
            districtDTO =  new DistrictDTO(district.getLocation(), district.getPrice());
        }
        else {
            districtDTO = null;
        }
        return districtDTO;
    }
}
