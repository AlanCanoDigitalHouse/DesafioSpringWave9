package com.desafiospring.socialmeli.dtos.requests;

import com.desafiospring.socialmeli.dtos.models.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
public class PostRequestDTO {

    @NotNull(message = "User Id no debe ser nulo")
    private Integer userId;

    @NotNull(message = "Date no debe ser nulo")
    private String date;

    @Valid
    private ProductDetail detail;

    @NotNull(message = "Category no debe ser nulo")
    private Integer category;

    @NotNull(message = "Price no debe ser nulo")
    private Double price;

}
