package com.socialmeli.services.impl;

import com.socialmeli.dtos.UserDTO;
import com.socialmeli.dtos.response.FollowCountDTO;
import com.socialmeli.dtos.response.FollowedListDTO;
import com.socialmeli.dtos.response.FollowerListDTO;
import com.socialmeli.exceptions.NotFoundException;
import com.socialmeli.exceptions.UserNotFoundException;
import com.socialmeli.models.User;
import com.socialmeli.models.UserSocial;
import com.socialmeli.repositories.ISocialRepository;
import com.socialmeli.repositories.impl.SocialRepository;
import com.socialmeli.services.IUsersService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class UsersService implements IUsersService {

    private final ISocialRepository socialRepository;

    public UsersService(ISocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }


    @Override
    public void followUser(Integer idUser, Integer idFollow) {
        UserSocial seller = socialRepository.findUserbyId(idFollow);
        UserSocial user = socialRepository.findUserbyId(idUser);
        if(!user.getFollowed().contains(seller)){
            seller.getFollowers().add(user);
            user.getFollowed().add(seller);
        }

    }

    @Override
    public FollowCountDTO countFollowers(Integer idUser) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        return new FollowCountDTO(user.getId(), user.getName(), user.getFollowers().size());
    }

    @Override
    public FollowerListDTO listFollowers(Integer idUser) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        ArrayList<UserDTO> followers = new ArrayList<UserDTO>();
        for( User follower : user.getFollowers() )
            followers.add( new UserDTO(follower.getId(), follower.getName()));
        return new FollowerListDTO(user.getId(), user.getName(), followers);
    }

    @Override
    public FollowedListDTO listFollowed(Integer idUser) {
        UserSocial user = socialRepository.findUserbyId(idUser);
        ArrayList<UserDTO> followed = new ArrayList<UserDTO>();
        for( User follower : user.getFollowers() )
            followed.add( new UserDTO(follower.getId(), follower.getName()));
        return new FollowedListDTO(user.getId(), user.getName(), followed);
    }
}
