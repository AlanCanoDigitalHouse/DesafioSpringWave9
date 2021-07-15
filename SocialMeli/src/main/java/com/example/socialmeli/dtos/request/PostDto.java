package com.example.socialmeli.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;



@Getter
@Setter
@Validated
public class PostDto implements Comparable<PostDto> {

    @NotNull(message = "El user Id ingresado es nulo")
    private Integer userId;

    @NotNull(message = "No ha ingresado fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "No ha ingresado el detalle del producto")
    @Valid
    private ProductDto detail;

    @NotNull(message = "No ha ingresado una categoria para el producto")
    private Integer category;

    @NotNull(message = "No ha ingresado el precio del producto")
    @Min(message = "El precio debe ser mayo a 1",value = 1)
    private Double price;


    @Override
    public int compareTo(PostDto u) {
        return u.getDate().compareTo(getDate());
    }




}
