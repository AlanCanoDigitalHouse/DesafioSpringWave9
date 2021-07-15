package com.mercadolibre.socialmeli.exceptions.users;

public class UserAlreadyUnfollowException extends Exception {

    private String followedVendor;
    private String followingUser;

    public UserAlreadyUnfollowException(String followedVendor, String followingUser) {
        this.followedVendor = followedVendor;
        this.followingUser = followingUser;
    }

    @Override
    public String getMessage() {
        return "The user: " + this.followingUser + " isn't following " + this.followedVendor;
    }

}
