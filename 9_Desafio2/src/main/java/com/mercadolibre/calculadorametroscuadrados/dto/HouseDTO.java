package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Validated
@Data
public class HouseDTO {
  

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(max=30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  private String prop_name;
  @NotBlank(message = "El barrio no puede estar vacío.")
  @Size(max=45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;
  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @Min(1)
  @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  private double district_price;
  @NotEmpty(message = "no debe estar vacío")
  private List<@Valid EnvironmentDTO> environments;

}
