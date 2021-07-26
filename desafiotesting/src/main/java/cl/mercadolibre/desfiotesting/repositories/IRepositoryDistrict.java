package cl.mercadolibre.desfiotesting.repositories;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;

public interface IRepositoryDistrict {

    DistrictDTO findDistrictByDTO(DistrictDTO districtDTO);

}
