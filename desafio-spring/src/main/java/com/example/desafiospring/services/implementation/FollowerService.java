package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.repositories.IFollowerRepository;
import com.example.desafiospring.services.IFollowerService;
import com.example.desafiospring.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FollowerService implements IFollowerService {

    private final IFollowerRepository followerRepository;
    private final IUserService userService;

    public FollowerService(IFollowerRepository followerRepository, IUserService userService) {
        this.followerRepository = followerRepository;
        this.userService = userService;
    }

    @Override
    public void followUserById(Long userId, Long userIdToFollow) throws AlreadyFollowedException, UserNotExistException {
        this.validateUsers(userId, userIdToFollow);
        this.followerRepository.followUserById(userId, userIdToFollow);
    }

    @Override
    public UserFollowersDto numFollowersByUserId(Long userId) throws UserNotExistException {
        UserDto userDto = this.userService.findByUserId(userId, true);
        if (Objects.isNull(userDto))
            throw new UserNotExistException("No existe un vendedor con el id " + userId);
        Long numFollowers = this.followerRepository.getNumFollowersById(userId);
        return new UserFollowersDto(userDto.getUserId(), userDto.getUserName(), numFollowers);
    }

    private void validateUsers(Long userId, Long userIdToFollow) throws AlreadyFollowedException, UserNotExistException {
        if (this.followerRepository.isFollowedByUserId(userId, userIdToFollow))
            throw new AlreadyFollowedException("El usuario " + userId
                    + " ya esta siguiendo al usuario " + userIdToFollow);
        if (Objects.isNull(this.userService.findByUserId(userId, false)))
            throw new UserNotExistException("No existe un comprador con el id " + userId);
        if (Objects.isNull(this.userService.findByUserId(userIdToFollow, true)))
            throw new UserNotExistException("No existe un vendedor con el id " + userIdToFollow);
    }

}
