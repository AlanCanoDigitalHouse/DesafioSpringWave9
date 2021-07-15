package com.example.desafio1springboot.repositories;

import com.example.desafio1springboot.dtos.*;
import com.example.desafio1springboot.exceptions.*;
import com.example.desafio1springboot.handlers.RepositoryHandler;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements IUserRepository{
    private List<UserSellerDTO> userSellerDB;

    public UserRepositoryImpl() {
        this.userSellerDB = RepositoryHandler.loadDatabaseUserSeller();
    }

    @Override
    public UserSellerDTO getUserSellerByIdAndCheckIfExists(Integer userId) throws UserSellerNotFoundExceptions {
        UserSellerDTO userSeller = userSellerDB.stream().filter(userSellerDTO -> userSellerDTO.getUserId().equals(userId)).findFirst().orElse(null);
        if(Objects.isNull(userSeller))
            throw new UserSellerNotFoundExceptions();
        return userSeller;
    }

    @Override
    public void checkIfUserClientExistsInUserSeller(Integer userId, UserSellerDTO userSeller) throws UserAlreadyFollowingSellerException {
        Optional<UserDTO> user = userSeller.getFollowers().stream().filter(userDTO -> userDTO.getUserId().equals(userId)).findFirst();
        UserDTO result = null;
        if(user.isPresent())
            throw new UserAlreadyFollowingSellerException();
    }

    @Override
    public UserDTO addClient_FollowerToUserSeller_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserAlreadyFollowingSellerException { // check nombre del metodo
        UserSellerDTO userSellerToFollow = getUserSellerByIdAndCheckIfExists(userIdToFollow);
        checkIfUserClientExistsInUserSeller(userId, userSellerToFollow);
        UserDTO user = new UserDTO(userId, "client" + (userId));

        for(Integer i=0; i < userSellerDB.size(); i++) {
            if(userIdToFollow.equals(userSellerDB.get(i).getUserId()))
                userSellerDB.get(i).getFollowers().add(user);
        }
        return user;
    }

    @Override
    public UserClientDTO followedListOfClient_(Integer userId) throws UserClientDoesNotExistsException {
        UserClientDTO userClient = new UserClientDTO();
        userClient.setUserId(userId);
        List<UserDTO> sellerFollowed = new ArrayList<>();

        for(UserSellerDTO userSellerDTO : userSellerDB) {
            UserDTO userClientFound = userSellerDTO.getFollowers().stream().filter(userDTO -> userDTO.getUserId().equals(userId)).findFirst().orElse(null);
            if(Objects.nonNull(userClientFound)) {
                userClient.setUserName(userClientFound.getUserName());
                sellerFollowed.add(new UserDTO(userSellerDTO.getUserId(), userSellerDTO.getUserName()));
            }
        }
        if(Objects.isNull(userClient.getUserName()))
            throw new UserClientDoesNotExistsException();
        userClient.setFollowed(sellerFollowed);
        return userClient;
    }

    @Override
    public void unfollowByClient_TheUserSeller_(Integer userId, Integer userIdToFollow) throws UserSellerNotFoundExceptions, UserClientNotFollowingSellerException {
        UserSellerDTO userSellerToUnfollow = getUserSellerByIdAndCheckIfExists(userIdToFollow);
        if(!userSellerToUnfollow.getFollowers().removeIf(userDTO -> userDTO.getUserId().equals(userId)))
            throw new UserClientNotFollowingSellerException();

        for(UserSellerDTO userSellerDTO : userSellerDB) {
            if(userSellerDTO.getUserId().equals(userSellerDTO)) {
                userSellerDTO.setFollowers(userSellerToUnfollow.getFollowers());
                break;
            }
        }
    }

}
