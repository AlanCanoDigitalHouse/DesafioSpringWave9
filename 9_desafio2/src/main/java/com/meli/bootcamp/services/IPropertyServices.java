package com.meli.bootcamp.services;

import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.exceptions.DistrictException;

public interface IPropertyServices {

    ValuationDTO valuation(PropertyDTO propertyDTO) throws DistrictException;

}
