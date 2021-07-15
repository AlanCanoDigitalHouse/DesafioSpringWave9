package com.socialMeli.model;

import com.socialMeli.exception.exception.UserDontFollowThisUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel extends AbstractModel {
    private static String typeClass = "USER";

    private String userName;
    private List<Integer> followed;


    @Builder
    public UserModel(int id, String userName) {
        super(id);
        this.userName = userName;
        this.followed = new ArrayList<>();
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

    //Needed because Jackson uses a default constructor other try is use @JsonTypeInfo and @JsonSubTypes
    //But after 2 hours i cant make this functional :(
    private UserModel() {
        super();
    }

}
