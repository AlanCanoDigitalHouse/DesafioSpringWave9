package com.example.demo.services;

import com.example.demo.dtos.request.User;
import com.example.demo.dtos.response.FollowedList;
import com.example.demo.dtos.response.FollowersCount;
import com.example.demo.dtos.response.FollowersList;
import com.example.demo.dtos.response.UserDto;
import com.example.demo.exceptions.DuplicateUser;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.exceptions.UserRemoved;
import com.example.demo.respositories.UserImpl;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    UserImpl userImpl;

    public UserService(UserImpl userImpl) {
        this.userImpl = userImpl;
    }

    //Read file users.json
    public void addFollowing(Integer id, Integer idToFollow) throws UserNotFound, DuplicateUser {
        userImpl.addFollowing(id, idToFollow);
    }

    //Get the count of followers
    public FollowersCount followersCount(Integer id) throws UserNotFound, UserRemoved {
        User user = userImpl.getFollowers(id);
        FollowersCount followersCount = new FollowersCount();
        followersCount.setUserName(user.getName());
        followersCount.setUserId(user.getUserId());
        followersCount.setFollowers_count(user.getFollowers().size());
        return followersCount;
    }

    //Get the followers list by userId
    public FollowersList followersList(Integer id) throws UserNotFound, UserRemoved {
        User user = userImpl.getFollowers(id);
        FollowersList followersList = new FollowersList();
        followersList.setUserId(user.getUserId());
        followersList.setName(user.getName());
        followersList.setFollowers(user.getFollowers());
        return followersList;
    }

    //Get followed list by userId
    public FollowedList followedList(Integer id) throws UserNotFound, UserRemoved {
        User user = userImpl.getFollowers(id);
        FollowedList followedList = new FollowedList();
        followedList.setUserId(user.getUserId());
        followedList.setUserName(user.getName());
        followedList.setFollowed(user.getFollowing());
        return followedList;
    }

    //Remove followed user by userId
    @Deprecated
    public void unfollow(Integer id, Integer idToUnfollow) throws UserRemoved, UserNotFound {
        List<User> users = userImpl.loadDatabase();
        if(Objects.nonNull(users)){
            var userId = users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
            var userToUnfollow = users.stream().filter(user -> user.getUserId().equals(idToUnfollow)).findFirst();
            if (userId.isPresent() && userToUnfollow.isPresent()) {
                userImpl.unfollowUser(userId.get(), userToUnfollow.get(), users);
            }
        } else {
            throw new UserNotFound();
        }
    }

    public FollowedList getOrderFollowedList(String type, Integer id) throws UserNotFound, UserRemoved {
        FollowedList list = followedList(id);
        if(type.equals("name_asc"))
            Collections.sort(list.getFollowed(), Comparator.comparing(UserDto::getName));
        else if (type.equals("name_desc")) {
            Collections.sort(list.getFollowed(), (Comparator.comparing(UserDto::getName)));
            Collections.reverse(list.getFollowed());
        }
        return list;
    }

    public FollowersList getOrderFollowersList(String type, Integer id) throws UserNotFound, UserRemoved {
        FollowersList list = followersList(id);
        if(type.equals("name_asc"))
            Collections.sort(list.getFollowers(), Comparator.comparing(UserDto::getName));
        else if (type.equals("name_desc")) {
            Collections.sort(list.getFollowers(), (Comparator.comparing(UserDto::getName)));
            Collections.reverse(list.getFollowers());
        }
        return list;
    }
}
