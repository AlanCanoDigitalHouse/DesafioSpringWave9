package com.mercadolibre.tucasitatasaciones.controllers;

import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.IHouseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/house")
@Validated
public class HouseController {

    private final IHouseService iHouseService;

    public HouseController(@Qualifier("houseService") IHouseService iHouseService) {
        this.iHouseService = iHouseService;
    }

    @PostMapping("/square-meters")
    public ResponseEntity<AssessmentDTO> calculateSquareMeters(@Valid @RequestBody HouseDTO house) throws DistrictNotFoundException {
        return ResponseEntity.ok(this.iHouseService.assessmentSquareMeters(house));
    }

    @PostMapping("/house-price")
    public ResponseEntity<AssessmentDTO> calculatePrice(@Valid @RequestBody HouseDTO house) throws DistrictNotFoundException {
        return ResponseEntity.ok(this.iHouseService.assessmentPrice(house));
    }

    @PostMapping("/house-biggest-room")
    public ResponseEntity<AssessmentDTO> calculateBiggestRoom(@Valid @RequestBody HouseDTO house) throws DistrictNotFoundException {
        return ResponseEntity.ok(this.iHouseService.assessmentBiggestRoom(house));
    }

    @PostMapping("/square-meters-rooms")
    public ResponseEntity<AssessmentDTO> calculateSquareMetersEachRoom(@Valid @RequestBody HouseDTO house) throws DistrictNotFoundException {
        return ResponseEntity.ok(this.iHouseService.assessmentSquareMetersEachRoom(house));
    }
}
