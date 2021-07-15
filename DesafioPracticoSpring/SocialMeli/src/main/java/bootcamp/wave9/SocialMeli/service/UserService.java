package bootcamp.wave9.SocialMeli.service;

import bootcamp.wave9.SocialMeli.dto.UserDTO;
import bootcamp.wave9.SocialMeli.entity.User;
import bootcamp.wave9.SocialMeli.exception.UserNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {

    void follow(int userId, int userToFollowId) throws UserNotFoundException;
    void unfollow(int userId, int userIdToUnfollow) throws UserNotFoundException;
    Map<String,Object> getFollowersAmount(int userId) throws UserNotFoundException;
    Map<String,Object> getFollowersList(int userId, String order) throws UserNotFoundException;
    Map<String,Object> getFollowedList(int userId, String order) throws UserNotFoundException;
    void createUser(UserDTO user);
    List<User> getUserList();
    User getUserById(int userId) throws UserNotFoundException;
    User deleteUserById(int userId) throws UserNotFoundException;
}


