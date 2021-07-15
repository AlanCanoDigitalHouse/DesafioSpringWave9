package bootcamp.wave9.SocialMeli.entity;

import bootcamp.wave9.SocialMeli.dto.UserDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class User {

    private int userId;
    private String userName;
    private List<UserDTO> followedList;
    private List<UserDTO> followerList;

    public User() {
        this.followedList = new ArrayList<>();
        this.followerList = new ArrayList<>();
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.followedList = new ArrayList<>();
        this.followerList = new ArrayList<>();
    }

    public void followUser(User user) {

        this.followedList.add(new UserDTO(user.getUserId(), user.getUserName()));
    }

    public void addFollower(User user) {

        this.followerList.add(new UserDTO(user.getUserId(), user.getUserName()));
    }

    public boolean unfollowUser(User user) {

        int size = this.followedList.size();

        this.followedList = this.followedList.stream().filter(u -> u.getUserId() != user.getUserId()).collect(Collectors.toList());

        return size != this.followedList.size();
    }

    public boolean removeFollower(User user) {

        int size = this.followerList.size();

        this.followerList = this.followerList.stream().filter(u -> u.getUserId() != user.getUserId()).collect(Collectors.toList());

        return size != this.followerList.size();
    }

}
