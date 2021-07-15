package com.mercadolibre.socialmeli.dtos.posts.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromoPostRequestDTO {

    @NotNull(message = "userId is mandatory")
    @PositiveOrZero(message = "userId must be positive or zero")
    private Integer userId;

    @NotNull(message = "date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "the date must be lower or equal to this day")
    private LocalDate date;

    @Valid
    @NotNull(message = "detail is mandatory")
    private ProductRequestDTO detail;

    @NotNull(message = "category is mandatory")
    @PositiveOrZero(message = "category must be positive or zero")
    private Integer category;

    @NotNull(message = "price is mandatory")
    @PositiveOrZero(message = "price must be positive or zero")
    private Double price;

    @NotNull(message = "hasPromo is mandatory")
    private Boolean hasPromo;

    @NotNull(message = "discount is mandatory")
    @PositiveOrZero(message = "discount must be positive or zero")
    private Double discount;

}
