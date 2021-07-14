package com.mercadolibre.desafio_spring.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafio_spring.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class NewPostRequest {
    @Min(message = "Id errado",value = 1)
    @NotNull(message = "Id nulo")
    int userId;

    @Min(message = "Id errado",value = 1)
    @NotNull(message = "Id nulo")
    int id_post;

    @NotNull(message = "Id nulo")
    @JsonFormat(pattern="dd-MM-yyyy")
    Date date;

    @Valid
    Product detail;

    @Min(message = "No se reconoce la categoria",value = 1)
    @NotNull(message = "Id nulo")
    int category;

    @Min(message = "Valor errado",value = 0)
    @NotNull(message = "El producto debe tener un precio, puede ser 0")
    double price;
}