package com.meli.bootcamp.controllers;

import com.meli.bootcamp.dto.request.PropertyDTO;
import com.meli.bootcamp.dto.ValuationDTO;
import com.meli.bootcamp.exceptions.DistrictException;
import com.meli.bootcamp.services.PropertyServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping()
public class PropertyRestController {

    @Autowired
    PropertyServicesImp propertyServices;

    /**
     * @param propertyDTO property to be valuated, containing name, district, ans list of environments
     * @return ValuationDTO contain area & valuation of the property (US-0001, US-0002), the biggest environment
     * (US-0003), and the details of each environment (US-0004)
     */
    @PostMapping("/valuation")
    public ValuationDTO valuation(@Valid @RequestBody PropertyDTO propertyDTO) throws DistrictException {
        return propertyServices.valuation(propertyDTO);
    }
}
