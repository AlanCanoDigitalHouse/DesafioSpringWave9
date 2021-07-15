package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.mercado_libre.bootcamp.spring.desafio.dtos.SellerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedListResponseDTO {
    private int userId;
    private String userName;
    private List<SellerDTO> followed;
}
