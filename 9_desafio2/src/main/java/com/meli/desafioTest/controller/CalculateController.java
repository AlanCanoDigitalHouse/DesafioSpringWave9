package com.meli.desafioTest.controller;

import com.meli.desafioTest.Dtos.*;
import com.meli.desafioTest.ICalculateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CalculateController {

    static ICalculateService SERVICE;

    CalculateController(ICalculateService service){
        SERVICE = service;
    }
    /**
     * TODO: En base a los datos enviados, calcula precio de la propiedad, total de metros cuadrados,
     * cual es la habitacion mas grande y el tama;o de cada una de las habitaciones.
     * @param house - datos de la casa que se van a evaluar
     * @return houseResponseDTO - Datos que se retornan calculados
     */
    @PostMapping("/calculate")
    public HouseResponseDTO calculate(@RequestBody @Valid HouseDTO house) {

        return SERVICE.calculate(house);
    }
}
