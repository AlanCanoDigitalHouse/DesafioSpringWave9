package com.mercadolibre.desafio1.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollowersCountDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}
