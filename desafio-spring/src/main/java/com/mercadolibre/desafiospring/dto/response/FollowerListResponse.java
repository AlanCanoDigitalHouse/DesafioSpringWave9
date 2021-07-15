package com.mercadolibre.desafiospring.dto.response;

import com.mercadolibre.desafiospring.model.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
public class FollowerListResponse extends UserListResponse {
    private List<UserDTOResponse> followers;

    public FollowerListResponse(Seller seller, String order) {
        super(seller);
        this.followers = new ArrayList<>();
        this.appendUserResponses(
                order == null ?
                seller.getFollowers() :
                seller.getSortedFollowers(order),
                this.followers);
    }
}
