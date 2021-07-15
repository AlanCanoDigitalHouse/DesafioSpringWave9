package com.mercadolibre.socialmeli.dtos.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollowsRespDTO {

    private Integer userId;
    private String userName;
    private List<UserDTO> followers;
    private List<UserDTO> followed;

}
