package com.mercadolibre.calculadorametroscuadrados.dto;

import javax.validation.constraints.*;

public class RoomDTO {
  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(regexp = "[A-Z](\\p{Alpha}||\\s)+", message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String name;
  @NotNull(message = "el ancho del ambiente no puede estar vacio")
  @DecimalMax(value = "25.0",message = "El máximo ancho permitido por propiedad es de 25 mts.")
  private Integer width;
  @NotNull(message = "el largo del ambiente no puede estar vacio")
  @DecimalMax(value = "33.0",message = "El máximo largo permitido por propiedad es de 33 mts.")
  private Integer length;

  public RoomDTO() {
  }

  public RoomDTO(String name, Integer width, Integer length) {
    this.name = name;
    this.width = width;
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getSquareFeet() {
    Integer result = 0;
    if(this.width != null && this.length != null)
      result = this.width * this.length;
    return result;
  }
}
