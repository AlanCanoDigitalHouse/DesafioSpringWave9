package com.example.demo.services;

import com.example.demo.dtos.response.CountFollowersResponseDto;
import com.example.demo.dtos.response.ListFollowersResponseDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.SellerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService {

    @Override
    public CountFollowersResponseDto countFollowers(User user) {
        CountFollowersResponseDto countFollowersResponseDto = new CountFollowersResponseDto(user.getUserId(),
                                                                                        user.getUserName(),
                                                                                        user.getFollowers().size());
        return countFollowersResponseDto;
    }

    public ListFollowersResponseDto listFollowers(User user, Map<Integer,User> usersList, String order){
        List<UserDto> usersDtos = new ArrayList<>();
        for(User follower: user.getFollowers()){
            UserDto userDto = new UserDto(follower.getUserId() , follower.getUserName());
            usersDtos.add(userDto);
        }
        this.orderByName(order , usersDtos);
        ListFollowersResponseDto followers = new ListFollowersResponseDto(user.getUserId(),
                                                                        user.getUserName(),
                                                                        usersDtos);
        return followers;
    }

    public void orderByName(String order, List<UserDto> list){
        if (order.equals("name_asc"))
            list.sort(Comparator.comparing(UserDto::getUserName));

        if (order.equals("name_desc"))
            list.sort(Comparator.comparing(UserDto::getUserName).reversed());
    }
}
