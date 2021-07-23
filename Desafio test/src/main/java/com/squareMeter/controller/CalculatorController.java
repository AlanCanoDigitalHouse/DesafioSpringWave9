package com.squareMeter.controller;

import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculator")
public class CalculatorController {

    private final PropertyService propertyService;
    /**
     * Calculate the full square meters of a property in base a the differents environments passed
     * @param propertyRequestDTO data of the property
     * @return total squeare meters of the property
     */
    @PostMapping("/squareMeters")
    public ResponseEntity<PropertySquareMetersResponseDTO> getTotalMeters(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(propertyService.getHouseTotalSquareMeters(propertyRequestDTO), HttpStatus.OK);
    }

    /*@PostMapping("/value")
    public ResponseEntity<HouseValueDTO> getPrice(@Valid @RequestBody PropertyModel propertyModel) {
        return new ResponseEntity<>(propertyService.getHouseValue(propertyModel), HttpStatus.OK);
    }

    @PostMapping("/biggerRoom")
    public ResponseEntity<EnvironmentModel> getBiggerRoom(@Valid @RequestBody PropertyModel propertyModel) {
        return new ResponseEntity<>(propertyService.getBiggerRoom(propertyModel), HttpStatus.OK);
    }

    @PostMapping("/metersPerRoom")
    public ResponseEntity<List<AmbientMetersDTO>> getMetersPerRoom(@Valid @RequestBody PropertyModel propertyModel) {
        return new ResponseEntity<>(propertyService.getMetersPerRoom(propertyModel), HttpStatus.OK);
    }
*/
}
