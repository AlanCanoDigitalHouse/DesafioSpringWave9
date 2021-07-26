package testingchallengue.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EstateDTO {

    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(min = 1, message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;

    @NotNull(message = "El nombre del barrio no puede estar vacío.")
    @Size(min = 1, message = "El nombre del barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotEmpty(message = "Cada propiedad debe tener al menos un ambiente.")
    private List<@Valid EnvironmentDTO> environments;

}
