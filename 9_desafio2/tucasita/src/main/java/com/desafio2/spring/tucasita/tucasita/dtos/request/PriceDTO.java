package com.desafio2.spring.tucasita.tucasita.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

    private String location;
    private Integer price;
}
