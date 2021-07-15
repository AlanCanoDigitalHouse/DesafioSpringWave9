package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.dto.request.UserDTO;
import com.mercadolibre.desafiospring.model.*;
import lombok.*;

@Getter
@Setter
public class FollowersCountResponse extends UserDTOResponse {
    private int followers_count;

    public FollowersCountResponse(int followers_count, User seller) {
        super(seller);
        this.followers_count = followers_count;
    }
}
