package com.bootcamp.desafio2.services;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.BusinessException;

import java.io.IOException;


public interface IPropertyService {

    ResponseDto calculatePrice(PropertyDto property) throws BusinessException, IOException, DistrictNotFoundException;

}
