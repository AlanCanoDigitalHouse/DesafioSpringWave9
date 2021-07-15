package com.mercadolibre.desafio.spring.dtos.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercadolibre.desafio.spring.dtos.request.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public class UserFollowedResponseDto {

    private Integer userId;
    private String userName;
    ArrayList<UserDto> followed;
}
