package com.example.desafiotesting.controllers;

import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.PropertyResponseDTO;
import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;
import com.example.desafiotesting.services.ICalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;


@RestController
@Validated
public class CalculateRestController {
    @Autowired
    ICalculateService calculateService;

    /**
     * @param property
     * @return
     * @throws DistrictException
     * @throws IOException
     */
    @PostMapping("/calculate")
    public PropertyResponseDTO calculate(@Valid @RequestBody PropertyDTO property) throws DistrictException, FileException {
        return calculateService.calculate(property);
    }
}
