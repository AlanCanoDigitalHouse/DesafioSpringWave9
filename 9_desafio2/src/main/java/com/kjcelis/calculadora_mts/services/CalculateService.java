package com.kjcelis.calculadora_mts.services;

import com.kjcelis.calculadora_mts.dto.request.EnvironmentDTO;
import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;
import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;

import java.util.List;
import java.util.Map;


public interface CalculateService {
    HouseResponseDTO calculate(HouseRequestDTO house) throws NotFoundDistricException;

    double totalAmountMts(List<EnvironmentDTO> environments);

    String largerEnvironment(List<EnvironmentDTO> environments);

    Map<String, Double> environmentsMts(List<EnvironmentDTO> environments);

    double calculatePrice(double result, String districtName, double districtPrice);
}
