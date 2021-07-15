package com.mercado_libre.bootcamp.spring.desafio.dtos;

import com.mercado_libre.bootcamp.spring.desafio.models.User;
import lombok.Data;

@Data
public class SellerDTO {
    private int userId;
    private String userName;

    public SellerDTO(User seller) {
        this.userId = seller.getUserId();
        this.userName = seller.getUserName();
    }
}
