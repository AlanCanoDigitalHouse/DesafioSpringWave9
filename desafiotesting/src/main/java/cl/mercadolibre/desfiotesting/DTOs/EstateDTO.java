package cl.mercadolibre.desfiotesting.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstateDTO {

    @JsonProperty(value = "prop_name")
    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    // TODO: REGEX MATCH FIRST LETTER CAPITAL @Pattern(regexp="/^[A-z][a-z0-9_-]{3,19}$/", message="El nombre de la propiedad debe comenzar con mayúscula.")
    String name;

    @JsonProperty(value = "district")
    @NotNull(message = "el barrio no puede estar vacio")
    DistrictDTO districtDTO;

    @JsonProperty(value = "environment")
    @NotNull(message = "El complejo habitacional no puede estar vacio")
    List<EnvironmentDTO> environmentDTOList;

}
