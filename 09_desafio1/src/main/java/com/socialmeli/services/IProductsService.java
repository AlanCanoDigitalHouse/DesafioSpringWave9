package com.socialmeli.services;

import com.socialmeli.dtos.ResponseDTO;
import com.socialmeli.dtos.request.PostRequestDTO;
import com.socialmeli.dtos.request.SortEnum;

public interface IProductsService {

    /**
     * service to add a post to SocialMeli
     * @param post post to add
     * @return OK status if post save was executed correctly
     */
    ResponseDTO addPostSocial(PostRequestDTO post);

    /**
     * Service to obtain the list of posts of sellers followed by a user (filtered by date).
     * @param idUser id of the user to return the posts of his followed sellers
     * @param sort ordering of posts by date
     * @return OK status and the list of posts if was executed correctly
     */
    ResponseDTO listPostFollowed(Integer idUser, SortEnum sort);

    /**
     * Service to list all posts published by sellers
     * @return list of posts
     */
    ResponseDTO listPosts();
}
