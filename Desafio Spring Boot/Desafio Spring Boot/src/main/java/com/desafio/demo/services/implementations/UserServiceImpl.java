package com.desafio.demo.services.implementations;

import com.desafio.demo.dtos.users.FollowedResponseDto;
import com.desafio.demo.dtos.users.UserResponseDto;
import com.desafio.demo.dtos.users.FollowersCountResponseDto;
import com.desafio.demo.dtos.users.FollowersResponseDto;
import com.desafio.demo.entiity.User;
import com.desafio.demo.exceptions.InteractionNotAllowedException;
import com.desafio.demo.exceptions.InvalidOrderingCriterionException;
import com.desafio.demo.repositories.ProductRepository;
import com.desafio.demo.repositories.UserRepository;
import com.desafio.demo.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public FollowersCountResponseDto countFollowers(int userId) {
        User user = userRepository.getUser(userId);
        return new FollowersCountResponseDto(userId, user.getName(), user.getFollowers().size());
    }

    //para comenzar a seguir validamos que los id existan (ver en UserRepo / getUser() ),
    // sean de usuarios distintos y que la interaccion aun no exista
    @Override
    public void followUser(Integer userId, Integer userIdToFollow) {

        if(userId==userIdToFollow){
            throw new InteractionNotAllowedException("You can not follow yourself");
        }
        if(isFollower(userId, userIdToFollow)){
            throw new InteractionNotAllowedException("This interaction already exists");
        }
        userRepository.saveFollow(userId, userIdToFollow);
    }

    @Override
    public FollowersResponseDto getFollowersList(int userId, String o) {
        User seller = userRepository.getUser(userId);
        List<UserResponseDto> followers = seller.getFollowers().stream()
                .map(followerId -> new UserResponseDto(followerId, userRepository.getUser(followerId).getName()))
                .collect(Collectors.toList());
        sort(followers, o);
        return new FollowersResponseDto(userId, seller.getName(), followers);
    }

    @Override
    public FollowedResponseDto getFollowedList(int userId, String o) {
        User user = userRepository.getUser(userId);
        List<UserResponseDto> followed = user.getFollowed().stream()
                .map(followedId -> new UserResponseDto(followedId, userRepository.getUser(followedId).getName()))
                .collect(Collectors.toList());
        sort(followed, o);
        return new FollowedResponseDto(userId, user.getName(), followed);
    }

    public FollowedResponseDto getFollowedList(int userId){
       return getFollowedList(userId, null);
    }

    @Override
    public void unfollow(int userId, int userIdToUnfollow) {

        if(userId==userIdToUnfollow){
            throw new InteractionNotAllowedException("The id corresponds to the same user");
        }

        if(!isFollower(userId, userIdToUnfollow)){
            throw new InteractionNotAllowedException("This interaction already exists");
        }

        userRepository.deleteFollow(userId, userIdToUnfollow);
    }

    private void sort(List<UserResponseDto> user, String order) {
        if ("name_desc".equals(order)) {
            Collections.sort(user, Comparator.comparing(UserResponseDto::getUserName).reversed());
        } else if ("name_asc".equals(order) || null == order) {
            Collections.sort(user, Comparator.comparing(UserResponseDto::getUserName));
        } else {
            throw new InvalidOrderingCriterionException();
        }
    }

    public boolean isFollower(int userid, int userIdToFollow){
        return getFollowedList(userid).getFollowed().stream().map(userResponse -> userResponse.getUserId()).anyMatch(id ->id ==userIdToFollow);
    }

    @Override
    public void initialize() {
        productRepository.initialize();
        userRepository.initilize();
        followUser(1, 2);
        followUser(1, 3);
        followUser(1, 0);
        followUser(2, 0);
        followUser(2, 1);
        followUser(2, 3);
        followUser(0, 2);
        followUser(0, 1);
    }
}