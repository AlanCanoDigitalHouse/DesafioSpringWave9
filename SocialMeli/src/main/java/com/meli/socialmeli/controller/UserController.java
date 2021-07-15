package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.response.FollowCountResponseDTO;
import com.meli.socialmeli.dto.response.FollowListResponseDTO;
import com.meli.socialmeli.dto.response.FollowedListResponseDTO;
import com.meli.socialmeli.exceptions.DataBaseException;
import com.meli.socialmeli.exceptions.UserNullException;
import com.meli.socialmeli.service.UserService;
import com.meli.socialmeli.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.URIUSER)
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Metodo para que un usuario pueda seguir a otro
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id del usuario
     * @param userIdFollow {Integer} id del usuario que quiere seguir.
     * @return {UserDTO} user fount.
     * */
    @RequestMapping(value = Constant.URIFOLLOW, method= RequestMethod.POST)
    public ResponseEntity<?> follow(@PathVariable(value = "userId")Integer userId,
                                    @PathVariable(value = "userIdToFollow")Integer userIdFollow) throws UserNullException, DataBaseException {
            return userService.followUser(userId,userIdFollow);
    }

    /**
     * Metodo para contar cuantos seguidores tiene un usuario
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id del usuario.
     * @return {ResponseEntity<FollowResponseDTO>} respuesta del servicio.
     **/
    @RequestMapping(value = Constant.URICOUNTFOLLOWER, method= RequestMethod.GET)
    public ResponseEntity<FollowCountResponseDTO> countFollow(@PathVariable(value = "userId")Integer userId) throws UserNullException, DataBaseException {
        return userService.countFollow(userId);
    }

    /**
     * Metodo para conocer los seguidores que tiene un usuario
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     **/
    @RequestMapping(value = Constant.URIFOLLOWERSLIST, method= RequestMethod.GET)
    public ResponseEntity<FollowListResponseDTO> obtainFollowList(@PathVariable(value = "UserID")Integer userId,@RequestParam(defaultValue = Constant.ORDERNAMIENTO_ASC)
            String order) throws UserNullException, DataBaseException {
        return userService.obtainFollowList(userId, order);
    }

    /**
     * Metodo para conocer a quienes sigue un usuario
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     **/
    @RequestMapping(value = Constant.URIFOLLOWEDLIST, method= RequestMethod.GET)
    public ResponseEntity<FollowedListResponseDTO> obtainFollowedList(@PathVariable(value = "UserID")Integer userId, @RequestParam(defaultValue = Constant.ORDERNAMIENTO_ASC)
            String order) throws UserNullException, DataBaseException {
        return userService.obtainFollowedList(userId,order);
    }

    /**
     * Metodo para dejar de seguir a alguien
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id del usuario
     * @param userIdToUnfollow {Integer} id del usuario que quiere seguir.
     * @return {UserDTO} user fount.
     **/
    @RequestMapping(value = Constant.URIUNFOLLOW, method= RequestMethod.POST)
    public ResponseEntity<?> unFollow(@PathVariable(value = "userId")Integer userId,
                                            @PathVariable(value = "userIdToUnfollow")Integer userIdToUnfollow) throws UserNullException, DataBaseException {
        return userService.unFollowUser(userId,userIdToUnfollow);
    }
}
