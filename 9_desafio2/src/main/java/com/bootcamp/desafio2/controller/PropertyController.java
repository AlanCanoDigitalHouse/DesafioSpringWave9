package com.bootcamp.desafio2.controller;

import com.bootcamp.desafio2.dtos.request.CasaDto;
import com.bootcamp.desafio2.dtos.response.CasaResponseDto;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/home")
public class PropertyController {

    @Autowired
    IPropertyService propertyService;

    @PostMapping("/calcular-area")
    public ResponseEntity<CasaResponseDto> calcularArea(@Valid @RequestBody CasaDto casa) throws ErrorMessage {
        return ResponseEntity.ok(propertyService.calcularArea(casa));
    }
}
