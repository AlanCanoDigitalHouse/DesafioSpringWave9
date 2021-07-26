package com.mercadolibre.squaremeter.controllers;

import com.mercadolibre.squaremeter.dtos.DistrictDTO;
import com.mercadolibre.squaremeter.dtos.request.Home;
import com.mercadolibre.squaremeter.dtos.response.InfoHome;
import com.mercadolibre.squaremeter.exceptions.DistrictNotExist;
import com.mercadolibre.squaremeter.services.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictControllers {

    DistrictService service;

    public DistrictControllers(DistrictService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DistrictDTO>> getAllDistrict() {
        return new ResponseEntity<>(service.getAllDistrict(), HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<InfoHome> postHouseReport(@Valid @RequestBody Home home) throws DistrictNotExist {
        return new ResponseEntity<>(service.postHouseReport(home), HttpStatus.OK);
    }

}