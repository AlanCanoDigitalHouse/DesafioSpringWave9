package com.kjcelis.calculadora_mts.controller;

import com.kjcelis.calculadora_mts.dto.request.HouseRequestDTO;
import com.kjcelis.calculadora_mts.dto.response.HouseResponseDTO;
import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;
import com.kjcelis.calculadora_mts.services.CalculateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HouseController {

    private final CalculateService calculateService;

    public HouseController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<HouseResponseDTO> calculeInfoProp(@Valid @RequestBody HouseRequestDTO house) throws NotFoundDistricException {
        return new ResponseEntity<>(calculateService.calculate(house), HttpStatus.OK);

    }
}


