package com.meli.tasaciones.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class CasaDto {
  @NotBlank(message = "El nombre de la propiedad no puede estar vacio")
  @Pattern(regexp = "[A-Z][\\p{Alnum} ]+", message = "El nombre de la propiedad debe comenzar con mayuscula")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
  @JsonProperty("prop_name")
  private String nombre;
  @NotBlank(message = "El barrio no puede estar vacio")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres")
  @JsonProperty("district_name")
  private String direccion;
  @NotNull(message = "El precio de un barrio no puede estar vacio")
  @DecimalMin(value = "0.01", message = "El precio debe ser positivo")
  @DecimalMax(value = "4000.0", message = "El precio maximo permitido por metro cuadrado no puede superar los 4000.0")
  @JsonProperty("district_price")
  private Double price;
  @NotEmpty(message = "La casa debe tener ambientes")
  @JsonProperty("environments")
  @Valid
  private List<HabitacionDto> habitaciones;

  public CasaDto(String nombre, String direccion, Double price, List<HabitacionDto> habitaciones) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.price = price;
    this.habitaciones = habitaciones;
  }
}
