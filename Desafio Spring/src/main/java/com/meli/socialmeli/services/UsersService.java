package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.FollowedUserListDTO;
import com.meli.socialmeli.dtos.response.FollowersCountDTO;
import com.meli.socialmeli.dtos.response.FollowersUserListDTO;
import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.models.Follow;
import com.meli.socialmeli.models.User;
import com.meli.socialmeli.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UsersService implements IUsersService{
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public FollowedUserListDTO getFollowing(int userId) throws UserDoesNotExistException{
        Optional<User> user = usersRepository.findUserById(userId);
        Stream<Follow> following = usersRepository.getFollowing(userId);
        List<User> followed = following.map(f -> usersRepository.findUserById(f.getToFollowUserId()).get()).collect(Collectors.toList());
        return new FollowedUserListDTO(user.get(), followed);
    }

    public FollowersUserListDTO getFollowers(int userId) throws UserDoesNotExistException{
        Optional<User> user = usersRepository.findUserById(userId);
        Stream<Follow> followers = usersRepository.getFollowers(userId);
        List<User> follows = followers.map(f -> usersRepository.findUserById(f.getFollowerUserId()).get()).collect(Collectors.toList());
        return new FollowersUserListDTO(user.get(), follows);
    }

    public FollowersCountDTO countFollowers(int userId) throws UserDoesNotExistException{
        Optional<User> user = usersRepository.findUserById(userId);
        Stream<Follow> followers = usersRepository.getFollowers(userId);
        return new FollowersCountDTO(user.get(), followers.count());
    }

    @Override
    public void follow(int toFollowUserId, int followerUserId) throws UserDoesNotExistException{
        Follow f = new Follow(toFollowUserId, followerUserId);
        if(!usersRepository.followExist(f)){
            usersRepository.addFollow(f);
        }
    }

    @Override
    public List<User> orderNameAsc(List<User> users) {
        return null;
    }

    @Override
    public List<User> orderNameDesc(List<User> users) {
        return null;
    }
}
