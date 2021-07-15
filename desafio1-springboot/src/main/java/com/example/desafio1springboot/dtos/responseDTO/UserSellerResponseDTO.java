package com.example.desafio1springboot.dtos.responseDTO;

import com.example.desafio1springboot.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSellerResponseDTO extends UserDTO {

    private Integer followers_count;

    private Integer promoproducts_count;

    public UserSellerResponseDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }

    public UserSellerResponseDTO(Integer userId, String userName) {
        super(userId, userName);
    }
}
