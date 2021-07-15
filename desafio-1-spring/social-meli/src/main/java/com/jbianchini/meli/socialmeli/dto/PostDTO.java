package com.jbianchini.meli.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class PostDTO {
    @NotNull(message = "User id must not be null")
    private Integer userId;

    @NotNull(message = "Please enter a post date")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$",
            message = "Please enter a" + " valid date.")
    private String date;

    @Valid
    @NotNull(message = "Must add a product to the post")
    private ProductDTO detail;

    @Min(value = 0, message = "Product category cannot be less than 0")
    @Max(value = 2000, message = "Product category cannot be greater than 2000")
    private Integer category;

    @Min(value = 1, message = "Please enter a post price greater than 0")
    @NumberFormat(pattern = "/^[0-9]+(\\\\.[0-9]+)?$")
    private double price;
}
