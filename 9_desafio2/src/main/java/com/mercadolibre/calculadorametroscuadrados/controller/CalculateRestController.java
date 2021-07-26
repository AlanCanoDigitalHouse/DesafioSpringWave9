package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.PropertyDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.*;
import com.mercadolibre.calculadorametroscuadrados.exception.apiValidationException.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/property")
@Validated
public class CalculateRestController {

    @Autowired
    CalculateService calculateService;

    /*TODO: US-0001  Total de metros cuadrados de una propiedad*/
    @PostMapping("/calculateTotalSquareMeters")
    public ResponseEntity<PropertyTotalSquareMetersResponse> calculateTotalSquareMeters(@Valid @RequestBody PropertyDTO house) throws DistrictNotFoundException {
        return new ResponseEntity<>(calculateService.calculateTotalSquareMeters(house), HttpStatus.OK);
    }

    /*TODO: US-0002 Valor de una propiedad*/
    @PostMapping("/calculatePropertyValue")
    public ResponseEntity<PropertyValueResponse> calculatePropertyValue(@Valid @RequestBody PropertyDTO house) throws DistrictNotFoundException {
        return new ResponseEntity<>(calculateService.calculatePropertyValue(house),HttpStatus.OK);
    }

    /*TODO: US-0003 Ambiente más grande*/
    @PostMapping("/calculateBiggerEnvironment")
    public ResponseEntity<PropertyEnvironmentBiggerResponse> calculateBiggerEnviroment(@Valid @RequestBody PropertyDTO house) throws DistrictNotFoundException {
        return new ResponseEntity<>(calculateService.calculateBiggerEnvironment(house),HttpStatus.OK);
    }
    /*
    /*TODO: US-0004 Cantidad de metros cuadrados por ambiente*/
    @PostMapping("/calculateTotalSquareMetersEnvironments")
    public ResponseEntity<PropertyEnvironmentSquareMetersResponse> calculateTotalSquarMetersEnviroment(@Valid @RequestBody PropertyDTO house) throws DistrictNotFoundException {
        return new ResponseEntity<>(calculateService.calculateTotalSquareMetersEnviroment(house),HttpStatus.OK);
    }
}
