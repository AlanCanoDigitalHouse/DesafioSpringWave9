package com.squareMeter.controller;

import com.squareMeter.dto.request.property.PropertyRequestDTO;
import com.squareMeter.dto.response.EnvironmentResponseDTO;
import com.squareMeter.dto.response.PropertyValueDTO;
import com.squareMeter.dto.response.PropertySquareMetersResponseDTO;
import com.squareMeter.exception.exception.DistrictNotExistsException;
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
     * Calculate the full square meters of a property in base a the different environments passed
     * @param propertyRequestDTO data of the property
     * @return total square meters of the property
     */
    @PostMapping("/squareMeters")
    public ResponseEntity<PropertySquareMetersResponseDTO> getTotalMeters(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) throws DistrictNotExistsException {
        return new ResponseEntity<>(propertyService.getHouseTotalSquareMeters(propertyRequestDTO), HttpStatus.OK);
    }

    /**
     * Calculate the price of a property in base of environments and district price
     * @param propertyModel data of the property
     * @return Value of the property
     * @throws DistrictNotExistsException In case of the district not exists in the database
     */
    @PostMapping("/value")
    public ResponseEntity<PropertyValueDTO> getPrice(@Valid @RequestBody PropertyRequestDTO propertyModel) throws DistrictNotExistsException {
        return new ResponseEntity<>(propertyService.getHouseValue(propertyModel), HttpStatus.OK);
    }

    /**
     * Calculate the biggest environment of a property
     * @param propertyRequestDTO data of the property
     * @return the biggest environment
     */
    @PostMapping("/biggerEnvironment")
    public ResponseEntity<EnvironmentResponseDTO> getBiggerRoom(@Valid @RequestBody PropertyRequestDTO propertyRequestDTO) {
        return new ResponseEntity<>(propertyService.getBiggerEnvironment(propertyRequestDTO), HttpStatus.OK);
    }
/*
    @PostMapping("/metersPerRoom")
    public ResponseEntity<List<AmbientMetersDTO>> getMetersPerRoom(@Valid @RequestBody PropertyModel propertyModel) {
        return new ResponseEntity<>(propertyService.getMetersPerRoom(propertyModel), HttpStatus.OK);
    }
*/
}
