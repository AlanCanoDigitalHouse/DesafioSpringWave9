package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.DistrictDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropertyDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.EnvironmentTotalMetersResponse;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;

import java.util.List;

public interface IEnviromentService {
    public Double calculateRoomSquareFeet(PropertyDTO house) throws DistrictNotFoundException;
    public List<EnvironmentTotalMetersResponse> calculateTotalSquareMetersEnviroment(PropertyDTO house) throws DistrictNotFoundException;
    public EnvironmentDTO calculateBiggerEnvironment(PropertyDTO house) throws DistrictNotFoundException;
    public Double calculatePrice(DistrictDTO district, Double totalSquare) throws DistrictNotFoundException;
}
