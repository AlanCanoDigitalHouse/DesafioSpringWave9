package com.example.desafiospring.dtos.responsedtos;

import com.example.desafiospring.dtos.SellerFollowedDTO;
import com.example.desafiospring.dtos.UserDTO;
import lombok.Data;

@Data
public class SellerTotalFollowersResponseDTO extends UserDTO {

    private Integer totalFollowers;

    public SellerTotalFollowersResponseDTO(Integer id, String name, Integer totalFollowers){
        super(id,name);
        this.totalFollowers=totalFollowers;
    }

}
