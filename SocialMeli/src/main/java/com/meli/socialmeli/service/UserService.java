package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.dto.Producto;
import com.meli.socialmeli.dto.request.PostRequestDTO;
import com.meli.socialmeli.dto.response.FollowCountResponseDTO;
import com.meli.socialmeli.dto.response.FollowListResponseDTO;
import com.meli.socialmeli.dto.response.FollowedListResponseDTO;
import com.meli.socialmeli.dto.response.PostListResponse;
import com.meli.socialmeli.exceptions.*;
import com.meli.socialmeli.repository.ProductRespository;
import com.meli.socialmeli.repository.UserRespository;
import com.meli.socialmeli.dto.Follower;
import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.utils.Constant;
import com.meli.socialmeli.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private ProductRespository productRespository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRespository.class);

    /**
     * Metodo para que un usuario pueda seguir a otro
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id del usuario.
     * @param userIdFollow {Integer} id del usuario que seguira.
     * @return {UserDTO} user fount.
     * */
    @Override
    public ResponseEntity<?> followUser(Integer userId, Integer userIdFollow) throws UserNullException, DataBaseException {
        UserDTO newFollower = userRespository.obtenerUsuario(userId);
        UserDTO usuarioDTO = userRespository.obtenerUsuario(userIdFollow);

        if(newFollower.getUserId().equals(usuarioDTO.getUserId())){
            return new ResponseEntity<>(Constant.AUTOFOLLOW, HttpStatus.BAD_REQUEST);
        }
        else{
            Optional<Follower> item = usuarioDTO.getFollowers().stream().filter(l->l.getUserId().equals(userId)).findFirst();
            if (item.isPresent()){
                return new ResponseEntity<>(Constant.USUARIO_SEGUIDO.concat(userIdFollow.toString()), HttpStatus.BAD_REQUEST);
            }else{
                userRespository.modificarFollowersUsuario(newFollower,usuarioDTO);
                userRespository.modificarFollowedUsuario(newFollower,usuarioDTO);
                return new ResponseEntity<>(Constant.OPERACION_EXITOSA, HttpStatus.OK);
            }
        }
    }

    /**
     * Metodo para conocer el numero de seguidores
     * @author Garduño Perez Josue Daniel
     * @param userId {int} id del usuario.
     * @return {ResponseEntity<FollowResponseDTO>} lista de seguidores.
     **/
    @Override
    public ResponseEntity<FollowCountResponseDTO> countFollow(Integer userId) throws UserNullException, DataBaseException{
        UserDTO usuarioDTO = userRespository.obtenerUsuario(userId);
        return new ResponseEntity<>(new FollowCountResponseDTO(usuarioDTO.getUserId(),
                usuarioDTO.getUserName(),usuarioDTO.getFollowers().size()), HttpStatus.OK);
    }

    /**
     * Metodo para obtener la lista de seguidores
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     **/
    @Override
    public ResponseEntity<FollowListResponseDTO> obtainFollowList(Integer userId) throws UserNullException, DataBaseException{
        UserDTO usuarioDTO = userRespository.obtenerUsuario(userId);
        return new ResponseEntity<>(new FollowListResponseDTO(usuarioDTO.getUserId(),
                usuarioDTO.getUserName(),usuarioDTO.getFollowers()), HttpStatus.OK);
    }

    /**
     * Metodo para obtener la lista de a quienes siguen
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     **/
    @Override
    public ResponseEntity<FollowedListResponseDTO> obtainFollowedList(Integer userId) throws UserNullException, DataBaseException{
        UserDTO usuarioDTO = userRespository.obtenerUsuario(userId);
        return new ResponseEntity<>(new FollowedListResponseDTO(usuarioDTO.getUserId(),
                usuarioDTO.getUserName(),usuarioDTO.getFollowed()), HttpStatus.OK);
    }

    /**
     * Metodo para obtener crear nueva publicacion
     * @author Garduño Perez Josue Daniel
     * @param request {PostRequestDTO} id of the user.
     * @return {UserDTO} user fount.
     **/
    @Override
    public ResponseEntity<?> newPost(PostRequestDTO request) throws UserNullException, DataBaseException,
            RepeatedPostException, DateNotValidException {
        Date datePost = request.getDate();

        if(Objects.isNull(productRespository.obtenerPublicacion(request.getId_post()))){
            if(Objects.isNull(productRespository.obtenerProducto(request.getDetail().getProduct_id()))){
                productRespository.anadirProducto(crearProducto(request));
            }
            if (validateDate(datePost)) {
                productRespository.anadirPublicacion(request);
                userRespository.modificarPostUsuario(userRespository.obtenerUsuario(request.getUserId()),request.getId_post());
            }else
                throw new DateNotValidException(Constant.DATE_NOT_VALID, HttpStatus.BAD_REQUEST);
        }else
        throw new RepeatedPostException(Constant.POST_EXISTENTE, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(Constant.OPERACION_EXITOSA, HttpStatus.OK);
    }

    public Producto crearProducto(PostRequestDTO request){
        Producto b = new Producto();
        b.setProduct_id(request.getDetail().getProduct_id());
        b.setProductName(request.getDetail().getProductName());
        b.setType(request.getDetail().getType());
        b.setBrand(request.getDetail().getBrand());
        b.setColor(request.getDetail().getColor());
        b.setNotes(request.getDetail().getNotes());
        return b;
    }

    private boolean validateDate(Date postDate) {
        Date actualDate = new Date();
        return actualDate.before(postDate);
    }

    /**
     * Metodo para obtener la lista de publicaciones de los que siguen
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     **/
    @Override
    public ResponseEntity<PostListResponse> obtainPostList(Integer userId) throws UserNullException, DataBaseException{
        List<PostDTO> postsFollowed = productRespository.searchUsersRecentPosts(userRespository.obtenerUsuario(userId));
        postsFollowed = Utils.postsSorter(postsFollowed);
        return new ResponseEntity<>(new PostListResponse(userId, postsFollowed), HttpStatus.OK);
    }

    /**
     * Metodo para que un usuario pueda dejar seguir a otro
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id del usuario.
     * @param userIdToUnfollow {Integer} id del usuario que seguira.
     * @return {UserDTO} user fount.
     * */
    @Override
    public ResponseEntity<?> unFollowUser(Integer userId, Integer userIdToUnfollow) throws UserNullException, DataBaseException {
        UserDTO unFollower = userRespository.obtenerUsuario(userId);
        UserDTO userToUnfollow = userRespository.obtenerUsuario(userIdToUnfollow);

        Optional<Follower> item = userToUnfollow.getFollowers().stream().filter(l->l.getUserId().equals(userId)).findFirst();
        if (item.isPresent()){
            userRespository.eliminarFollowerUsuario(unFollower,userToUnfollow);
            userRespository.eliminarFollowedUsuario(unFollower,userToUnfollow);
            return new ResponseEntity<>(Constant.OPERACION_EXITOSA, HttpStatus.OK);
        }else
            return new ResponseEntity<>(Constant.USUARIO_NO_SEGUIDOR.concat(userIdToUnfollow.toString()), HttpStatus.BAD_REQUEST);
    }


}
