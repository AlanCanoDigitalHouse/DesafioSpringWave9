package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.request.PostRequestDTO;
import com.meli.socialmeli.dto.response.PostListResponse;
import com.meli.socialmeli.exceptions.*;
import com.meli.socialmeli.service.UserService;
import com.meli.socialmeli.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.URIPRODUCT)
public class ProductController {

    @Autowired
    UserService userService;

    /**
     * Metodo para dar de alta una nueva publicacion
     *
     * @param request {PostRequestDTO} id of the user.
     * @return {UserDTO} user fount.
     * @author Garduño Perez Josue Daniel
     **/
    @PostMapping(value = Constant.URINEWPOST)
    public ResponseEntity<?> newPost(@RequestBody PostRequestDTO request) throws UserNullException, DataBaseException,
            RepeatedPostException, DateNotValidException {
        return userService.newPost(request);
    }

    /**
     * Metodo para conocer las publicaciones realizadas por los que sigue el usuario
     *
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     * @author Garduño Perez Josue Daniel
     **/
    @RequestMapping(value = Constant.URIPRODUCTLIST, method = RequestMethod.GET)
    public ResponseEntity<PostListResponse> obtainPostList(@PathVariable(value = "userId") Integer userId, @RequestParam(defaultValue = Constant.ORDERNAMIENTO_ASC)
            String order) throws UserNullException, DataBaseException {
        return userService.obtainPostList(userId, order);
    }
}
