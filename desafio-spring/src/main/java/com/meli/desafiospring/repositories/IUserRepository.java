package com.meli.desafiospring.repositories;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.models.User;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IUserRepository {

    /*
    todo: devuelvo los datos y la capa de servicio los prepara como responseDTOs
     */
    public HttpStatus follow(Integer userId, Integer userIdToFollow);
    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow);
    public Integer followers_count(Integer userId);
    public List<User> followers_list(Integer userId);
    public List<User> followed_list(Integer userId);
    public HttpStatus newPost(Integer userId, PostDTO postDTO);
    // Hardcode para 2 semanas; luego agrego el parámetro para setear el tiempo
    public List<PostDTO> lastPostedItems(Integer userId);

}
