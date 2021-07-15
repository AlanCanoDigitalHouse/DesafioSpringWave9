package com.socialmeli.helpers;

import com.socialmeli.dtos.request.PostRequestDTO;
import com.socialmeli.dtos.response.PostResponseDTO;
import com.socialmeli.models.PostSocial;

import java.time.LocalDate;

public class PostHelper {

    /**
     * Mapper between posting model objects and DTO posts response
     * @param model model to be mapped to DTO response
     * @return DTO response Post
     */
    public static PostResponseDTO mapper(PostSocial model) {
        return new PostResponseDTO(
                model.getUserId(), model.getId(),
                model.getDate().toString(),
                ProductHelper.mapper(model.getDetail()),
                model.getCategory(),
                model.getPrice()
        );
    }

    /**
     * Mapper between posting DTO response objects and model posts
     * @param responseDTO DTO response to be mapped to model
     * @return model Post
     */
    public static PostSocial mapper(PostResponseDTO responseDTO) {
        return new PostSocial(
                responseDTO.getUserId(), responseDTO.getId_post(),
                LocalDate.parse(responseDTO.getDate()),
                ProductHelper.mapper(responseDTO.getDetail()),
                responseDTO.getCategory(), responseDTO.getPrice()
        );
    }

    /**
     * Mapper between posting DTO request objects and model posts
     * @param requestDTO DTO request to be mapped to model
     * @return model Post
     */
    public static PostSocial mapper(PostRequestDTO requestDTO) {
        return new PostSocial(
                requestDTO.getUserId(), null,
                requestDTO.getDate(),
                ProductHelper.mapper(requestDTO.getDetail()),
                requestDTO.getCategory(), requestDTO.getPrice()
        );
    }
}
