package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
  @NotNull(message = "El nombre del ambiente no puede estar vacío.")
  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @Size(min =1 , max=30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp = "^[A-Z][A-Za-z]*", message="El nombre del ambiente debe comenzar con mayúscula.")
  private String environment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @Range(min=1, max=25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
  private Integer environment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @Range(min=1, max=33, message = "El máximo largo permitido por propiedad es de 33 mts.")
  private Integer environment_length;


  public Integer getSquareFeet() {
    Integer result = 0;
    if(this.environment_width != null && this.environment_length != null)
      result = this.environment_width * this.environment_length;
    return result;
  }
}
