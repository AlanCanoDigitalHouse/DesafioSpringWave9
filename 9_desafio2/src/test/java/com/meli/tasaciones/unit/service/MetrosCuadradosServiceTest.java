package com.meli.tasaciones.unit.service;

import com.meli.tasaciones.dto.CasaDto;
import com.meli.tasaciones.dto.HabitacionDto;
import com.meli.tasaciones.exception.CalcularValorException;
import com.meli.tasaciones.exception.LocationNotFoundException;
import com.meli.tasaciones.exception.LocationValidationException;
import com.meli.tasaciones.exception.MetrosCuadradosException;
import com.meli.tasaciones.repository.LocationRepositoryImp;
import com.meli.tasaciones.service.MetrosCuadradosServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetrosCuadradosServiceTest {

  @Mock
  LocationRepositoryImp repo;

  @InjectMocks
  MetrosCuadradosServiceImp service;

  private final String TEST_LOCATION = "TestLocation";
  private final Double TEST_PRICE_PER_METER = 1000.0;
  private final Double TEST_PRICE_OTHER_PER_METER = 900.0;

  CasaDto casaDto;
  CasaDto casaSinHabitaciones;
  CasaDto casaConHabitacionesNegativas;

  @BeforeEach
  void setUp() throws LocationNotFoundException {
    ArrayList<HabitacionDto> habitacionDtos = new ArrayList<>();
    habitacionDtos.add(new HabitacionDto("sala", 2.0, 2.0));
    habitacionDtos.add(new HabitacionDto("comedor", 3.0, 2.0));
    habitacionDtos.add(new HabitacionDto("recamara", 2.0, 3.0));
    casaDto = new CasaDto("nombre", TEST_LOCATION, TEST_PRICE_PER_METER, habitacionDtos);

    casaSinHabitaciones = new CasaDto("nombre", TEST_LOCATION, TEST_PRICE_PER_METER, new ArrayList<>());

    ArrayList<HabitacionDto> habitacionNegativas = new ArrayList<>();
    habitacionNegativas.add(new HabitacionDto("sala", -2.0, 2.0));
    habitacionNegativas.add(new HabitacionDto("comedor", -3.0, 2.0));
    habitacionNegativas.add(new HabitacionDto("recamara", 2.0, 3.0));
    casaConHabitacionesNegativas = new CasaDto("negativo", TEST_LOCATION, TEST_PRICE_PER_METER, habitacionNegativas);
  }

  @Test
  void whenCalcularValorThenValorCorrecto() throws MetrosCuadradosException {
    when(repo.getLocationPrice(TEST_LOCATION)).thenReturn(TEST_PRICE_PER_METER);
    double sum = casaDto.getHabitaciones().stream().mapToDouble(h -> h.getAncho() * h.getLargo()).sum();
    double expected = sum * TEST_PRICE_PER_METER;

    double result = service.calcularValor(casaDto);

    Assertions.assertEquals(expected, result);
  }

  @Test
  void givenCasaSinHabitacionesWhenCalcularThenExceptionThrown() throws MetrosCuadradosException {
    Assertions.assertThrows(CalcularValorException.class,
            () -> service.calcularValor(casaSinHabitaciones));
  }

  @Test
  void givenCasaConHabitacionesNegativasWhenCalcularThenExceptionThrown() {
    try {
      Double aDouble = service.calcularValor(casaConHabitacionesNegativas);
    } catch (MetrosCuadradosException e) {
      Assertions.assertAll(
              () -> Assertions.assertEquals("El largo y ancho de los ambientes debe ser positivo",
                      e.getMessage()),
              () -> Assertions.assertEquals(CalcularValorException.class, e.getClass())
      );
    }
  }

  @Test
  void givenOtherPricewhenCalcularValorThenExcpectLocationException() throws MetrosCuadradosException {
    when(repo.getLocationPrice(TEST_LOCATION)).thenReturn(TEST_PRICE_OTHER_PER_METER);
    Assertions.assertThrows(LocationValidationException.class, () -> service.calcularValor(casaDto));
  }

  @Test
  void whenCalcularTotalMetrosCuadradosThenCorrect() throws LocationNotFoundException, CalcularValorException {
    double sum = casaDto.getHabitaciones().stream().mapToDouble(h -> h.getAncho() * h.getLargo()).sum();
    Assertions.assertEquals(sum, service.calcularTotalMetrosCuadrados(casaDto));
  }

  @Test
  void whenGetHabitacionMasGrandeThenCorrect() throws CalcularValorException {
    List<HabitacionDto> habitaciones = casaDto.getHabitaciones();
    double maxMetrosCuadrados = 0;
    HabitacionDto habitacionMasGrande = null;
    for (HabitacionDto habitacion : habitaciones) {
      double metrosCuadrados = habitacion.getLargo() * habitacion.getAncho();
      if (metrosCuadrados > maxMetrosCuadrados) {
        maxMetrosCuadrados = metrosCuadrados;
        habitacionMasGrande = habitacion;
      }
    }

    HabitacionDto habitacionMasGrande1 = service.getHabitacionMasGrande(casaDto);
    Assertions.assertEquals(habitacionMasGrande, habitacionMasGrande1);
  }

  @Test
  void whenCalcularMetrosCaudradosPorHabitacionThenValid() throws CalcularValorException {
    Map<String, Double> expected =
            casaDto.getHabitaciones().stream().collect(Collectors.toMap(habitacionDto -> habitacionDto.getNombre(),
                    habitacionDto -> habitacionDto.getLargo() * habitacionDto.getAncho()));
    Map<String, Double> result = service.calcularMetrosCaudradosPorHabitacion(casaDto);
    Assertions.assertEquals(expected, result);
  }

  @Test
  void givenValidLocationWhenGetValorThenValidPrice() throws MetrosCuadradosException {
    when(repo.getLocationPrice(TEST_LOCATION)).thenReturn(TEST_PRICE_PER_METER);
    double valorMetroCuadrado = service.getValorMetroCuadrado(TEST_LOCATION, TEST_PRICE_PER_METER);
    Assertions.assertEquals(TEST_PRICE_PER_METER, valorMetroCuadrado);
  }

  @Test
  void givenInvalidLocationWhenGetValorThenExceptionThrown() throws MetrosCuadradosException {
    when(repo.getLocationPrice(TEST_LOCATION)).thenReturn(TEST_PRICE_OTHER_PER_METER);
    Assertions.assertThrows(LocationValidationException.class,
            () -> service.getValorMetroCuadrado(TEST_LOCATION,
                    TEST_PRICE_PER_METER));
  }
}