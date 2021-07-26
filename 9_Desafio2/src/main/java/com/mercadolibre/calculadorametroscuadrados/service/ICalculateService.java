package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.ResponseOkDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DistrictDoesntExistException;

import java.util.List;

public interface ICalculateService {

    public HouseResponseDTO calculate(HouseDTO house) throws DistrictDoesntExistException;

    public ResponseOkDTO loadDistricts (List<String> districtsList);

}
