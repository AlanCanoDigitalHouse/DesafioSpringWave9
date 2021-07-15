package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

    private Integer userId;
    private String userName;
    private List<User> followed;
    private List<User> followers;
    @JsonIgnore
    @JsonProperty(value = "isSeller")
    private Boolean isSeller;
    private Integer followersCount;

    public void addFollowers (User user) {
        if(followers == null) {
            followers = new ArrayList<>();
        }

        followers.add(new User(user.getUserId(), user.getUserName(),null,null,null,null));
    }

    public void addFollowed (User user) {
        if(followed == null) {
            followed = new ArrayList<>();
        }
        followed.add(new User(user.getUserId(), user.getUserName(),null,null,null,null));
    }

    public void removeFollower (User user) {
        followers.remove(new User(user.getUserId(), user.getUserName(),null,null,null,null));
    }

    public void removeFollowed (User user) {
        followed.remove(new User(user.getUserId(), user.getUserName(),null,null,null,null));
    }


}
