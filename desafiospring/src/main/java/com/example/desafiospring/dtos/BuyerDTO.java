package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDTO extends UserDTO{

    private List<SellerFollowedDTO> followed;

    public BuyerDTO(Integer id, String name){
        super(id, name);
    }

}
