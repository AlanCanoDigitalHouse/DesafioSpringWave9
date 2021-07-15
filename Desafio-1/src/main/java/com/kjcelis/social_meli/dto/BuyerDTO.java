package com.kjcelis.social_meli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerDTO extends UserDTO {
    private ArrayList<SellerDTO> followed;

    public BuyerDTO(Integer userId, String userName) {
        super(userId, userName);
    }

    public BuyerDTO(Integer userId, String userName, ArrayList<SellerDTO> followed) {
        super(userId, userName);
        this.followed = followed;
    }
}




