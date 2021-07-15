package com.desafio.spring.dtos;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Getter
@Setter
public class SellerDto extends UserDto{
    List<ClientDto> followers;

    public SellerDto(Integer userId, String userName){
        super(userId, userName);
        this.followers = new ArrayList<>();
    }

}
