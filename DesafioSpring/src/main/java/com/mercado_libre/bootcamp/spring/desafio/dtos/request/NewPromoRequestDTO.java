package com.mercado_libre.bootcamp.spring.desafio.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercado_libre.bootcamp.spring.desafio.models.Product;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class NewPromoRequestDTO {
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

    @NotNull(message = "Por favor, ingresar si el producto contiene o no una promocion")
    private Boolean hasPromo;

    @DecimalMin(value = "0", message = "El descuento debe ser mayor a 0")
    @DecimalMax(value = "100.01", message = "El descuento debe ser menor o igual a 100")
    private double discount;
}
