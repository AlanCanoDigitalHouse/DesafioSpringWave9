package com.mercadolibre.calculadorametroscuadrados.dto.response;

import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDTO {
  private String prop_name;
  private Double prop_area; //US-0001 Para mostrar el total de metros cuadrados de una propiedad
  private Double prop_price; //US-0002 Para indicar el valor de la propiedad
  private EnvironmentResponseDTO biggest_environment; //US-0003 El ambiente más grande
  private List<@Valid EnvironmentResponseDTO> environments; //US-0004 Areas de los diferentes ambientes de una propiedad

}
