package com.example.desafiospring.dtos.responsedtos;

import com.example.desafiospring.dtos.SellerFollowedDTO;
import com.example.desafiospring.dtos.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class BuyerWithAllFollowsResponseDTO extends UserDTO {

    private List<SellerFollowedDTO> follows;

    public BuyerWithAllFollowsResponseDTO(Integer userId, String username){
        super(userId,username);
    }

}
