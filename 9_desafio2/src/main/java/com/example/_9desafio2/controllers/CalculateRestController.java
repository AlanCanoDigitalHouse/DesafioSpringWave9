package com.example._9desafio2.controllers;
import com.example._9desafio2.dtos.request.PropertyDTO;
import com.example._9desafio2.dtos.response.PropertyResponseDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;
import com.example._9desafio2.exceptions.PriceNotMatchException;
import com.example._9desafio2.services.ICalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

    @Autowired
    ICalculateService calculateService;

    @PostMapping("/calculate")
    public PropertyResponseDTO calculate(@Valid @RequestBody PropertyDTO propertyDTO) throws PriceNotMatchException, DistrictNotFoundException {
         return calculateService.calculate(propertyDTO);
    }
}


