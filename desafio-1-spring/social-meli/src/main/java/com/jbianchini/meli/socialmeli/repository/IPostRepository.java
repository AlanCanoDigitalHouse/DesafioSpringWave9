package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.Post;
import com.jbianchini.meli.socialmeli.model.User;

import java.util.List;

public interface IPostRepository {
    /** Saves a post
     * @param post {@link Post} a post
     * @return the new Post
     */
    Post save(Post post);

    /** Finds post by post id
     * @param postId post id
     * @return a {@link Post}
     */
    Post findByPostId(Integer postId);

    /**Deletes a post
     * @param post a Post
     */
    void delete(Post post);

    /** Finds Posts from at most two weeks ago filtering by user.
     * @param user {@link User} who owns the posts.
     * @return List containing all the posts.
     */
    List<Post> findByUserSinceTwoWeeksAgo(User user);
}
