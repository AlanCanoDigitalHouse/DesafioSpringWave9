package com.mercadolibre.socialmeli.dtos.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.dtos.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowedPostsDTO extends UserDTO {

    private Integer products_count;
    private List<PostDTO> posts;

    public FollowedPostsDTO(Integer buyerId) {
        super(buyerId, null);
    }

}
