package com.example.desafio1springboot.dtos.responseDTO;

import com.example.desafio1springboot.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSellerResponseDTO extends UserDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer followers_count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer promoproducts_count;

    public UserSellerResponseDTO(Integer userId, String userName, Integer followers_count) {
        super(userId, userName);
        this.followers_count = followers_count;
    }

    public UserSellerResponseDTO(Integer userId, String userName) {
        super(userId, userName);
    }
}
