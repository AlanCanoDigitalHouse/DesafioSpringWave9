package com.example.desafiospring.services.user;


import com.example.desafiospring.dtos.User;
import com.example.desafiospring.exceptions.AlreadyFollow;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserDontHaveFollowersorFollowed;
import com.example.desafiospring.exceptions.UserNotExistException;

public interface UserService {

    void follow(Integer userId, Integer userIdToFollow) throws UserNotExistException, SameUserException, AlreadyFollow;
    User followersCount(Integer userId) throws UserNotExistException, UserDontHaveFollowersorFollowed;
    User followersList (Integer userId, String order) throws UserNotExistException, UserDontHaveFollowersorFollowed;
    User followedList(Integer userId, String order) throws UserNotExistException, UserDontHaveFollowersorFollowed;
    void unFollow(int userId, int userIdToUnfollow) throws UserNotExistException, SameUserException;
    void orderListFollowers (User user, String order) throws UserNotExistException;
    void orderListFollowed(User user, String order) throws UserNotExistException;

}
