package com.example.desafio_spring.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotNull(message = "Debe contener un nombre de usuario")
    @NotBlank(message = "Debe contener un nombre de usuario")
    private String userName;

}
