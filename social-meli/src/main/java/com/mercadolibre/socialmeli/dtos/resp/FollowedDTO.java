package com.mercadolibre.socialmeli.dtos.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.socialmeli.dtos.BuyerDTO;
import com.mercadolibre.socialmeli.dtos.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowedDTO extends BuyerDTO {

    private Long followed_count;
    private List<UserDTO> followed;

    public FollowedDTO(UserDTO user) {
        super(user.getUserId(), user.getUserName());
    }

}
