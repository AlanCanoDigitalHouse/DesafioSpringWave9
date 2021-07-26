package com.mercadolibre.calculadorametroscuadrados.controller;

import com.mercadolibre.calculadorametroscuadrados.dto.AmbienteDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.BarrioDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.ValorResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PropiedadDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.BarrioNoEncontradoException;
import com.mercadolibre.calculadorametroscuadrados.service.CalcularService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalcularRestController {

  private final CalcularService calcularService;

  public CalcularRestController(CalcularService calcularService) {
    this.calcularService = calcularService;
  }

  @PostMapping("/inicializarPropiedad")
  @ResponseStatus(HttpStatus.OK)
  public BarrioDTO inicializar(@Valid @RequestBody PropiedadDTO propiedad) throws BarrioNoEncontradoException {
    return calcularService.inicializar(propiedad);
  }

  @GetMapping("/calcularMetrosDePropiedad")
  public ValorResponseDTO calculate(){
    return new ValorResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),"Metros cuadrados totales", calcularService.calcularMetrosCuadrados());
  }

  @GetMapping("/calcularValorDePropiedad")
  public ValorResponseDTO calcularValor(){
    return new ValorResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(), "Precio total de La propiedad", calcularService.calcuarValorTotal());
  }

  @GetMapping("/calcularAmbienteMasGrande")
    public AmbienteDTO calcularAmbienteMasGrande(){
      return calcularService.calcularAmbienteMasGrande();
    }

  @GetMapping("/listarAreaDeAmbientes")
  public ValorResponseDTO listarAreas(){
    return new ValorResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(), "Listado de areas de cada ambiente", calcularService.listarAreas());
  }
}



