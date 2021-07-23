package com.bootcamp.desafio2.controler;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.dtos.response.ResponseDto;
import com.bootcamp.desafio2.exceptions.BusinessException;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/property")
public class PropertyController {

    IPropertyService propertyService;

    public PropertyController(IPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<ResponseDto> calculateArea(@Valid @RequestBody PropertyDto property)
            throws BusinessException, IOException {
        return ResponseEntity.ok(propertyService.calculatePrice(property));
    }
}
