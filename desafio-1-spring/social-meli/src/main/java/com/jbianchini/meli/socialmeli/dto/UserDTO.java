package com.jbianchini.meli.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class UserDTO {
    private int userId;

    @NotNull(message = "Please enter a valid name.")
    @NotBlank(message = "The name is empty")
    private String userName;

    public UserDTO(String userName) {
        this.userName = userName;
    }
}
