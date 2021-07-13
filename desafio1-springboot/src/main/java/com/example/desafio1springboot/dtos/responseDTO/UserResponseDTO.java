package com.example.desafio1springboot.dtos.responseDTO;

import com.example.desafio1springboot.dtos.UserDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDTO extends UserDTO {
    private Integer followers_count;

    public UserResponseDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }
}
