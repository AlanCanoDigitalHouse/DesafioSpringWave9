package com.socialMeli.exception.exception;

public class UserDontFollowThisUser extends Exception {
    public UserDontFollowThisUser(String userName, Integer userNotFollowed) {
        super("The user " + userName + " not follow the user " + userNotFollowed + "");
    }
}
