package cl.mercadolibre.desfiotesting.services;

import cl.mercadolibre.desfiotesting.DTOs.EstateDTO;
import cl.mercadolibre.desfiotesting.DTOs.EnvironmentDTO;
import cl.mercadolibre.desfiotesting.DTOs.responseDTOs.EnvironmentWithSize;
import cl.mercadolibre.desfiotesting.exceptions.DistrictNotFoundException;

import java.util.List;

public interface IEstateService {

    Double calculateEstateSize(EstateDTO estateDTO) throws DistrictNotFoundException;

    Double calculateEstatePrice(EstateDTO estateDTO) throws DistrictNotFoundException;

    EnvironmentDTO calculateBiggestEnvironment(EstateDTO estateDTO);

    List<EnvironmentWithSize> calculateSizeOfEachEnvironment(EstateDTO estateDTO);

}
