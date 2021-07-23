package com.meli.tasaciones.unit.controller;

import com.meli.tasaciones.controller.MetrosCuadradosController;
import com.meli.tasaciones.dto.CasaDto;
import com.meli.tasaciones.dto.HabitacionDto;
import com.meli.tasaciones.dto.response.MetrosCuadradosResponse;
import com.meli.tasaciones.exception.MetrosCuadradosException;
import com.meli.tasaciones.service.MetrosCuadradosServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetrosCuadradosControllerTest {

  @Mock
  MetrosCuadradosServiceImp service;

  @InjectMocks
  MetrosCuadradosController controller;


  private final String TEST_LOCATION = "TestLocation";
  private final Double TEST_PRICE_PER_METER = 1000.0;
  private final Double TEST_PRICE_OTHER_PER_METER = 900.0;

  CasaDto casaDto;
  CasaDto casaSinHabitaciones;
  MetrosCuadradosResponse casaDtoResponse;
  ResponseEntity<MetrosCuadradosResponse> expectedResponse;

  @BeforeEach
  void setUp() {
    ArrayList<HabitacionDto> habitacionDtos = new ArrayList<>();
    habitacionDtos.add(new HabitacionDto("sala", 2.0, 2.0));
    habitacionDtos.add(new HabitacionDto("comedor", 3.0, 2.0));
    habitacionDtos.add(new HabitacionDto("recamara", 2.0, 3.0));
    casaDto = new CasaDto("nombre", TEST_LOCATION, TEST_PRICE_PER_METER, habitacionDtos);

    casaSinHabitaciones = new CasaDto("nombre", TEST_LOCATION, TEST_PRICE_PER_METER, new ArrayList<>());

    expectedResponse = buildValidCasaResponse(casaDto);
  }

  @Test
  void givenValidPayloadWhenCalcularThenCorrectResponse() throws MetrosCuadradosException {
    when(service.calcularValor(casaDto)).thenReturn(casaDtoResponse.getValorDeLaCasa());
    when(service.calcularTotalMetrosCuadrados(casaDto)).thenReturn(casaDtoResponse.getMetrosTotales());
    when(service.getHabitacionMasGrande(casaDto)).thenReturn(casaDtoResponse.getHabitacionMasGrande());
    when(service.calcularMetrosCaudradosPorHabitacion(casaDto)).thenReturn(casaDtoResponse.getMetrosCuadradosPorHabitacion());

    ResponseEntity<MetrosCuadradosResponse> response = controller.calcularMetrosCuadrados(casaDto);
    MetrosCuadradosResponse body = response.getBody();

    Assertions.assertEquals(expectedResponse, response);
  }

  @Test
  void givenCasaSinHabitacionesWhenCalcularThenExceptionThrown() throws MetrosCuadradosException {
    when(service.calcularValor(casaSinHabitaciones)).thenThrow(MetrosCuadradosException.class);

    Assertions.assertThrows(MetrosCuadradosException.class,
            () -> controller.calcularMetrosCuadrados(casaSinHabitaciones));
  }

  private ResponseEntity<MetrosCuadradosResponse> buildValidCasaResponse(CasaDto casaDto) {
    double totalMetrosCuadradosExpected = casaDto.getHabitaciones().stream().mapToDouble(h -> h.getAncho() * h.getLargo()).sum();

    double valorDeLaCasaExpected = totalMetrosCuadradosExpected * TEST_PRICE_PER_METER;
    Map<String, Double> habitacionesConMetrosCuadrados =
            casaDto.getHabitaciones().stream().collect(Collectors.toMap(entry -> entry.getNombre(),
            entry -> entry.getAncho() * entry.getLargo()));
    HabitacionDto habitacionDto = casaDto.getHabitaciones().stream().max(Comparator.comparingDouble(h -> h.getAncho() * h.getLargo())).get();

    casaDtoResponse = new MetrosCuadradosResponse(totalMetrosCuadradosExpected, valorDeLaCasaExpected,
            habitacionDto, habitacionesConMetrosCuadrados);
    return new ResponseEntity<>(casaDtoResponse, HttpStatus.OK);
  }
}