package com.meli.itbootcamp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserSeller extends User {

    private List<User> usersFollowme;
    private List<Post> listPosts;
    private List<Promo> listPromo;


    public UserSeller(Integer userID, String userName) {
        super(userID, userName);
        usersFollowme = new ArrayList<>();
        listPosts = new ArrayList<>();
        listPromo = new ArrayList<>();
    }

    public UserSeller(User newUser) {
        super(newUser.getUserID(), newUser.getUserName());
        usersFollowme = new ArrayList<>();
        listPosts = new ArrayList<>();
        listPromo = new ArrayList<>();
    }

    /**
     * US01
     *
     * @param newFollower
     * @return
     */
    public boolean addFollower(User newFollower) {
        if (Objects.nonNull(usersFollowme)) {
            return usersFollowme.add(newFollower);
        }
        return false;
    }

    /**
     * US07
     *
     * @param userToRemove
     * @return
     */
    public boolean removeFollower(User userToRemove) {
        return usersFollowme.remove(userToRemove);
    }

    /**
     * US03
     *
     * @return user who follow the seller
     */
    public List<User> listFollowers() {
        return usersFollowme;
    }

    /**
     * US02
     *
     * @return quantity of user following the seller
     */
    public Integer quantFollowers() {
        return usersFollowme.size();
    }

    /**
     * US06
     *
     * @return collection of post made on the span of two week from today
     */
    public List<Post> getLatestPost() {
        LocalDate twoWeeks = LocalDate.now().minusWeeks(2);
        return listPosts.stream().filter(post -> post.getDate().after(java.sql.Date.valueOf(twoWeeks))).collect(Collectors.toList());
    }

    /**
     * US05
     */
    public Boolean addNewPost(Post newPost) {
        return listPosts.add(newPost);
    }

    /**
     * US10
     * @param newPromo
     * @return
     */
    public Boolean addNewPromo(Promo newPromo) {

        return listPromo.add(newPromo);
    }

    /**
     * US11
     * @return
     */
    public Integer countPromo(){
        return  listPromo.size();
    }

    public List<Promo> getAllPromo(){
        return  listPromo;
    }

}
