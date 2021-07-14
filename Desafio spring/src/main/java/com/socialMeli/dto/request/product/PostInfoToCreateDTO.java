package com.socialMeli.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PostInfoToCreateDTO {
    @NotNull(message = "User id can't be null")
    private Integer userId;

    @NotNull(message = "Date  can't be null")
    private String date;

    @NotNull(message = "detailProduct can't be null")
    @Valid
    private DetailProductDTO detail;

    @NotNull(message = "Category name can't be null")
    @Digits(integer=5, fraction=0, message = "That number is much bigger (5 real num max)")
    private Integer category;

    @NotNull(message = "Price can't be null")
    @Digits(integer=8, fraction=2, message = "That number is much bigger (8 real num max, and 2 decimal max)")
    private Double price;

}
