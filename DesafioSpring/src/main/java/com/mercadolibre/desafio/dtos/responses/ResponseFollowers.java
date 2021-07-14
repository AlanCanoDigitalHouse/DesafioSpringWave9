package com.mercadolibre.desafio.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ResponseFollowers {

    private Integer userId;
    private String userName;
    private List<ResponseUser> followers;
}
