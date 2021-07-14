package com.example.socialmeli.services.interfaces;


import com.example.socialmeli.dtos.request.PostPromoRequestDTO;
import com.example.socialmeli.dtos.request.PostRequestDTO;
import com.example.socialmeli.dtos.response.PostPromoResponseDTO;
import com.example.socialmeli.dtos.response.PostResponseDTO;
import com.example.socialmeli.dtos.response.SuccessResponseDTO;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.exceptions.DateParserException;
import com.example.socialmeli.exceptions.OrderInvalidFormatException;

import java.util.List;

public interface PostServices {

    SuccessResponseDTO newPost(PostRequestDTO postRequestDTO) throws DataNotFound, DateParserException;

    List<PostResponseDTO> getPostByUserId(Integer userId) throws DataNotFound;

    List<PostResponseDTO> getPostByUserId(Integer userId, String order) throws DataNotFound, OrderInvalidFormatException;

    SuccessResponseDTO newPromoPost(PostPromoRequestDTO postPromoRequestDTO) throws DataNotFound, DateParserException;

    PostPromoResponseDTO getPostPromoByUser(Integer userId) throws DataNotFound;

    List<PostResponseDTO> getPostPromoDetailByUser(Integer userId) throws DataNotFound;
}
