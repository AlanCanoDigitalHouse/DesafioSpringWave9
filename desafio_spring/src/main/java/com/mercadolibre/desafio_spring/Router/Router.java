package com.mercadolibre.desafio_spring.Router;

public class Router {
    public static final String FOLLOW="/users/{userId}/follow/{userIdToFollow}";
    public static final String GET_FOLLOWERS_COUNT = "/users/{userID}/followers/count";
    public static final String GET_FOLLOWERS_LIST = "/users/{UserID}/followers/list";
    public static final String GET_FOLLOWED_LIST = "/users/{UserID}/followed/list";
    public static final String NEW_POST = "/products/newpost";
    public static final String PRODUCTS_FOLLOWED = "/products/followed/{userId}/list";
    public static final String UNFOLLOW = "/users/{userId}/unfollow/{userIdToUnfollow}";
    public static final String SORTED_FOLLOWERS_BY_ALF = "/users/{UserID}/followers/list";
    public static final String SORTED_FOLLOWED_BY_ALF = "/users/{UserID}/followed/list";
    public static final String SORTED_PRODUCTS_BY_DATE = "/products/followed/{userId}/list";
    public static final String NEW_PROMO_POST ="/products/newpromopost";
    public static final String COUNT_PROMOS = "/products/{userId}/countPromo/";
    public static final String LISTS_PROMOS_POST = "/products/{userId}/list";
    public static final String CREATE_NEW_CAMPAING = "/products/{user_id}/newcampaing";
    public static final String NEW_DONATION = "/products/{userId}/campaing";
    public static final String GET_CAMPAINGS_USER = "/products/{userId}/campaings/list";
}
