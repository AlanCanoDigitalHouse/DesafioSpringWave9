package com.meli.socialmeli.services;

import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.models.Follow;
import com.meli.socialmeli.models.User;
import com.meli.socialmeli.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IUsersService{
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void follow(int toFollowUserId, int followerUserId) throws UserDoesNotExistException{
        Follow f = new Follow(toFollowUserId, followerUserId);
        try{
            if(!usersRepository.followExist(f)){
                usersRepository.addFollow(f);
            }
        }catch(UserDoesNotExistException e){
            throw e;
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
