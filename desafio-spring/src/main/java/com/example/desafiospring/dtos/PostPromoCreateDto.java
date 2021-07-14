package com.example.desafiospring.dtos;

import com.example.desafiospring.enums.ConstantEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Validated
public class PostPromoCreateDto {

    @Min(message = "El user id no puede ser negativo", value = 0)
    @NotNull(message = "El user id es obligatorio")
    private Long userId;

    @NotNull(message = "La fecha es obligatoria")
    @NotBlank(message = "La fecha no puede ser vacia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantEnum.DATE_FORMAT)
    private String date;

    @Valid
    @NotNull(message = "El detalle de la publicacion es obligatorio")
    private ProductCreateDto detail;

    @Min(message = "El user id no puede ser negativo", value = 0)
    @Max(message = "No existe una categoria mayor a 100", value = 100)
    @NotNull(message = "La categoria es obligatoria")
    private Long category;

    @DecimalMin(message = "El precio no puede ser negativo", value = "0.0")
    @NotNull(message = "El precio de la publicacion es obligatorio")
    private Double price;

    @NotNull(message = "Es necesario indicar si la publicacion es una promocion o no")
    private Boolean hasPromo;

    private Double discount;

}
