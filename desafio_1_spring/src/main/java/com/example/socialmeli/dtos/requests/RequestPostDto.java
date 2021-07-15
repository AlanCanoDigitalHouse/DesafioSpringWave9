package com.example.socialmeli.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Validated
public class RequestPostDto {
    @Min(message = "El user id debe ser un numero positivo", value=1)
    @NotNull(message = "El userId es un campo obligatorio")
    private Integer userId;

    @NotNull(message = "La fecha es un campo obligatorio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    private RequestProductDto detail;

    @Min(message = "La categoría debe ser un numero positivo", value=1)
    @NotNull(message = "La categoria es un campo obligatorio")
    private Integer category;

    @Min(message = "El precio debe ser un numero positivo", value=1)
    @NotNull(message = "El precio es un campo obligatorio")
    private double price;

    //No los valido, pueden no estar y por default no tienen promo.
    private Boolean hasPromo;
    private double discount;
}
