package com.mercado_libre.bootcamp.spring.desafio.dtos.response;

import com.mercado_libre.bootcamp.spring.desafio.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInformationResponseDTO {
    private List<User> users;
}
