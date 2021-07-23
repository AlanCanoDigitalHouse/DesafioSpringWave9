package com.example.demo.Controllers;

import com.example.demo.DTOs.DistrictDTO;
import com.example.demo.DTOs.PropertyDTO;
import com.example.demo.DTOs.PropertyDetailsDTO;
import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    IPropertyService propertyService;

    // Get details by passing a property DTO
    @PostMapping("/details")
    public ResponseEntity<PropertyDetailsDTO> getAssessment(@Valid @RequestBody PropertyDTO property) throws CustomExceptionHandler {
        return new ResponseEntity<>(propertyService.getDetails(property), HttpStatus.OK);
    }

    // Creates new district
    @PostMapping("/district")
    public ResponseEntity<String> addDistrict(@Valid @RequestBody DistrictDTO district) throws CustomExceptionHandler {
        return new ResponseEntity<>(propertyService.addDistrict(district), HttpStatus.OK);
    }

}
