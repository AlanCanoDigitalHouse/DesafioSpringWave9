package com.mercado_libre.bootcamp.desafio2.controllers;

import com.mercado_libre.bootcamp.desafio2.model.Neighborhood;
import com.mercado_libre.bootcamp.desafio2.services.neighborhood.NeighborhoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/information")
public class InformationController {

    private final NeighborhoodService neighborhoodService;

    public InformationController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping("/neighborhoods")
    public ResponseEntity<List<Neighborhood>> getNeighborhoods() {
        return new ResponseEntity<>(neighborhoodService.getNeighborhoods(), HttpStatus.OK);
    }
}
