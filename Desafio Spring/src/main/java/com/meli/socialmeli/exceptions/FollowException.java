package com.meli.socialmeli.exceptions;

public class FollowException extends Exception{
    public String ERROR;
    public FollowException(int user, int userToUnfollow){
        super();
        if(user == userToUnfollow){
            this.ERROR = "Cannot autofollow user " + user;
        }else{
            this.ERROR = "User " + user + " does not follow user " + userToUnfollow;
        }
    }
}
