package com.socialmeli.dtos.requests;

import com.socialmeli.validations.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "userId must not be null")
    private Integer userId;

    @NotNull(message = "date must not be null")
    @DateValidation(pattern="dd-MM-yyyy", message = "date must be like dd-MM-yyyy")
    private String date;

    @NotNull(message = "detail must not be null")
    private ProductRequestDTO detail;

    @NotNull(message = "category must not be null")
    private Integer category;

    @NotNull(message = "price must not be null")
    @DecimalMin(value = "0.01", message = "price must be greater than zero")
    private Double price;

}
