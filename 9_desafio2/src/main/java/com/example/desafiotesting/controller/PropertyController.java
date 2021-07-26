package com.example.desafiotesting.controller;

import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.ResponseDTO;
import com.example.desafiotesting.exception.DistrictNotFoundException;
import com.example.desafiotesting.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController()
@RequestMapping("/properties")
public class PropertyController {

    PropertyService propertyService;

    public PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    /**
     *
     * @param property
     * @return
     * @throws DistrictNotFoundException
     */
    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO calculate(@Valid  @RequestBody PropertyDTO property) throws DistrictNotFoundException {
        return propertyService.calculateAll(property);
    }
}
