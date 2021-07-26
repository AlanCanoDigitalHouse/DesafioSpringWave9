package cl.mercadolibre.desfiotesting.services;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;

public interface IDistrictService {

    Boolean existsDistrictByDTO(DistrictDTO district);

}
