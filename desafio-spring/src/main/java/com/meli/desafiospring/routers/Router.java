package com.meli.desafiospring.routers;

public class Router {

    public static final String FOLLOW = "/{userId}/follow/{userIdToFollow}";
    public static final String UNFOLLOW = "/{userId}/unfollow/{userIdToUnfollow}";
    public static final String FOLLOWERS_COUNT = "/{userId}/followers/count/";
    public static final String FOLLOWERS_LIST = "/{userId}/followers/list";
    public static final String FOLLOWED_LIST = "/{userId}/followed/list";

    public static final String NEW_POST = "/newpost";
    public static final String FOLLOWED_POST_LIST = "/followed/{userId}/list";

}
