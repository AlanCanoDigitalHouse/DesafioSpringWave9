package com.example.desafiospring.dtos.responsedtos;

import com.example.desafiospring.dtos.PostDTO;
import com.example.desafiospring.dtos.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class SellerWithPostsDTO extends UserDTO {

    List<PostDTO> listPosts;

    public SellerWithPostsDTO(Integer id, String userName){
        super(id,userName);
    }

}
