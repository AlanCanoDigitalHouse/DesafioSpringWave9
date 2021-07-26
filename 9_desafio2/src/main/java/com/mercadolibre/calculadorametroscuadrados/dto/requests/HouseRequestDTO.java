package com.mercadolibre.calculadorametroscuadrados.dto.requests;

import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class HouseRequestDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp = "^[A-Z][A-Za-z0-9 -]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(max=30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String name;

  @NotNull(message = "Las habitaciones son un campo obligatorio")
  @NotEmpty(message = "La casa debe tener mínimo una habitación.")
  @Valid
  private List<RoomRequestDTO> rooms;

  @NotNull(message = "El barrio no puede estar vacío")
  @Valid
  private LocationDTO location;

}
