package com.mercadolibre.desafio_spring.dtos.request;

import com.mercadolibre.desafio_spring.entities.Product;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Validated
public class UserProduct {

    @Min(message = "Id errado",value = 1)
    @NotNull(message = "Id nulo")
    int userId;

    @Min(message = "Id errado",value = 1)
    @NotNull(message = "Id nulo")
    int id_post;

    Date date;

    @Valid
    Product detail;

    @Min(message = "Id errado",value = 1)
    @NotNull(message = "Id nulo")
    int category;

    @Min(message = "Id errado",value = 0)
    @NotNull(message = "Id nulo")
    double price;

    public UserProduct(int userId, int id_post, Date date, Product detail, int category, double price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }


}
