package com.mercadolibre.tucasita.controller;

import com.mercadolibre.tucasita.dto.PropertyDTO;
import com.mercadolibre.tucasita.dto.response.PropertyInfoResponseDTO;
import com.mercadolibre.tucasita.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Property Info Controller provides an endpoint for consulting a Property information like total square feet, bigger
 * room, square feet per room and the property total price. The endpoint getPropertyInfo receives a {@link PropertyDTO}
 * and returns {@link PropertyInfoResponseDTO} containing requested information.
 */
@RestController
@RequestMapping
public class PropertyInfoController {
    private PropertyService propertyService;

    public PropertyInfoController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /** Receives a {@link PropertyDTO} with some property info and returns the property measures and price.
     * @param propertyDTO a DTO containing property data.
     * @return {@link PropertyInfoResponseDTO}
     */
    @PostMapping("/propertyInfo")
    public ResponseEntity<PropertyInfoResponseDTO> getPropertyInfo(@RequestBody @Valid PropertyDTO propertyDTO) {
        return new ResponseEntity<>(propertyService.getInfo(propertyDTO), HttpStatus.OK);
    }

}
