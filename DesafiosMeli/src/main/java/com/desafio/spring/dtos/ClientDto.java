package com.desafio.spring.dtos;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Getter
@Setter
public class ClientDto extends UserDto{
    List<SellerDto> followed;

    public ClientDto(Integer userId, String userName){
        super(userId, userName);
        this.followed = new ArrayList<>();
    }

}
