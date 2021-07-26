package com.example.tucasitatasacciones.controller;


import com.example.tucasitatasacciones.dto.request.property.PropertyRequestDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentMetersResponseDTO;
import com.example.tucasitatasacciones.dto.response.environment.EnvironmentResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertySquareMetersResponseDTO;
import com.example.tucasitatasacciones.dto.response.property.PropertyValueDTO;
import com.example.tucasitatasacciones.exception.exception.DistrictNotExistsException;
import com.example.tucasitatasacciones.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class RestCalculatorController {

    private final PropertyService propertyService;

    /**
     *US-0001: Calcular el total de metros cuadrados de una propiedad
     *
     */
    @PostMapping("/squareMeters")
    public ResponseEntity<PropertySquareMetersResponseDTO> getTotalMeters(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(propertyService.getHouseTotalSquareMeters(propertyRequestDTO), HttpStatus.OK);
    }


    /**
     US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas. Tener en cuenta que los precios por metro cuadrado están determinados según el barrio.
     */
    @PostMapping("/value")
    public ResponseEntity<PropertyValueDTO> getPrice(@Valid @RequestBody PropertyRequestDTO propertyModel) throws DistrictNotExistsException {
        return new ResponseEntity<>(propertyService.getHouseValue(propertyModel), HttpStatus.OK);
    }


    /**
     US-0003: Determinar cuál es el ambiente más grande.
     */
    @PostMapping("/biggerEnvironment")
    public ResponseEntity<EnvironmentResponseDTO> getBiggerRoom(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(propertyService.getBiggerEnvironment(propertyRequestDTO), HttpStatus.OK);
    }


    /**
     US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de una propiedad.
     */
    @PostMapping("/metersPerRoom")
    public ResponseEntity<List<EnvironmentMetersResponseDTO>> getMetersPerEnvironment(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(propertyService.getMetersPerEnvironment(propertyRequestDTO), HttpStatus.OK);
    }

}
