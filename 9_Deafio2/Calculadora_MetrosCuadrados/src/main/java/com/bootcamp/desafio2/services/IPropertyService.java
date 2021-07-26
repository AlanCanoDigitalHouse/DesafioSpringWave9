package com.bootcamp.desafio2.services;

import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;

public interface IPropertyService {

    HouseResponseDTO calculateArea(HouseDTO casa) throws  DistrictNotExistsException, PriceNotMatchException;

}
