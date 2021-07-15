package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.model.*;

import java.util.*;

import lombok.*;

@Getter
@Setter
public abstract class UserListResponse {
    private int userId;
    private String userName;

    public UserListResponse(User user) {
        this.userId = user.getId();
        this.userName = user.getName();
    }

    public void appendUserResponses(List<? extends User> origin,
                                    List<UserDTOResponse> destiny) {
        origin.forEach((x) -> {
            destiny.add(new UserDTOResponse(x));
        });
    }
}
