package com.mercadolibre.socialmedia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @NotNull(message = "This field cant be null.")
    protected Integer userId;

    protected String userName;

    public User(Integer userId) {
        this.userId = userId;
    }


}
