package com.meli.tasaciones.service;

import com.meli.tasaciones.dto.CasaDto;
import com.meli.tasaciones.dto.HabitacionDto;
import com.meli.tasaciones.exception.CalcularValorException;
import com.meli.tasaciones.exception.MetrosCuadradosException;

import java.util.Map;

public interface MetrosCuadradosService {

  Double calcularValor(CasaDto casaDto) throws MetrosCuadradosException;

  Double calcularTotalMetrosCuadrados(CasaDto casaDto) throws CalcularValorException;

  HabitacionDto getHabitacionMasGrande(CasaDto casaDto) throws CalcularValorException;

  Map<HabitacionDto, Double> calcularMetrosCaudradosPorHabitacion(CasaDto casaDto) throws CalcularValorException;

  Double getValorMetroCuadrado(String location, Double precioPorMetroCuadrado) throws MetrosCuadradosException;

}
