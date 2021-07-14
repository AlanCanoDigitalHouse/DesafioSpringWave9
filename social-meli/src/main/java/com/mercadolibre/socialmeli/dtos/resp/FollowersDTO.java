package com.mercadolibre.socialmeli.dtos.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.socialmeli.dtos.SellerDTO;
import com.mercadolibre.socialmeli.dtos.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowersDTO extends SellerDTO {

    private Long followers_count;
    private List<UserDTO> followers;

    public FollowersDTO(UserDTO user) {
        super(user.getUserId(), user.getUserName());
    }

}
