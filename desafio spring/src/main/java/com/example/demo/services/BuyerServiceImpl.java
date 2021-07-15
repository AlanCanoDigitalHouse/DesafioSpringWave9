package com.example.demo.services;

import com.example.demo.dtos.response.ListFollowedResponseDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.BuyerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Override
    public void followSeller(User user, User userToFollow) {
        user.follow(userToFollow);
        userToFollow.addFollower(user);
    }

    @Override
    public ListFollowedResponseDto listFollowed(User user, Map<Integer,User> usersList, String order){
        List<UserDto> usersDtos = new ArrayList<>();
        for(User followedUser: user.getFollowed()){
            UserDto userDto = new UserDto(followedUser.getUserId() , followedUser.getUserName());
            usersDtos.add(userDto);
        }
        if(order != null) {this.orderByName(order , usersDtos);}
        ListFollowedResponseDto followeds = new ListFollowedResponseDto(user.getUserId(),
                                                                        user.getUserName(),
                                                                        usersDtos);
        return followeds;
    }

    @Override
    public void unfollowSeller(User user, User removedUser) {
        user.unfollow(removedUser);
        removedUser.removeFollower(user);
    }

    public void orderByName(String order, List<UserDto> list){
        if (order.equals("name_asc"))
            list.sort(Comparator.comparing(UserDto::getUserName));

        if (order.equals("name_desc"))
            list.sort(Comparator.comparing(UserDto::getUserName).reversed());
    }
}
