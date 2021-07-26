package com.mercadolibre.TuCasitaTasacionesDesafioTesting.controllers;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.request.HouseRequestDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.HouseResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.dto.response.RoomResponseDto;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.service.ITuCasitaTasacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/TuCasitaTasaciones")

public class TuCasitaTasacionesController {

    private final ITuCasitaTasacionesService service;

    public TuCasitaTasacionesController(ITuCasitaTasacionesService service) {
        this.service = service;
    }

    private HouseResponseDto response = new HouseResponseDto();

    /**
     * Save the Request data
     *
     * @param house
     * @return STATUS CREATED - (201)
     */
    @PostMapping("/saveData")
    public ResponseEntity<HouseResponseDto> saveData(@RequestBody @Valid HouseRequestDto house) {
        service.enterData(house);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * US-0001
     * Calculate the total square meters of a property
     * @return response, status OK - 200
     */

    @GetMapping("/houseArea")
    public ResponseEntity<HouseResponseDto> calculateArea() {
        response = service.calculateHouseArea();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * US-0002
     * Show the value of a property based on its environments and measurements. The price per square meter are determined by neighborhood.
     * @return response, status OK - 200
     */
    @GetMapping("/priceByLocation")
    public ResponseEntity<HouseResponseDto> calculatePriceByLocation() {
        response = service.calculatePriceByLocation();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * US-0003
     * Calculate which is the biggest room
     * @return responseRoom, status OK - 200
     */
    @GetMapping("/biggestRoom")
    public ResponseEntity<RoomResponseDto> calculateBiggestRoom() {
        RoomResponseDto responseRoom = service.calculateBiggestRoom();
        return new ResponseEntity<>(responseRoom, HttpStatus.OK);
    }

    /**
     * US-0004
     * Show the amount of square meters that each room of a property has.
     * @return response, status OK - 200
     */
    @GetMapping("/roomArea")
    public ResponseEntity<HouseResponseDto> calculateRoomArea() {
        response = service.calculateRoomArea();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
