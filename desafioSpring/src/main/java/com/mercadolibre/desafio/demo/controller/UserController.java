package com.mercadolibre.desafio.demo.controller;
import com.mercadolibre.desafio.demo.dtos.response.FollowedListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.FollowersCountResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.FollowersListResponseDTO;
import com.mercadolibre.desafio.demo.dtos.response.SuccessfullyResponseDTO;
import com.mercadolibre.desafio.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * TODO: REQUEST TO US 0001 - poder realizar la acción de “Follow” (seguir) a un determinado vendedor
     * POST /users/{userId}/follow/{userIdToFollow}
     * @param userId
     * @param userIdToFollow
     * @return
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<SuccessfullyResponseDTO> follow(@PathVariable(value="userId") Integer userId, @PathVariable(value="userIdToFollow") Integer userIdToFollow) {
        return new ResponseEntity<>(userService.addFollower(userIdToFollow,userId), HttpStatus.OK);
    }


    /**
     * TODO: REQUEST TO US 0002 - Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
     * GET /users/{userId}/followers/count/
     * @param userId
     * @return ResponseEntity<OkResponse>
     */
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponseDTO> countFollowers(@PathVariable(value="userId") Integer userId){
        return new ResponseEntity<>(userService.countFollowers(userId),HttpStatus.OK);
    }


    /**
     * TODO: REQUEST TO US 0003 - Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
     * GET /users/{UserID}/followers/list
     * TODO: US 0008: Ordenamiento alfabético ascendente y descendente
     * GET users/{UserID}/followers/list?order=name_asc
     * GET users/{UserID}/followers/list?order=name_desc
     * @param userId
     * @return ResponseEntity<FollowersListResponseDTO>
     */
    @GetMapping("/{UserID}/followers/list")
    public ResponseEntity<FollowersListResponseDTO> followersList(@PathVariable(value="UserID") Integer userId, @RequestParam(value = "order", required = false) String order){
        return new ResponseEntity<>(userService.listFollowers(userId,order),HttpStatus.OK);
    }


    /**
     * TODO: REQUEST TO US 0004 - Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
     * GET /users/{UserID}/followed/list
     * TODO: US 0008: Ordenamiento alfabético ascendente y descendente
     * GET /users/{UserID}/followed/list?order=name_asc
     * GET /users/{UserID}/followed/list?order=name_desc
     * @param userId
     * @return ResponseEntity<FollowedListResponseDTO>
     */
    @GetMapping("/{UserID}/followed/list")
    public ResponseEntity<FollowedListResponseDTO> followedList(@PathVariable(value="UserID") Integer userId, @RequestParam(value = "order", required = false) String order) {
        return new ResponseEntity<>(userService.listFollowing(userId,order),HttpStatus.OK);
    }


    /**
     * TODO: REQUEST TO US 0007 - Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
     * POST /users/{userId}/unfollow/{userIdToUnfollow}
     * @param userId
     * @param userIdToUnfollow
     * @return ResponseEntity<SuccessfullyResponseDTO>
     */
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<SuccessfullyResponseDTO> Unfollow(@PathVariable(value="userId") Integer userId, @PathVariable(value="userIdToUnfollow") Integer userIdToUnfollow) {
        return new ResponseEntity<>(userService.unfollow(userIdToUnfollow,userId),HttpStatus.OK);
    }

}
