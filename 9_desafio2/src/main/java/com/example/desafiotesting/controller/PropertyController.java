package com.example.desafiotesting.controller;

import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.ResponseDTO;
import com.example.desafiotesting.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/properties")
@Validated
public class PropertyController {

    PropertyService propertyService;

    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO calculate(@Valid  @RequestBody PropertyDTO property){
        return propertyService.calculateAll(property);
    }
}
