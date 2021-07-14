package com.meli.socialmeli.services;

import com.meli.socialmeli.dtos.response.FollowedUserListDTO;
import com.meli.socialmeli.dtos.response.FollowersCountDTO;
import com.meli.socialmeli.dtos.response.FollowersUserListDTO;
import com.meli.socialmeli.exceptions.FollowException;
import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.models.Follow;
import com.meli.socialmeli.models.User;
import com.meli.socialmeli.repositories.UsersRepository;
import com.meli.socialmeli.utils.SortPostDTOByDate;
import com.meli.socialmeli.utils.SortUserByName;
import org.springframework.stereotype.Service;

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

    public User getUser(int userId) throws UserDoesNotExistException{
        if(!usersRepository.findUserById(userId).isPresent()){
            throw new UserDoesNotExistException("id " + userId);
        }
        return usersRepository.findUserById(userId).get();
    }

    public boolean validateUserPosting(int userId) throws UserDoesNotExistException{
        if(!usersRepository.userExist(userId)){
            throw new UserDoesNotExistException("id " + userId);
        }
        return true;
    }

    public FollowedUserListDTO getFollowing(int userId, String order) throws UserDoesNotExistException{
        Optional<User> user = usersRepository.findUserById(userId);
        Stream<Follow> following = usersRepository.getFollowing(userId);
        List<User> followed = following.map(f -> usersRepository.findUserById(f.getToFollowUserId()).get()).collect(Collectors.toList());
        if(order != null && order.equals("name_desc")){
            return new FollowedUserListDTO(user.get(), orderNameDesc(followed));
        }
        return new FollowedUserListDTO(user.get(), orderNameAsc(followed));
    }

    public FollowersUserListDTO getFollowers(int userId, String order) throws UserDoesNotExistException{
        Optional<User> user = usersRepository.findUserById(userId);
        Stream<Follow> followers = usersRepository.getFollowers(userId);
        List<User> follows = followers.map(f -> usersRepository.findUserById(f.getFollowerUserId()).get()).collect(Collectors.toList());
        if(order != null && order.equals("name_desc")){
            return new FollowersUserListDTO(user.get(), orderNameDesc(follows));
        }
        return new FollowersUserListDTO(user.get(), orderNameAsc(follows));
    }

    public FollowersCountDTO countFollowers(int userId) throws UserDoesNotExistException{
        Optional<User> user = usersRepository.findUserById(userId);
        Stream<Follow> followers = usersRepository.getFollowers(userId);
        return new FollowersCountDTO(user.get(), followers.count());
    }

    public void unfollow(int toUnfollowUserId, int followerUserId) throws UserDoesNotExistException, FollowException {
        Follow f = new Follow(toUnfollowUserId, followerUserId);
        usersRepository.deleteFollow(f);
    }

    @Override
    public void follow(int toFollowUserId, int followerUserId) throws UserDoesNotExistException{
        Follow f = new Follow(toFollowUserId, followerUserId);
        usersRepository.addFollow(f);
    }

    @Override
    public List<User> orderNameAsc(List<User> users) {
        users.sort(new SortUserByName().reversed());
        return users;
    }

    @Override
    public List<User> orderNameDesc(List<User> users) {
        users.sort(new SortUserByName());
        return users;
    }
}
