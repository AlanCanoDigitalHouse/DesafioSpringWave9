package com.example.socialmeli.models;
import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.request.PostDto;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {

    private Integer userId;
    private String userName;
    private List<UserDto> followed=new ArrayList<>();
    private List<UserDto> followers=new ArrayList<>();
    private List<PostDto> posts=new ArrayList<>();

    public Integer countFollowers() {
        return followers.size();
    }

    public void addFollower(UserDto follower) {
        followers.add(follower);
    }

    public void addFollowed(UserDto userFollowed) {
        followed.add(userFollowed);
    }

    public void removeFollower(UserDto follower){
        followers.remove(follower);
    }

    public void removeFollowed(UserDto userFollowed){
        followed.remove(userFollowed);
    }

    public void addPost(PostDto post){
        posts.add(post);
    }
}
