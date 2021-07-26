package com.mercadolibre.calculadorametroscuadrados.dto;

import com.mercadolibre.calculadorametroscuadrados.validators.constraints.IsUpperCaseConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class HouseDTO {

  @IsUpperCaseConstraint(message = "El nombre de la propiedad debe comenzar con mayuscula.")
  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(min = 1, max = 30,message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotBlank(message = "El barrio no puede estar vacio.")
  @Size( max = 30,message = "La longitud del barrio no puede superar los 45 caracteres ni estar vacio.")
  private String district_name;

  @NotNull(message = "El precio de un barrio no puede estar vacío")
  @Max(message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.",value = 4000)
  private Double district_price;

  @NotEmpty(message = "La casa debe tener por lo menos un ambiente.")
  private List<@Valid EnvironmentDTO> environments;
}
