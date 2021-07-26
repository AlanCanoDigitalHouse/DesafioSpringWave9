package com.bootcamp.desafio2.controller;

import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class PropertyController {

    @Autowired
    IPropertyService propertyService;

    @PostMapping("/calculate")
    public ResponseEntity<HouseResponseDTO> getArea(@Valid @RequestBody HouseDTO casa) throws DistrictNotExistsException, PriceNotMatchException {
            return ResponseEntity.ok(propertyService.calculateArea(casa));
    }
}
