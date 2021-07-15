package com.mercadolibre.desafio.spring.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public class NumberFollowersResponseDto {

    private Integer userId;
    private String userName;
    private Integer followers_count;

}
