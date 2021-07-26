package com.bootcamp.desafio2.controller;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.DistrictNotFoundException;
import com.bootcamp.desafio2.exceptions.ErrorMessage;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/house")
public class PropertyController {

    @Autowired
    IPropertyService propertyService;

    @PostMapping("/calculateMeters")
    public ResponseEntity<ResponseDto> calculateArea(@Valid @RequestBody PropertyDto property)
            throws ErrorMessage, IOException, DistrictNotFoundException {
        return ResponseEntity.ok(propertyService.calculatePrice(property));
    }
}
