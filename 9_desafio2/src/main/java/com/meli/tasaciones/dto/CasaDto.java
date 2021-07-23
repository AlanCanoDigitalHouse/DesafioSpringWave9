package com.meli.tasaciones.dto;


import com.meli.tasaciones.model.Habitacion;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class CasaDto {
  @NotNull(message = "El nombre no puede ser null")
  @NotEmpty(message = "El nombre no puede estar vacio")
  private String nombre;
  private String direccion;
  @NotEmpty(message = "La casa debe tener habitaciones")
  @Valid
  private List<HabitacionDto> habitaciones;

  public CasaDto(String nombre, String direccion, List<HabitacionDto> habitaciones) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.habitaciones = habitaciones;
  }
}
