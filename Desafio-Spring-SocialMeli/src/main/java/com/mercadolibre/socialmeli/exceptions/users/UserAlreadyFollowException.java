package com.mercadolibre.socialmeli.exceptions.users;

public class UserAlreadyFollowException extends Exception {

    private String followedVendor;
    private String followingUser;

    public UserAlreadyFollowException(String followedVendor, String followingUser) {
        this.followedVendor = followedVendor;
        this.followingUser = followingUser;
    }

    @Override
    public String getMessage() {
        return "The user: " + this.followingUser + " is already following " + this.followedVendor;
    }
}
