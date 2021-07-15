package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.model.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
public class FollowingListResponse extends UserListResponse {
    private List<UserDTOResponse> followed;

    public FollowingListResponse(Client client, String order) {
        super(client);
        this.followed = new ArrayList<>();
        this.appendUserResponses(
                order == null ?
                client.getFollowed() :
                client.getSortedFollowed(order),
                this.followed);
    }
}
