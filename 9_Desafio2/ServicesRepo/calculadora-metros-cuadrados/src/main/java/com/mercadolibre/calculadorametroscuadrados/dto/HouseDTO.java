package com.mercadolibre.calculadorametroscuadrados.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HouseDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp = "^[A-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String propName;

  @NotBlank(message = "El barrio no puede estar vacío.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String districtName;

  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  @DecimalMin(value = "1", message = "El precio minimo permitido por metro cuadrado no puede ser inferiror a 1 U$S.")
  private Double districtPrice;

  @NotEmpty(message = "La lista de ambientes no puede estar vacía.")
  private List<@Valid RoomDTO> environment;
}
