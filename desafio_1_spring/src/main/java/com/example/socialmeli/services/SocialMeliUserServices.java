package com.example.socialmeli.services;

import com.example.socialmeli.dtos.responses.ResponseRequestDto;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.handlers.ServiceHandler;
import com.example.socialmeli.handlers.UserHandler;
import com.example.socialmeli.models.User;
import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class SocialMeliUserServices implements ISocialMeliUserServices {
    private static UserRepository userRepository;

    public SocialMeliUserServices(){
        userRepository = new UserRepository();
        ServiceHandler.initializeUsers(userRepository);
    }


    public ResponseRequestDto follow(Integer userId, Integer userIdToFollow) throws UserNotFound {
        User user = userRepository.find(userId);
        User userToFollow = userRepository.find(userIdToFollow);

        user.addFollowed(userToFollow);
        userToFollow.addFollower(user);

        return new ResponseRequestDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),"User correctly followed");
    }

    public ResponseRequestDto unfollow(Integer userId, Integer userIdToUnfollow) throws UserNotFound {
        User user = userRepository.find(userId);
        User userToUnfollow = userRepository.find(userIdToUnfollow);

        user.removeFollowed(userToUnfollow);
        userToUnfollow.removeFollower(user);

        return new ResponseRequestDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),"User correctly unfollowed");
    }

    public ResponseCantFollowersDto getFollowersCount(Integer userId) throws UserNotFound {
        User user = userRepository.find(userId);
        return UserHandler.getCantFollowers(user);
    }

    public ResponseFollowersDto getFollowersInfo(Integer userId, String order) throws UserNotFound {
        User user = userRepository.find(userId);
        return UserHandler.getFollowersInfo(user,order);
    }

    public ResponseFollowedDto getFollowedInfo(Integer userId, String order) throws UserNotFound {
        User user = userRepository.find(userId);
        return UserHandler.getFollowedInfo(user,order);
    }

}
