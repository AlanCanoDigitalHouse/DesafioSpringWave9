package com.example.DesafioSpring.dtos;

import com.example.DesafioSpring.models.Product;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Validated

public class PublicationDTO {


    @Min(message = "Enter an integer greater than zero", value = 0)
    @NotNull(message = "Null values not allowed")
    private Integer userId;

    @Min(message = "Enter an integer greater than zero", value = 0)
    @NotNull(message = "Null values not allowed")
    private Integer id_post;

    @NotNull(message = "Null values not allowed")
    private LocalDate date;

    @NotNull(message = "Product is required")
    private Product detail;

    @Min(message = "Price starts at zero", value = 0)
    @NotNull(message = "Null values not allowed")
    private Integer category;

    @Min(message = "Price starts at zero", value = 0)
    @NotNull(message = "Null values not allowed")
    private Double price;

}
