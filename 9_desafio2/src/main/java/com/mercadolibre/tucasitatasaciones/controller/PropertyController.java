package com.mercadolibre.tucasitatasaciones.controller;

import com.mercadolibre.tucasitatasaciones.dto.request.PropertyRequestDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.EnvironmentAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.LargestEnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyTotalAreaDTO;
import com.mercadolibre.tucasitatasaciones.dto.response.PropertyValuationDTO;
import com.mercadolibre.tucasitatasaciones.service.IPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final IPropertyService propertyService;

    public PropertyController(IPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/totalArea")
    public ResponseEntity<PropertyTotalAreaDTO> calculatePropArea(@Valid @RequestBody PropertyRequestDTO propData) {
        var response = this.propertyService.calculatePropertyTotalArea(propData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/valuation")
    public ResponseEntity<PropertyValuationDTO> valuateProp(@Valid @RequestBody PropertyRequestDTO propData) {
        var response = this.propertyService.valuateProperty(propData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/environment/largest")
    public ResponseEntity<LargestEnvironmentDTO> determineLargestEnvironment(@Valid @RequestBody PropertyRequestDTO propData) {
        var response = this.propertyService.determineLargestEnvironment(propData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/environment/area")
    public ResponseEntity<List<EnvironmentAreaDTO>> calculateEnvironmentsArea(@Valid @RequestBody PropertyRequestDTO propData) {
        var response = this.propertyService.calculateEnvironmentsArea(propData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
