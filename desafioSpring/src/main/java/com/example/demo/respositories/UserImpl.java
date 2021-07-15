package com.example.demo.respositories;

import com.example.demo.dtos.request.User;
import com.example.demo.dtos.response.UserDto;
import com.example.demo.exceptions.DuplicateUser;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.exceptions.UserRemoved;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class UserImpl implements SocialMedia{

    private static final String PATH = System.getProperty("user.dir") + "/src/main/resources/static/users.json";

    private List<User> users;

    public UserImpl(List<User> users) {
        this.users = loadDatabase();
    }

    public void addFollowing(Integer id, Integer idToFollow) throws UserNotFound, DuplicateUser {
        users = loadDatabase();
        if (Objects.nonNull(users)) {
            var userId = users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
            var userToFollow = users.stream().filter(user -> user.getUserId().equals(idToFollow)).findFirst();
            if (userId.isPresent() && userToFollow.isPresent()) {
                updateFile(userId.get(), userToFollow.get());
                users = loadDatabase();
            } else {
                throw new UserNotFound();
            }
        }
    }

    //Get the followers count for an specific user
    public User getFollowers(Integer userId) throws UserNotFound {
        users = loadDatabase();
        if(Objects.nonNull(users)){
            var optionalUser = users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
            if (optionalUser.isPresent())
                return optionalUser.get();
            else
                throw new UserNotFound();
        }
        //Review the exception
        //throw new UserNotFound();
        return null;
    }

    //Remove follower and following user
    public void unfollowUser(User user, User toUnfollow, List<User> users) throws UserRemoved {
        List<UserDto> following = user.getFollowing();
        List<UserDto> followed = toUnfollow.getFollowers();
        Boolean userIsFollowing = userExistUnfollow(following, toUnfollow.getUserId());
        Boolean userIsFollowed = userExistUnfollow(followed, user.getUserId());
        if(userIsFollowing && userIsFollowed) {
            UserDto userFollowing = new UserDto();
            userFollowing.setName(toUnfollow.getName());
            userFollowing.setUserId(toUnfollow.getUserId());
            UserDto userFollowed = new UserDto();
            userFollowed.setName(user.getName());
            userFollowed.setUserId(user.getUserId());
            following.remove(userFollowing);
            followed.remove(userFollowed);
            try {
                var file = ResourceUtils.getFile(PATH);
                var mapper = new ObjectMapper();
                mapper.writeValue(file, new ArrayList<>(users));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            throw new UserRemoved();
        }
    }

    public Boolean userExist(List<UserDto> list, Integer userId) throws DuplicateUser {
        if(list.isEmpty())
            return false;
        else {
            var optionalUser = list.stream().filter(follower -> follower.getUserId().equals(userId)).findFirst();
            if(optionalUser.isPresent())
                throw new DuplicateUser();
            else
                return false;
        }
    }

    public Boolean userExistUnfollow(List<UserDto> list, Integer userId) throws UserRemoved {
        var optionalUser = list.stream().filter(follower -> follower.getUserId().equals(userId)).findFirst();
        if(optionalUser.isPresent()) {
            return true;
        }
        throw new UserRemoved();
    }

    private void updateFile(User user, User toFollow) throws DuplicateUser {
        List<UserDto> following = user.getFollowing();
        List<UserDto> followed = toFollow.getFollowers();
        Boolean userIsFollowing = userExist(following, toFollow.getUserId());
        Boolean userIsFollowed = userExist(followed, user.getUserId());
        if(!userIsFollowing && !userIsFollowed) {
            UserDto userFollowing = new UserDto();
            userFollowing.setName(toFollow.getName());
            userFollowing.setUserId(toFollow.getUserId());
            UserDto userFollowed = new UserDto();
            userFollowed.setName(user.getName());
            userFollowed.setUserId(user.getUserId());
            following.add(userFollowing);
            followed.add(userFollowed);
            try {
                var file = ResourceUtils.getFile(PATH);
                var mapper = new ObjectMapper();
                mapper.writeValue(file, new ArrayList<>(users));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new DuplicateUser();
        }

    }

    @Override
    public List<User> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    @Override
    public List<User> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {};
        List<User> userList = null;
        try {
            userList = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
