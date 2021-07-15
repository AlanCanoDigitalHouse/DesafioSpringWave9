package com.mercadolibre.socialmeli.dtos.posts.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Validated
public class PostRequestDTO {

    @NotNull(message = "userId is mandatory")
    @Positive(message = "userId must be 1 or more")
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

}
