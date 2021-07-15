package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO extends UserDTO{

    private List<BuyerFollowerDTO> followers;
    private List<PostDTO> postDTOList;

    public SellerDTO(Integer userId, String name){
        super(userId,name);
    }

}
