package com.mercadolibre.socialmeli.dto.response;

import com.mercadolibre.socialmeli.dto.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class FollowedResponseDTO {
    private Integer userId;
    private String userName;
    private List<UserDTO> followed;
}
