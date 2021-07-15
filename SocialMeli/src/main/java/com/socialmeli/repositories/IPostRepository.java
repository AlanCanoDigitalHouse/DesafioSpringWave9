package com.socialmeli.repositories;

import com.socialmeli.models.PostSocial;
import com.socialmeli.models.UserSocial;
import com.socialmeli.repositories.impl.PostRepository;

import java.util.ArrayList;

public interface IPostRepository {

    /**
     * Search post in the repository from the repository ID
     * @param Id post id to be searched
     * @return post found
     */
    PostSocial findById(Integer Id);

    /**
     * Save a new post in the repository
     * @param post post to save
     * @return saved post
     */
    PostSocial savePost(PostSocial post);

    /***
     * Removes a post from the repository
     * @param post post to be deleted
     */
    void delete(PostSocial post);

    /**
     * Sort by past date range of posts in the repository
     * @param daysAgo
     * @return
     */
    ArrayList<PostSocial> filterByDate(Integer daysAgo);

    /**
     * List all posts in the repository
     * @return list of posts
     */
    ArrayList<PostSocial> list();

}
