package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.PostDTO;
import com.jbianchini.meli.socialmeli.dto.PostsByFollowerDTO;
import com.jbianchini.meli.socialmeli.dto.response.ResponseDTO;

public interface IPostService {
    /** Creates a new {@link com.jbianchini.meli.socialmeli.model.Post}.
     * @param postDTO dto containing the new  post info.
     * @return a {@link ResponseDTO} with the status and the post information created.
     */
    ResponseDTO newPost(PostDTO postDTO);

    /** Returns a list with the posts of the users that the user with id "userId" follows, on a specific order.
     * @param userId follower user id
     * @param order String specifying order
     * @return a {@link PostsByFollowerDTO} with the posts list.
     */
    PostsByFollowerDTO getFollowedPostsByUserId(Integer userId, String order);
}
