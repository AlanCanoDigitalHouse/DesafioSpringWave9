package com.mercadolibre.socialmeli.dtos;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class SellerDTO {

    private Integer userId;
    private String userName;
    private List<UserDTO> followers = new LinkedList<>();

}