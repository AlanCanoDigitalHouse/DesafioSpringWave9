package com.kjcelis.social_meli.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kjcelis.social_meli.dto.PostDTO;
import com.kjcelis.social_meli.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerListPpostResDTO extends UserDTO {

    private ArrayList<PostDTO> post_promo;
    private Integer promoproducts_count;

    public SellerListPpostResDTO(Integer userId, String userName, ArrayList<PostDTO> post_promo) {
        super(userId, userName);
        this.post_promo = post_promo;
    }

    public SellerListPpostResDTO(Integer userId, String userName, Integer promoproducts_count) {
        super(userId, userName);
        this.promoproducts_count = promoproducts_count;
    }
}
