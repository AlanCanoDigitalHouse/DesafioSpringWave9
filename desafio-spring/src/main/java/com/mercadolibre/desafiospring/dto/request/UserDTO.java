package com.mercadolibre.desafiospring.dto.request;

import com.mercadolibre.desafiospring.exception.*;
import lombok.*;

@Getter
@Setter
public class UserDTO {
    private int userId;
    private String userName;

    public UserDTO(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
