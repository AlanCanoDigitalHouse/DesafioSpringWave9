package com.mercadolibre.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ResponseFollowed {

    private Integer userId;
    private String userName;
    private List<ResponseUser> followed;
}
