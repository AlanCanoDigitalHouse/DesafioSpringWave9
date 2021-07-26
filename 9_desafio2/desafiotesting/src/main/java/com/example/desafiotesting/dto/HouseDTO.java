package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseDTO {
  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(min =1 , max=30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp = "^[A-Z][A-Za-z]*", message="El nombre de la propiedad debe comenzar con mayúscula.")
  private String prop_name;

  @Valid
  @NotEmpty(message = "Debe tener al menos un cuarto")
  private List<RoomDTO> rooms;

  @NotNull(message = "El barrio no puede estar vacío.")
  @NotBlank(message = "El barrio no puede estar vacío.")
  @Size(min =1 , max=45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;

}
