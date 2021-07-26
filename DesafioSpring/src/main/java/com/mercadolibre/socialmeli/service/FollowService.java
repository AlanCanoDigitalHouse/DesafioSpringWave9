package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.ListUserFollowDto;
import com.mercadolibre.socialmeli.dto.UserDto;
import com.mercadolibre.socialmeli.dto.request.FollowRequest;
import com.mercadolibre.socialmeli.dto.response.CountFollowersResponse;
import com.mercadolibre.socialmeli.dto.response.UserFollowedResponse;
import com.mercadolibre.socialmeli.dto.response.UserFollowersResponse;
import com.mercadolibre.socialmeli.entity.Follow;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.logic.FollowUtils;
import com.mercadolibre.socialmeli.logic.UserUtils;
import com.mercadolibre.socialmeli.repository.IFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService implements IFollowService {

    private final IFollowRepository followRepository;
    private final IUserService userService;

    @Autowired
    public FollowService(IFollowRepository followRepository, IUserService userService) {
        this.followRepository = followRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity createFollow(FollowRequest followRequest) {
        followRepository.save(new Follow(followRequest.getUserId(), followRequest.getUserIdToFollow(), true));
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Message", "The follow was created"));
    }

    @Override
    public Integer countFollow(Integer userId) {
        followRepository.countByUserIdToFollow(userId);
        return followRepository.countByUserIdToFollow(userId);
    }


    @Override
    public List<Integer> listUserFollower(Integer userId) {
        FollowUtils followUtils = new FollowUtils();
        return followUtils.listIdUser(followRepository.findByUserIdToFollow(userId));
    }

    @Override
    public List<Integer> listUserFollowed(Integer userId) {
        FollowUtils followUtils = new FollowUtils();
        List<Follow> fo = followRepository.findByUserId(userId);
        return followUtils.listIdUserToFollow(fo);
    }

    //0004
    public UserFollowedResponse listUserIdFollowed(Integer userId) {
        final List<ListUserFollowDto> users = userService.listUser(listUserFollower(userId));
        UserUtils userUtils = new UserUtils();
        return new UserFollowedResponse(userId, userService.findById(userId).getUserName(), users);
    }

    @Override
    public List<Follow> findAll() {
        return followRepository.findAll();
    }

    //0007
    @Override
    public ResponseEntity findByUserIdAndUserIdToFollow(Integer userId, Integer userIdToFollow) {
        Follow f = followRepository.findFollowByUserIdAndUserIdToFollow(userId, userIdToFollow);
        f.setStatus(false);
        followRepository.save(f);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Message", "Status Code 200 "));
    }

    //0002
    @Override
    public CountFollowersResponse countFollowers(Integer userId) {
        final User user = userService.findById(userId);
        return new CountFollowersResponse(user.getUserId(), user.getUserName(), countFollow(userId));
    }

    @Override
    public UserFollowersResponse followers(Integer userId, String order) {
        UserDto user = new UserDto(userService.findById(userId));

        if (order != null) {
            if (order.equals("name_asc")) {
                return new UserFollowersResponse(user.getUserId(), user.getUserName(), userService.listUser(listUserFollowed(userId)).stream().sorted(Comparator.comparing(ListUserFollowDto::getUserName)).collect(Collectors.toList()));
            } else if (order.equals("name_desc")) {
                return new UserFollowersResponse(user.getUserId(), user.getUserName(), userService.listUser(listUserFollowed(userId)).stream().sorted(Comparator.comparing(ListUserFollowDto::getUserName).reversed()).collect(Collectors.toList()));
            } else {
                return new UserFollowersResponse(user.getUserId(), user.getUserName(), userService.listUser(listUserFollowed(userId)).stream().sorted(Comparator.comparing(ListUserFollowDto::getUserName)).collect(Collectors.toList()));
            }
        } else {
            return new UserFollowersResponse(user.getUserId(), user.getUserName(), userService.listUser(listUserFollowed(userId)).stream().sorted(Comparator.comparing(ListUserFollowDto::getUserName)).collect(Collectors.toList()));
        }

    }

}