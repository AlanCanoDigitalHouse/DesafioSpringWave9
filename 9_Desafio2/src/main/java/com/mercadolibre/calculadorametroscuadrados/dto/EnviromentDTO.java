package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.*;
@AllArgsConstructor
public class EnviromentDTO {

  @NotNull(message = "El nombre del ambiente no puede estar vacio.")
  @Pattern(regexp = "(^[A-Z]).*",message = "El nombre del ambiente debe comenzar con mayuscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String name;

  @NotNull(message = "El ancho del ambiente no puede estar vacio.")
  @Max(value = 25,message = "El maximo ancho permitido por propiedad es de 25 mts.")
  private Double width;

  @NotNull(message = "El largo del ambiente no puede estar vacio.")
  @Max(value = 33,message = "El maximo largo permitido por propiedad es de 33 mts.")
  private Double length;

  public EnviromentDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getWidth() {
    return width;
  }

  public void setWidth(Double width) {
    this.width = width;
  }

  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  public Double getSquareFeet() {
    Double result = 0D;
    if(this.width != null && this.length != null)
      result = this.width * this.length;
    return result;
  }
}
