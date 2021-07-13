package com.mercadolibre.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class RequestPostDto {

    @NotNull
    @Min(message = "the min id is 0", value = 0)
    private Integer userId;
    @NotNull
    private String date;
    @Valid
    @NotNull
    private RequestDetailDto detail;
    @NotNull
    @Min(message = "the min is 0", value=0)
    private Integer category;
    @NotNull
    @Min(message = "the min price is 1",value = 1)
    private Double price;
}
