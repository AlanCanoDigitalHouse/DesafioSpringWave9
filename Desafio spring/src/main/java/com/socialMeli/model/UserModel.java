package com.socialMeli.model;

import com.socialMeli.exception.exception.UserDontFollowThisUser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel extends AbstractModel {
    private String userName;
    private List<Integer> followed;

    public UserModel() {
        followed = new ArrayList<>();
    }

    @Override
    public String getModelClassName() {
        return "user";
    }

    public void addNewUserFollowed(Integer newModel) {
        followed.add(newModel);
    }

    public void unFollowUser(Integer userId) throws UserDontFollowThisUser {
        boolean removed = followed.remove(userId);
        if (!removed) throw new UserDontFollowThisUser(userName, userId);
    }

}
