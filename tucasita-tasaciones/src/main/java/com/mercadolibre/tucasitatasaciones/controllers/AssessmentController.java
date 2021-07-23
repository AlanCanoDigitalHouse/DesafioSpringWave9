package com.mercadolibre.tucasitatasaciones.controllers;

import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.dtos.res.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.interfaces.PropertyAssessmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/property")
public class AssessmentController {

    private final PropertyAssessmentService assessmentService;

    public AssessmentController(PropertyAssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    /* TODO: US-0001: Calcular el total de metros cuadrados de una propiedad */
    @PostMapping(path = "/dimension")
    public ResponseEntity<AssessmentDTO> calcPropDimension(@Valid @RequestBody PropertyDTO prop) throws DistrictNotFoundException {
        return new ResponseEntity<>(assessmentService.calcDimensionM2(prop), HttpStatus.OK);
    }

    /* TODO: US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas.
             Tener en cuenta que los precios por metro cuadrado están determinados según el
             barrio. */
    @PostMapping(path = "/price")
    public ResponseEntity<AssessmentDTO> calcPropPrice(@Valid @RequestBody PropertyDTO prop) throws DistrictNotFoundException {
        return new ResponseEntity<>(assessmentService.calcPrice(prop), HttpStatus.OK);
    }

    /* TODO: US-0003: Determinar cuál es el ambiente más grande. /*/
    @PostMapping(path = "/rooms/biggest")
    public ResponseEntity<AssessmentDTO> getBiggestRoom(@Valid @RequestBody PropertyDTO prop) throws DistrictNotFoundException {
        return new ResponseEntity<>(assessmentService.getBiggestRoom(prop), HttpStatus.OK);
    }

    /* TODO: US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de
             una propiedad. */
    @PostMapping(path = "/rooms/dimensions")
    public ResponseEntity<AssessmentDTO> getRoomsDimensions(@Valid @RequestBody PropertyDTO prop) throws DistrictNotFoundException {
        return new ResponseEntity<>(assessmentService.getRoomsDimensions(prop), HttpStatus.OK);
    }

}
