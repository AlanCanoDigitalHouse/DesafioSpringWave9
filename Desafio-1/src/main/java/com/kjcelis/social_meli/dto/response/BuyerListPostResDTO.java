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
public class BuyerListPostResDTO extends UserDTO {

    private ArrayList<PostDTO> posts;

    public BuyerListPostResDTO(Integer userId, ArrayList<PostDTO> posts) {
        super(userId);
        this.posts = posts;
    }
}
