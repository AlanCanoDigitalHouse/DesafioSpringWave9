package com.mercadolibre.socialmeli.dtos;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class BuyerDTO {

    private Integer userId;
    private String userName;

    private List<UserDTO> followed = new LinkedList<>();

}