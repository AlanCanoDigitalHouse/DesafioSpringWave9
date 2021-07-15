package com.kjcelis.social_meli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;
    private String userName;

    public UserDTO(Integer userId) {
        this.userId = userId;
    }
}
