package com.mercadolibre.socialmeli.dtos;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class BuyerDTO extends UserDTO {

    private List<UserDTO> followed = new LinkedList<>();

    public BuyerDTO(Integer userId, String userName) {
        super(userId, userName);
    }

}