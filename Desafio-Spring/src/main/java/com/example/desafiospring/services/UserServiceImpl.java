package com.example.desafiospring.services;

import com.example.desafiospring.dtos.general.User;
import com.example.desafiospring.dtos.general.UserInfo;
import com.example.desafiospring.dtos.response.FollowedsListDTO;
import com.example.desafiospring.dtos.response.FollowersCountDTO;
import com.example.desafiospring.dtos.response.FollowersListDTO;
import com.example.desafiospring.exceptions.UserNotFindException;
import com.example.desafiospring.repositories.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepositoryImpl userRepository;


    public UserServiceImpl(UserRepositoryImpl repository) {
        this.userRepository = repository;
    }

    @Override
    public ResponseEntity<?> newFollow(Integer userId, Integer userToFollow) throws UserNotFindException{
        UserInfo user = userRepository.getUser(userId);

        UserInfo userTo = userRepository.getUser(userToFollow);

        if(user != null && userTo !=null) {
            System.out.println("Existen los usuarios");
            User userAux = new User(userId, user.getUserName());
            User userAuxToFollow = new User(userToFollow, userTo.getUserName());
            user.getFollower().add(userAuxToFollow);
            userTo.getFollowed().add(userAux);
            userRepository.updateUsersFile();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            System.out.println("No existen los usuarios");
            throw  new UserNotFindException();
        }
    }

    @Override
    public FollowersCountDTO followerCount(Integer userId) throws UserNotFindException {
        FollowersCountDTO response = new FollowersCountDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null){
            response.setUserId(userId);
            response.setUserName(user.getUserName());
            response.setFollowers_count(user.getFollower().size());
            return response;
        }else{
            throw new UserNotFindException();
        }
    }

    @Override
    public FollowersListDTO followerList(Integer userId) throws UserNotFindException {
        FollowersListDTO response = new FollowersListDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null){
            response.setUserId(userId);
            response.setUserName(user.getUserName());
            response.setFollowers(user.getFollower());
            return response;
        }else{
            throw new UserNotFindException();
        }
    }

    @Override
    public FollowedsListDTO followedList(Integer userId) throws UserNotFindException {
        FollowedsListDTO response = new FollowedsListDTO();
        UserInfo user = userRepository.getUser(userId);
        if (user != null){
            response.setUserId(userId);
            response.setUserName(user.getUserName());
            response.setFolloweds(user.getFollowed());
            return response;
        }else{
            throw new UserNotFindException();
        }
    }
}
