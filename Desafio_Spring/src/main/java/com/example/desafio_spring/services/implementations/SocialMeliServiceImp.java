package com.example.desafio_spring.services.implementations;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.UserResponseDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.UserAlreadyFollowedException;
import com.example.desafio_spring.exceptions.UserNotExistException;
import com.example.desafio_spring.repositories.interfaces.ISocialMeliRepository;
import com.example.desafio_spring.services.interfaces.ISocialMeliService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class SocialMeliServiceImp implements ISocialMeliService {
    ISocialMeliRepository iSocialMeliRepository;

    public SocialMeliServiceImp(ISocialMeliRepository iSocialMeliRepository) {
        this.iSocialMeliRepository = iSocialMeliRepository;
    }

    @Override
    public User saveUser(UserRequestDTO userRequestDTO) {
        //TODO: validaciones para el usuario guardado
        User user = new User();
        Integer exists = iSocialMeliRepository.getUserByUser(user.getUserName());
        if (exists > 0) {
            user.setUserId(exists);
            user.setUserName(user.getUserName());
            user.setFollowed(user.getFollowed());
            user.setFollowers(user.getFollowers());
            user.setFollower_count(user.getFollower_count());
        }else{
            user.setUserId(iSocialMeliRepository.saveUser(userRequestDTO));
            user.setUserName(userRequestDTO.getUserName());
            user.setFollowed(new ArrayList<>());
            user.setFollowers(new ArrayList<>());
            user.setFollower_count(0);
        }
        return user;
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        return iSocialMeliRepository.getUsers();
    }

    @Override
    public void Follow(Integer id, Integer idToFollow) throws UserAlreadyFollowedException {
        User user = iSocialMeliRepository.getUserById(id);
        User userToFollow = iSocialMeliRepository.getUserById(idToFollow);
        AtomicInteger counter = new AtomicInteger();
        if(Objects.nonNull(user) && Objects.nonNull(userToFollow)){
            user.getFollowed().add(new UserResponseDTO(userToFollow.getUserId(),userToFollow.getUserName()));
            userToFollow.getFollowers().add(new UserResponseDTO(user.getUserId(),user.getUserName()));
            iSocialMeliRepository.saveUserByUser(user);
            iSocialMeliRepository.saveUserByUser(userToFollow);
        }else{
            throw new UserAlreadyFollowedException("el usuario ya fue seguido");
        }
    }

    @Override
    public User FollwerCount(Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        if (Objects.nonNull(user)){
            user.setFollower_count(user.getFollowers().size());
            iSocialMeliRepository.saveUserByUser(user);
            return new User(user.getUserId(),user.getUserName(),user.getFollower_count());
        }else{
            throw new UserNotExistException("El usuario asociado al id indicado no existe");
        }
    }

    @Override
    public User FollowersList(Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        if (Objects.nonNull(user)){
            return new User(user.getUserId(),user.getUserName(),user.getFollowers());
        }else{
            throw new UserNotExistException("El usuario asociado al id indicado no existe");
        }
    }

    @Override
    public User FollwedList(Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        if (Objects.nonNull(user)){
            return new User(user.getUserId(),user.getUserName(),user.getFollowed(), true);
        }else{
            throw new UserNotExistException("El usuario asociado al id indicado no existe");
        }
    }

    @Override
    public Post savePost(PostRequestDTO postRequestDTO) throws ParseException {
        Post post = new Post();
        Integer exists = iSocialMeliRepository.getPostById(post.getId_post());
        if (exists > 0) {
            post.setId_post(exists);
            post.setUserId(post.getUserId());
            post.setCategory(post.getCategory());
            post.setDate(post.getDate());
            post.setDetail(post.getDetail());
            post.setPrice(post.getPrice());
        }else{
            post.setId_post(iSocialMeliRepository.savePost(postRequestDTO));
            post.setUserId(postRequestDTO.getUserId());
            post.setDate(postRequestDTO.getDate());
            post.setCategory(postRequestDTO.getCategory());
            post.setDetail(postRequestDTO.getDetail());
            post.setPrice(postRequestDTO.getPrice());
        }
        return post;
    }


}
