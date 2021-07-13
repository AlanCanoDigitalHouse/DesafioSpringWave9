package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.PostDto;
import com.example.desafiospring.dtos.UserBasicDto;
import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.exceptions.UserNotFollowedException;
import com.example.desafiospring.repositories.IFollowerRepository;
import com.example.desafiospring.services.IFollowerService;
import com.example.desafiospring.services.IUserService;
import com.example.desafiospring.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class FollowerService implements IFollowerService {

    private final IFollowerRepository followerRepository;
    private final IUserService userService;
    private final ObjectMapper objectMapper;

    public FollowerService(IFollowerRepository followerRepository, IUserService userService, ObjectMapper objectMapper) {
        this.followerRepository = followerRepository;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void followUserById(Long userId, Long userIdToFollow) throws AlreadyFollowedException, UserNotExistException, SameUserException {
        this.validateUsersFollow(userId, userIdToFollow);
        this.followerRepository.followUserById(userId, userIdToFollow);
    }

    @Override
    public void unfollowUserById(Long userId, Long userIdToUnfollow) throws UserNotExistException, SameUserException, UserNotFollowedException {
        this.validateUsersUnfollow(userId, userIdToUnfollow);
        this.followerRepository.unfollowUserById(userId, userIdToUnfollow);
    }

    @Override
    public UserFollowersDto numFollowersByUserId(Long userId) throws UserNotExistException {
        UserDto userDto = this.userService.validateSellerExist(userId);
        Long numFollowers = this.followerRepository.getNumFollowersById(userId);
        return new UserFollowersDto(userDto.getUserId(), userDto.getUserName(), numFollowers);
    }

    @Override
    public UserFollowersDto getUserFollowers(Long userId, String order) throws UserNotExistException {
        UserDto userDto = this.userService.validateSellerExist(userId);
        List<Long> followersId = this.followerRepository.getListFollowersById(userId);
        List<UserBasicDto> followers = this.getListUsers(followersId);
        Comparator<UserBasicDto> c = (o1, o2) ->
                Utils.compareNames(o1.getUserName(), o2.getUserName(), order.equalsIgnoreCase("name_asc"));
        followers.sort(c);
        return new UserFollowersDto(userDto.getUserId(), userDto.getUserName(), followers, null);
    }

    @Override
    public UserFollowersDto getUserFollowed(Long userId, String order) throws UserNotExistException {
        UserDto userDto = this.userService.validateUserExist(userId);
        List<Long> followedId = this.followerRepository.getListFollowedById(userId);
        List<UserBasicDto> followed = this.getListUsers(followedId);
        Comparator<UserBasicDto> c = (o1, o2) ->
                Utils.compareNames(o1.getUserName(), o2.getUserName(), order.equalsIgnoreCase("name_asc"));
        followed.sort(c);
        return new UserFollowersDto(userDto.getUserId(), userDto.getUserName(), null, followed);
    }

    private List<UserBasicDto> getListUsers(List<Long> usersId) {
        List<UserBasicDto> users = new ArrayList<>();
        if (Objects.nonNull(usersId)) {
            usersId.forEach(x -> {
                UserDto user = this.userService.findByUserId(x);
                if (Objects.nonNull(user))
                    users.add(this.objectMapper.convertValue(user, UserBasicDto.class));
            });
        }
        return users;
    }

    private void validateUsersFollow(Long userId, Long userIdToFollow) throws AlreadyFollowedException, UserNotExistException, SameUserException {
        if (this.followerRepository.isFollowedByUserId(userId, userIdToFollow))
            throw new AlreadyFollowedException("El usuario " + userId
                    + " ya esta siguiendo al usuario " + userIdToFollow);
        this.userService.validateUserExist(userId);
        this.userService.validateSellerExist(userIdToFollow);
        if (userId.equals(userIdToFollow))
            throw new SameUserException("Un usuario no puede seguirse a si mismo");
    }

    private void validateUsersUnfollow(Long userId, Long userIdToUnfollow) throws UserNotFollowedException, UserNotExistException, SameUserException {
        if (!this.followerRepository.isFollowedByUserId(userId, userIdToUnfollow))
            throw new UserNotFollowedException("El usuario " + userId
                    + " no esta siguiendo al usuario " + userIdToUnfollow);
        this.userService.validateUserExist(userId);
        this.userService.validateSellerExist(userIdToUnfollow);
        if (userId.equals(userIdToUnfollow))
            throw new SameUserException("Un usuario no puede dejar de seguirse a si mismo");
    }


}
