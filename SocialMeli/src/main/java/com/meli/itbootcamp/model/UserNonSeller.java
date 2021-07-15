package com.meli.itbootcamp.model;

import java.util.ArrayList;
import java.util.List;

public class UserNonSeller extends User {
    private List<UserSeller> sellerFollowing;

    public UserNonSeller(Integer userID, String userName) {
        super(userID, userName);
        sellerFollowing = new ArrayList<>();
    }

    public UserNonSeller (User newUser){
        super(newUser.getUserID(), newUser.getUserName());
        sellerFollowing = new ArrayList<>();
        }


    /**
     * US04
     * @return
     */
    public List<UserSeller> sellersFollowed() {
        return this.sellerFollowing;
    }

    /**
     * US01
     * @param newSeller
     * @return
     */
    public boolean followSeller(UserSeller newSeller) {
        if (newSeller.addFollower(this)) {
            return this.sellerFollowing.add(newSeller);
        }
        return false;
    }

    /**
     * US07
     * @param sellerToRemove
     * @return
     */
    public boolean unfollowSeller(UserSeller sellerToRemove) {
        if (sellerToRemove.removeFollower(this)) {
            return this.sellerFollowing.remove(sellerToRemove);
        }
        return false;
    }

    /**
     * US06
     * @return
     */
    public List<Post> latestPostFromSeller(){
        List<Post> tempPost = new ArrayList();
        sellerFollowing.forEach(seller->{tempPost.addAll(seller.getLatestPost());});
        return  tempPost;
    }

    }


