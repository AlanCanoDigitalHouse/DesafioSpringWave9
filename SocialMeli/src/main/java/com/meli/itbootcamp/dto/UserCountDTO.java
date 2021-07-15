package com.meli.itbootcamp.dto;

import com.meli.itbootcamp.model.User;
import lombok.*;

@Getter
@Setter
@ToString
public class UserCountDTO extends UserDTO{

    private Integer followers_count;

    public UserCountDTO(User seller, Integer quant){
        super(seller);
        this.followers_count=quant;
    }
}
