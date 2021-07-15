package com.meli.itbootcamp.controllers;

import com.meli.itbootcamp.dto.ResponseDTO;
import com.meli.itbootcamp.dto.UserCountDTO;
import com.meli.itbootcamp.dto.UserFollowsDTO;
import com.meli.itbootcamp.exceptions.UserException;
import com.meli.itbootcamp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserServices userServices;

    /**
     * US0001- Que un usuario siga a un  vendedor
     * @param userId    id del usuario que seguira al vendedor
     * @param userIdToFollow    id del vendedor que sera seguido por el usuario
     * @return  resultado al completar el seguimiento
     * @throws UserException    si los id son identicos, o si alguno de ellos no se corresponde a un usuario exitente
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<ResponseDTO> followSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws UserException {

        return new ResponseEntity<>(userServices.followSeller(userId, userIdToFollow),HttpStatus.OK);
    }

    /**
     * US0002- Cantidad de usuario que siguen a un vendedor
     * @param userId usuario cuya cantidad de followers necestimas
     * @return  Datos del vendedor, y la cantidad de seguidores
     * @throws UserException si el usuario que se paso no existe o no es un vendedor
     */
    @GetMapping("/{userId}/followers/count/")
    public UserCountDTO countSellerFollowers(@PathVariable Integer userId) throws UserException {
        return userServices.numberFollowers(userId);
    }

    /**
     * US0003- Lista de seguidores de un vendedor
     * @param UserId vendedor a analizar
     * @param orderBy orden en que se presentan los valores del listado (ASC/DES) (US0008)
     * @return El listado de los detalles de todos los usuarios que siguen al vendedor
     * @throws UserException si el usuario que se paso no existe o no es un vendedor
     */
    @GetMapping("/{UserId}/followers/list")
    public UserFollowsDTO sellerFollowers(@PathVariable Integer UserId,
                                          @RequestParam(value = "order") Optional<String> orderBy) throws UserException {
        return userServices.followersList(UserId, orderBy);
    }

    /**
     * US0004- Lista de todos los vendedores que son seguidos por un usuario
     * @param UserId El usuario del que se desea conocer el listado de seguidos
     * @param orderBy orden en que se presentan los valores del listado (ASC/DES) (US0008)
     * @return Listado de los detalles de todos los vendedores que son seguidos por el usuario
     * @throws UserException si el usuario que se paso no existe o es un usuario vendedor.
     */
    @GetMapping("/{UserId}/followed/list")
    public UserFollowsDTO userFollows(@PathVariable Integer UserId,@RequestParam("order") Optional<String> orderBy) throws UserException {
        return userServices.followedList(UserId,orderBy);
    }

    /**
     * US0007- peticion para dejar de seguir un vendedor
     * @param userId el usuario que realiza la peticion
     * @param userIdToUnfollow el vendedor que se desea dejar de seguir
     * @return Detalle de la operacion de unfollow
     * @throws UserException si los usuarios no existen o no correspenden a un usuario y un vendedor, respectivamente
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<ResponseDTO> unfollowSeller(@PathVariable Integer userId,
    @PathVariable Integer userIdToUnfollow) throws UserException {
            return  new ResponseEntity<>(userServices.unfollowSeller(userId,userIdToUnfollow),HttpStatus.OK);
    }

    public  UserServices getService(){ return  userServices;}
}
