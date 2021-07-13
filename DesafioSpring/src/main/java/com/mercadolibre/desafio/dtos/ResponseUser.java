package com.mercadolibre.desafio.dtos;

import com.mercadolibre.desafio.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUser {

    private Integer userId;
    private String userName;

    public ResponseUser(User user) {
        this.userId = user.getUserID();
        this.userName = user.getUserName();
    }

}
