package com.mercadolibre.socialmeli.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SellerPromoPostListDTO extends UserDTO {

    private List<PromoPostDTO> posts;

    public SellerPromoPostListDTO(UserDTO user) {
        this.setUserId(user.getUserId());
        this.setUserName(user.getUserName());
    }

}
