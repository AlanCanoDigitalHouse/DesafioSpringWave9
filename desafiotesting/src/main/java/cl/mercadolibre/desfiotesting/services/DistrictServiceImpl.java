package cl.mercadolibre.desfiotesting.services;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;
import cl.mercadolibre.desfiotesting.repositories.IRepositoryDistrict;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements IDistrictService{

    private final IRepositoryDistrict repositoryDistrict;

    public DistrictServiceImpl(IRepositoryDistrict repositoryDistrict) {
        this.repositoryDistrict = repositoryDistrict;
    }

    @Override
    public Boolean existsDistrictByDTO(DistrictDTO district){
        DistrictDTO districtDTO = this.repositoryDistrict.findDistrictByDTO(district);
        return districtDTO !=null;
    }

}
