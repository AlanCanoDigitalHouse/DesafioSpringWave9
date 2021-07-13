package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Validated
public class PostCreateDto {

    @Min(message = "El user id no puede ser negativo", value = 0)
    @NotNull(message = "El user id es obligatorio")
    private Long userId;

    @NotNull(message = "La fecha es obligatoria")
    @NotBlank(message = "La fecha no puede ser vacia")
    @Pattern(message = "El formato de fecha debe ser dd-MM-yyyy", regexp = "^(\\d{2})(\\-)(\\d{2})(\\-)(\\d{4})")
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

}
