package com.mercado_libre.bootcamp.spring.desafio.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercado_libre.bootcamp.spring.desafio.models.Product;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class NewProductRequestDTO {

    @NotNull(message = "Por favor, volvé a intentar ingresado un userId")
    private Integer userId;

    @NotNull(message = "Por favor, ingresá el campo date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @Valid
    private Product detail;

    @NotNull(message = "Recordá ingresar una categoría")
    private Integer category;

    @DecimalMin(value = "0", message = "El precio debe ser mayor a 0")
    private double price;
}
