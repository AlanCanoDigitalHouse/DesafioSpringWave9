package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.model.*;
import lombok.*;

@Getter
@Setter
public class UserDTOResponse {
    private int userId;
    private String userName;

    public UserDTOResponse(User user) {
        this.userId = user.getId();
        this.userName = user.getName();
    }
}
