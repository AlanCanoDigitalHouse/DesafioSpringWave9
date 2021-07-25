package com.mercadolibre.desafio_testing.controller;

import com.mercadolibre.desafio_testing.dto.request.*;
import com.mercadolibre.desafio_testing.dto.response.*;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.service.*;
import com.mercadolibre.desafio_testing.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // Create a district.
    // Expects: a deserialized JSON in DistrictDTO's format.
    // Returns: a response with a 200 or 400 status.
    @PostMapping(ConstantsUtils.DISTRICT_ENDPOINT)
    public ResponseEntity<DistrictCreationReponseDTO> createDistrict(
            @RequestBody DistrictDTO district)
            throws DistrictAlreadyExistsException, DistrictNotValidException {
        return new ResponseEntity<>(
                this.cityService.createDistrict(district), HttpStatus.OK);
    }

    // Create a property.
    // Expects: a deserialized JSON in PropertyDTO's format.
    // Returns: a response with a 200 or 400 status.
    @PostMapping(ConstantsUtils.PROPERTY_ENDPOINT)
    public ResponseEntity<PropertyCreationResponseDTO> createProperty(
            @RequestBody PropertyDTO property)
            throws PropertyNotValidException, DuplicatedPropertyException,
            DistrictDoesNotExistException, DuplicateRoomException {
        return new ResponseEntity<>(
                this.cityService.createProperty(property), HttpStatus.OK);
    }

    // US-0001: get square meters of a property.
    // Expects: a property name on the URL.
    // Returns: a response with the square meters of the property in
    //  PropertySquareMetersResponseDTO0's format (status 200) or an error
    //  response (status 400).
    @GetMapping(ConstantsUtils.PROPERTY_ENDPOINT + "/{name}" +
            ConstantsUtils.SQR_MTS_ENDPOINT)
    public ResponseEntity<PropertySquareMetersResponseDTO> getSquareMeters(
            @PathVariable String name) throws PropertyDoesNotExistsException,
            DistrictDoesNotExistException {
        return new ResponseEntity<>(this.cityService.getSquareMeters(name),
                HttpStatus.OK);
    }

    // US-0002
    // Expects: a property name on the URL.
    // Returns: a response with the price of the property in
    //  PropertyPriceResponseDTO's format (status 200) or an error
    //  response (status 400).
    @GetMapping(ConstantsUtils.PROPERTY_ENDPOINT + "/{name}" +
            ConstantsUtils.PRICE_ENDPOINT)
    public ResponseEntity<PropertyPriceResponseDTO> getPrice(
            @PathVariable String name) throws DistrictDoesNotExistException,
            PropertyDoesNotExistsException {
        return new ResponseEntity<>(this.cityService.getPrice(name),
                HttpStatus.OK);
    }

    // US-0003
    // Expects: a property name on the URL.
    // Returns: a response with the biggest room of the property in
    //  BiggestRoomResponseDTO's format (status 200) or an error
    //  response (status 400).
    @GetMapping(ConstantsUtils.PROPERTY_ENDPOINT + "/{name}" +
            ConstantsUtils.BIG_ROOM_ENDPOINT)
    public ResponseEntity<BiggestRoomResponseDTO> getBiggestRoom(
            @PathVariable String name) throws PropertyDoesNotExistsException {
        return new ResponseEntity<>(this.cityService.getBiggestRoom(name),
                HttpStatus.OK);
    }

    // US-0004
    // Expects: a property name on the URL.
    // Returns: a response with the list of rooms of the property in
    //  RoomsSquareMetersResponseDTO's format (status 200) or an error
    //  response (status 400).
    @GetMapping(ConstantsUtils.PROPERTY_ENDPOINT + "/{name}" +
            ConstantsUtils.ROOMS_ENDPOINT + ConstantsUtils.SQR_MTS_ENDPOINT)
    public ResponseEntity<RoomsSquareMetersResponseDTO> getSquareMetersOfRooms(
            @PathVariable String name) throws PropertyDoesNotExistsException {
        return new ResponseEntity<>(
                this.cityService.getSquareMetersOfRooms(name), HttpStatus.OK);
    }
}
