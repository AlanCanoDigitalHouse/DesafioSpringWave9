package com.example.desafiospring.repository;


import com.example.desafiospring.dtos.FollowDTO;
import com.example.desafiospring.dtos.request.UserRequestDTO;
import com.example.desafiospring.exceptions.UserNotFollowedException;

import java.util.List;

public interface IUserRepository {

    boolean userExsistsDB(int pUserId);

    List<UserRequestDTO> getUsersDB();

    UserRequestDTO getUserDB(int pUserId);

    FollowDTO getUserFollowedDB(UserRequestDTO pUser, int pUserIdFollowed) throws UserNotFollowedException;

    FollowDTO getUserFollowerDB(UserRequestDTO pUser, int pUserIdFollowed);

}
