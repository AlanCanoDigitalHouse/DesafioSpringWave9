package com.example.socialmeli.services;

import com.example.socialmeli.dtos.PostDto;
import com.example.socialmeli.dtos.responses.ResponsePostsListDto;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.models.User;
import com.example.socialmeli.dtos.responses.ResponseCantFollowersDto;
import com.example.socialmeli.dtos.responses.ResponseFollowedDto;
import com.example.socialmeli.dtos.responses.ResponseFollowersDto;
import com.example.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class SocialMeliServices implements ISocialMeliServices{
    private static UserRepository userRepository;

    public SocialMeliServices(){
        userRepository = new UserRepository();
    }

    public void init() {
        User user_1 = new User(1,"Carlos");
        User user_2 = new User(2,"Marta");
        User user_3 = new User(3,"Jose");
        User user_4 = new User(4,"Manuela");
        userRepository.add(user_1);
        userRepository.add(user_2);
        userRepository.add(user_3);
        userRepository.add(user_4);
    }

    public void follow(HttpServletResponse response, Integer userId, Integer userIdToFollow) throws UserNotFound {
        User user = userRepository.find(userId);
        User userToFollow = userRepository.find(userIdToFollow);

        user.addFollowed(userToFollow);
        userToFollow.addFollower(user);
        response.setStatus(HttpServletResponse.SC_OK);

        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public void unfollow(HttpServletResponse response, Integer userId, Integer userIdToUnfollow) throws UserNotFound {
        User user = userRepository.find(userId);
        User userToUnfollow = userRepository.find(userIdToUnfollow);

        user.removeFollowed(userToUnfollow);
        userToUnfollow.removeFollower(user);
        response.setStatus(HttpServletResponse.SC_OK);

        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public ResponseCantFollowersDto getFollowersCount(HttpServletResponse response, Integer userId) throws UserNotFound {
        User user = userRepository.find(userId);
        return user.getCantFollowers();
    }

    public ResponseFollowersDto getFollowersInfo(HttpServletResponse response, Integer userId, String order) throws UserNotFound {
        User user = userRepository.find(userId);
        return user.getInfoFollowers(order);
    }

    public ResponseFollowedDto getFollowedInfo(HttpServletResponse response, Integer userId, String order) throws UserNotFound {
        User user = userRepository.find(userId);
        return user.getInfoFollowed(order);
    }

    public void post(HttpServletResponse response, PostDto postDto) throws UserNotFound {
        User user = userRepository.find(postDto.getUserId());

        user.addPost(postDto);
        response.setStatus(HttpServletResponse.SC_OK);

        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    public ResponsePostsListDto getPostsInfo(HttpServletResponse response, Integer userId, String order) throws UserNotFound {
        User user = userRepository.find(userId);//MEJOR HAGO QUE EL .FIND TIRE EL ERROR, ASI NO LO HACEN TODOS LOS METODOS
        return user.getPostsList(order);
    }

}
