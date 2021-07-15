package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.dto.request.PostRequestDTO;
import com.meli.socialmeli.dto.response.FollowCountResponseDTO;
import com.meli.socialmeli.dto.response.FollowListResponseDTO;
import com.meli.socialmeli.dto.response.FollowedListResponseDTO;
import com.meli.socialmeli.dto.response.PostListResponse;
import com.meli.socialmeli.exceptions.*;
import org.springframework.http.ResponseEntity;

public interface UserServiceInterface {
    ResponseEntity<?> followUser(Integer userId, Integer userIdFollow) throws UserNullException, DataBaseException;
    ResponseEntity<FollowCountResponseDTO> countFollow(Integer userId) throws UserNullException, DataBaseException;
    ResponseEntity<FollowListResponseDTO> obtainFollowList(Integer userId, String order) throws UserNullException, DataBaseException;
    ResponseEntity<?> unFollowUser(Integer userId, Integer userIdFollow) throws UserNullException, DataBaseException;
    ResponseEntity<FollowedListResponseDTO> obtainFollowedList(Integer userId,String order) throws UserNullException, DataBaseException;
    ResponseEntity<?> newPost(PostRequestDTO request) throws UserNullException, DataBaseException, RepeatedPostException, ProductNullException, DateNotValidException;
    ResponseEntity<PostListResponse> obtainPostList(Integer userId) throws UserNullException, DataBaseException;
}
