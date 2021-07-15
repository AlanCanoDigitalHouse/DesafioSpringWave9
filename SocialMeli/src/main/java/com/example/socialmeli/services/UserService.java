package com.example.socialmeli.services;

import com.example.socialmeli.dtos.FollowDto;
import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.responses.AllFollowedResponseDto;
import com.example.socialmeli.dtos.responses.AllFollowersResponseDto;
import com.example.socialmeli.dtos.responses.AllUserResponseDto;
import com.example.socialmeli.dtos.responses.CountFollowersResponseDto;
import com.example.socialmeli.exceptions.NotFoundException;
import com.example.socialmeli.repositories.IUserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(Integer userId, Integer idToFollow) {
        if (userId.equals(idToFollow)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No es posible seguirse a si mismo");
        }
        UserDto user = userRepository.findUser(userId);
        UserDto userToFollow = userRepository.findUser(idToFollow);
        editFollow(user,userToFollow);
        userRepository.saveUsers();
    }

    private void editFollow(UserDto user, UserDto userToFollow){
        if (user.getFollowing() == null || userToFollow.getFollowers() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No es posible realizar la operación, verifique colocar un usuario y un vendedor");
        }
        user.addFollowing(userToFollow);
        userToFollow.addFollower(user);
    }

    @Override
    public CountFollowersResponseDto countFollowers(Integer userId) {
        UserDto user = userRepository.findUser(userId);
        if (user.getFollowers() == null)
            throw new NotFoundException("El usuario no puede ser seguido");
        return new CountFollowersResponseDto(user.getUserId(), user.getUserName(), user.getFollowers().size());
    }

    @Override
    public AllFollowersResponseDto allFollowers(Integer userId, String order) {
        UserDto user = userRepository.findUser(userId);
        if (user.getFollowers() == null)
            throw new NotFoundException("El usuario no puede ser seguido");
        Comparator<FollowDto> comparator = nameComparator(order);
        return new AllFollowersResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getFollowers().stream().sorted(comparator).collect(Collectors.toList())
        );
    }

    private Comparator<FollowDto> nameComparator(String order){
        if(order != null && !order.isEmpty() && order.equals("name_asc")){
            return (f1,f2) -> f1.getUserName().compareTo(f2.getUserName());
        }
        return (f1,f2) -> f2.getUserName().compareTo(f1.getUserName());
    }

    @Override
    public AllFollowedResponseDto allFollowed(Integer userId, String order) {
        UserDto user = userRepository.findUser(userId);
        if (user.getFollowing() == null)
            throw new NotFoundException("El usuario no puede seguir usuarios");
        Comparator<FollowDto> comparator = nameComparator(order);
        return new AllFollowedResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getFollowing().stream().sorted(comparator).collect(Collectors.toList())
        );
    }


    @Override
    public void unfollow(Integer userId, Integer idToUnfollow) {
        if (userId == idToUnfollow){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No es posible seguirse a si mismo");
        }
        UserDto user = userRepository.findUser(userId);
        UserDto userToUnfollow = userRepository.findUser(idToUnfollow);
        user.removeFollowing(idToUnfollow);
        userToUnfollow.removeFollower(userId);
        userRepository.saveUsers();
    }

    @Override
    public UserDto findUser(Integer userId) {
        return userRepository.findUser(userId);
    }

    @Override
    public AllUserResponseDto allUsers(){
        return new AllUserResponseDto(
            userRepository.getUsers().stream().filter(userDto -> userDto.getFollowers() == null).collect(Collectors.toList()),
            userRepository.getUsers().stream().filter(userDto -> userDto.getFollowing() == null).collect(Collectors.toList())
        );
    }
}
