package com.meli.tasaciones.controller;

import com.meli.tasaciones.dto.MetrosCuadradosResponse;
import com.meli.tasaciones.service.CalculadoraMetrosCuadradosImp;
import com.meli.tasaciones.model.Casa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MetrosCuadradosController {

  @Autowired
  CalculadoraMetrosCuadradosImp metrosCuadradosService;

  @PostMapping("/metroscuadrados")
    public ResponseEntity<MetrosCuadradosResponse> calcularMetrosCuadrados(@Valid @RequestBody Casa casa) {
    return new ResponseEntity<>(new MetrosCuadradosResponse(
            metrosCuadradosService.calcularTotalMetrosCuadrados(casa),
            metrosCuadradosService.calcularValor(casa),
            metrosCuadradosService.getHabitacionMasGrande(casa),
            metrosCuadradosService.calcularMetrosCaudradosPorHabitacion(casa)), HttpStatus.OK);
  }
}
