package com.example._9desafio2.dtos.request;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class PropertyDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(min = 1,max = 30,message ="La longitud del nombre no puede superar los 30 caracteres." )
    private String prop_name;

    @Valid
    private DistrictDTO district;

    @Valid
    @NotEmpty(message = "La lista de ambientes no puede estar vacia")
    private List<EnviromentDTO> environments;




}
