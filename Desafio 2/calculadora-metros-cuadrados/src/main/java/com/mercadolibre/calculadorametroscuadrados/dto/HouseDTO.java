package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;



@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {


  @Pattern(regexp = "^[A-Z]",message = "El nombre de una propiedad debe comenzar con mayúscula.")
  @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @Valid
  @NotEmpty(message = "El El barrio no puede estar vacío.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;
  @Valid
  @NotEmpty(message = "El precio de un barrio no puede estar vacío.")
  private Double district_price;
  @Valid
  @NotEmpty(message = "La casa no puede no tener ambientes")
  private List<RoomDTO> rooms;

}
