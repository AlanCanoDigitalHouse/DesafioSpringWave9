package cl.mercadolibre.desfiotesting.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class EnvironmentDTO {

    @JsonProperty(value = "environment_name")
    @NotNull(message = "El nombre del ambiente no puede estar vacio.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del ambiente debe comenzar con mayuscula.")
    String name;

    @JsonProperty(value = "environment_length")
    @NotNull(message = "El largo del ambiente no puede estar vacio.")
    @DecimalMax(value = "33", message = "El maximo largo permitido por ambiente es de 33mts")
    Double length;

    @JsonProperty(value = "environment_width")
    @NotNull(message = "El ancho del ambiente no puede estar vacio.")
    @DecimalMax(value = "25", message = "El maximo ancho permitido por ambiente es de 25mts")
    Double width;

    public Double getSizeEnvironment(){
        return this.length * this.width;
    }

}
