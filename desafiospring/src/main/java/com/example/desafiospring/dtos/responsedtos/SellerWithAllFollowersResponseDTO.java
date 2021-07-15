package com.example.desafiospring.dtos.responsedtos;

import com.example.desafiospring.dtos.BuyerFollowerDTO;
import com.example.desafiospring.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerWithAllFollowersResponseDTO extends UserDTO {

    private List<BuyerFollowerDTO> followers;

    public SellerWithAllFollowersResponseDTO(Integer id, String username){
        super(id,username);
    }
}
