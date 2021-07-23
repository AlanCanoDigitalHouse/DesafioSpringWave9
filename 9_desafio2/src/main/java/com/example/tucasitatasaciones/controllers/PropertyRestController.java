package com.example.tucasitatasaciones.controllers;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;
import com.example.tucasitatasaciones.services.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/property")
public class PropertyRestController {

    PropertyService propertyService;

    public PropertyRestController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * US-0001: Calcular el total de metros cuadrados de una propiedad
     * US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas.
     * Tener en cuenta que los precios por metro cuadrado están determinados según el
     * barrio.
     * US-0003: Determinar cuál es el ambiente más grande.
     * US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de
     * una propiedad.
     * @param property
     * @return ResponseEntity<PropertyResponseDTO>
     * @throws DistrictException
     */
    @PostMapping("/calculate")
    public ResponseEntity<PropertyResponseDTO> calculateProperty(
            @Valid @RequestBody PropertyDTO property
            ) throws DistrictException {
        return new ResponseEntity<>(propertyService.calculateProperty(property), HttpStatus.OK);
    }
}
