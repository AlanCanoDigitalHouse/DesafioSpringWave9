package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoCountResponseDTO {
    private int userId;
    private String userName;

    @JsonProperty("promoproducts_count")
    private int count;
}
