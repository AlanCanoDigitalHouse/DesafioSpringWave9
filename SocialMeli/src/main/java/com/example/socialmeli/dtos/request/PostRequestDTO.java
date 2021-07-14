package com.example.socialmeli.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "El user id no puede ser nulo")
    @Min(value = 1, message = "El user id debe ser mayor que 0")
    private Integer userId;

    @NotNull(message = "La fecha de publicacion no puede ser vacia")
    @NotBlank(message = "La fecha de publicacion no puede ser vacia")
    @Pattern(regexp = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$",
    message = "La fecha debe tener el siguiente formato dd-mm-yyy")
    private String date;

    @Valid
    private ProductRequestDTO detail;

    @NotNull(message = "La categoria no puede ser nula")
    @Min(value = 1, message = "La categoria debe ser mayor que 0")
    private Integer category;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "1.50", message = "EL precio debe ser superior a 1,50$")
    private Double price;

}
