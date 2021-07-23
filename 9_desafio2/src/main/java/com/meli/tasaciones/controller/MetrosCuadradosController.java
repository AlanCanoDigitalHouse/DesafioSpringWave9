package com.meli.tasaciones.controller;

import com.meli.tasaciones.dto.CasaDto;
import com.meli.tasaciones.dto.response.MetrosCuadradosResponse;
import com.meli.tasaciones.exception.MetrosCuadradosException;
import com.meli.tasaciones.service.MetrosCuadradosServiceImp;
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
  MetrosCuadradosServiceImp metrosCuadradosService;

  @PostMapping("/metroscuadrados")
    public ResponseEntity<MetrosCuadradosResponse> calcularMetrosCuadrados(@Valid @RequestBody CasaDto casaDto) throws MetrosCuadradosException {
    return new ResponseEntity<>(new MetrosCuadradosResponse(
            metrosCuadradosService.calcularTotalMetrosCuadrados(casaDto),
            metrosCuadradosService.calcularValor(casaDto),
            metrosCuadradosService.getHabitacionMasGrande(casaDto),
            metrosCuadradosService.calcularMetrosCaudradosPorHabitacion(casaDto)), HttpStatus.OK);
  }
}
