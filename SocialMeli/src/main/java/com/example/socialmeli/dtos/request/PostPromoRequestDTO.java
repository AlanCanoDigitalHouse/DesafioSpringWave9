package com.example.socialmeli.dtos.request;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostPromoRequestDTO extends PostRequestDTO{

    @AssertTrue(message = "Debe ingresar verdadero")
    private Boolean hasPromo;
    @DecimalMin(value = "0.1", message = "El descuento debe ser superior a 0.1")
    private Double discount;


}
