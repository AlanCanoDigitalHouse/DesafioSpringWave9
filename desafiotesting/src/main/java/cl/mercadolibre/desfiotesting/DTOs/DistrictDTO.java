package cl.mercadolibre.desfiotesting.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class DistrictDTO {

    @JsonProperty(value = "district_name")
    @NotNull(message = "El barrio no puede estar vacio.")
    @Size(max = 45,message = "La longitud del barrio no puede superar los 45 caracteres.")
    String name;

    @JsonProperty(value = "district_price")
    @NotNull(message = "El precio del barrio no puede estar vacio.")
    @DecimalMax(value = "4000", message = "El precio maximo permitido por metro cuadrado no puede superar los 40000 U$S.")
    Double price;

}
