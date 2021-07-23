package com.mercadolibre.calculadorametroscuadrados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class HouseDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp="^[A-Z].*$", message="El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @JsonProperty("prop_name")
  private String name;

  @NotBlank(message = "El barrio no puede estar vacío.")
  @NotNull(message = "El barrio no puede estar vacío.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  @JsonProperty("district_name")
  private String districtName;

  @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  @DecimalMin( value = "0.0", message = "El precio mínimo permitido por metro cuadrado no puede ser menor a 0 U$S.")
  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @JsonProperty("district_price")
  private Double districtPrice;

  @NotEmpty(message = "La propiedad debe tener al menos un ambiente.")
  private List<@Valid RoomDTO> environments;

  @JsonProperty("total_square_meter")
  private Double totalSquareMeter;
  private Double price;
  private RoomDTO biggest;

}
