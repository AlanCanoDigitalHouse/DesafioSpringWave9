package com.mercadolibre.tucasitatasaciones.dtos.request;

import com.mercadolibre.tucasitatasaciones.validations.FirstValidation;
import com.mercadolibre.tucasitatasaciones.validations.SecondValidation;
import com.mercadolibre.tucasitatasaciones.validations.ThirdValidation;
import lombok.*;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@GroupSequence({DistrictDTO.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class DistrictDTO {

    @NotBlank(message = "El barrio no puede estar vacío", groups = FirstValidation.class)
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres", max = 45, groups = SecondValidation.class)
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío", groups = FirstValidation.class)
    @DecimalMin(message = "El precio del metro cuadrado no puede ser menor a cero", value = "0.0", groups = SecondValidation.class)
    @DecimalMax(message = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S", value = "4000.0", groups = ThirdValidation.class)
    private Double district_price;

}
