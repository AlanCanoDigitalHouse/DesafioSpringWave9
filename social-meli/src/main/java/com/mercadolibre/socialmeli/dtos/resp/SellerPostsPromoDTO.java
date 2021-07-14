package com.mercadolibre.socialmeli.dtos.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.socialmeli.dtos.PostPromoDTO;
import com.mercadolibre.socialmeli.dtos.SellerDTO;
import com.mercadolibre.socialmeli.dtos.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerPostsPromoDTO extends UserDTO {

    private Integer promoproducts_count;
    private List<PostPromoDTO> posts;

    public SellerPostsPromoDTO(SellerDTO user) {
        super(user.getUserId(), user.getUserName());
    }

}
