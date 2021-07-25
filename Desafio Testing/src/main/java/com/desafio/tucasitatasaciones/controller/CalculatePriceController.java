package com.desafio.tucasitatasaciones.controller;

import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculatePriceController {
    private final DistrictService districtService;

    public CalculatePriceController(DistrictService districtService){
        this.districtService = districtService;
    }

    @PostMapping
    public ResponseEntity<PropertyResponseDTO> getInfo(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(districtService.propertyInfo(propertyRequestDTO), HttpStatus.OK);
    }

}
