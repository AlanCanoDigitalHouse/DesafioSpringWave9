package com.example._9desafio2.services;

import com.example._9desafio2.dtos.request.PropertyDTO;
import com.example._9desafio2.dtos.response.PropertyResponseDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;
import com.example._9desafio2.exceptions.PriceNotMatchException;

public interface ICalculateService {

     PropertyResponseDTO calculate(PropertyDTO house) throws PriceNotMatchException, DistrictNotFoundException;

}

