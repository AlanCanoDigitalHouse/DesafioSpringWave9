package com.mercadolibre.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    @NotNull(message = "User id cannot be null")
    int userId;
    int id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    LocalDate date;
    @NotNull(message = "Detail cannot be null")
    Product detail;
    @NotNull(message = "Category cannot be null")
    @Min(value = 1, message = "Category cannot be zero or negative")
    int category;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price cannot be zero or negative")
    double price;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    Boolean hasPromo = false;
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount cannot be zero or negative")
    @DecimalMax(value = "1.0", message = "Discount cannot be more than 1")
    Double discount;
}
