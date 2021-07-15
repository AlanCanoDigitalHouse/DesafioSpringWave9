package com.mercadolibre.socialmeli.dtos.request;

import com.mercadolibre.socialmeli.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated

public class PostRequestDTO {
    @NotNull(message = "userId Required")
    @Min(message = "userId must be greater than 0", value = 0)
    private Integer userId;

    @NotNull(message = "date Required")
    private LocalDate date;

    @NotNull(message = "detail Required")
    @Valid
    private Product detail;

    @NotNull(message = "category Required")
    @Min(message = "category must be greater than 0", value = 0)
    private Integer category;

    @NotNull(message = "price Required")
    @Min(message = "price must be greater than 0", value = 0)
    private Double price;

}

