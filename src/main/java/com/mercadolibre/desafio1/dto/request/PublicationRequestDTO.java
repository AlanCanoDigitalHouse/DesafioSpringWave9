package com.mercadolibre.desafio1.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationRequestDTO {

    @NotNull(message = "El campo 'userId' no puede ser Null.")
    @Positive(message = "El campo 'userId' debe ser un numero positivo.")
    @Min(value = 0, message = "El campo 'userId' debe ser un numero mayor 0.")
    @NumberFormat
    private Integer userId;

    @NotNull(message = "El campo 'date' no puede ser Null.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    private ProductRequestDTO detail;

    @NotNull(message = "El campo 'category' no puede ser Null.")
    @Positive(message = "El campo 'category' debe ser un numero positivo.")
    @Min(value = 0, message = "El campo category debe ser un numero mayor 0.")
    private Integer category;

    @NotNull(message = "El campo 'price' no puede ser Nulol.")
    @Positive(message = "El campo 'price' debe ser un numero positivo.")
    @Min(value = 0, message = "El campo 'price' debe ser un numero mayor 0.")
    private Double price;
}