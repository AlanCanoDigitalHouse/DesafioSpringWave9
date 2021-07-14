package com.example.desafiospring.services;

import com.example.desafiospring.dtos.UserFollowersDto;
import com.example.desafiospring.exceptions.AlreadyFollowedException;
import com.example.desafiospring.exceptions.SameUserException;
import com.example.desafiospring.exceptions.UserNotExistException;
import com.example.desafiospring.exceptions.UserNotFollowedException;

import java.io.IOException;

public interface IFollowerService {

    /**
     * Permite al un usuario seguir a un vendedor
     * @param userId Id del usuario
     * @param userIdToFollow Id del vendedor
     * @throws AlreadyFollowedException Excepcion si el usuario ya esta siguiendo al vendedor
     * @throws UserNotExistException Excepcion si no existe un usuario para los Id enviados
     * @throws SameUserException Excepcion si el usuario es el mismo vendedor
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    void followUserById(Long userId, Long userIdToFollow)
            throws AlreadyFollowedException, UserNotExistException, SameUserException, IOException;

    /**
     * Permite que un usuario deje de seguir a un vendedor
     * @param userId Id del usuario
     * @param userIdToUnfollow Id del vendedor
     * @throws UserNotExistException Excepcion si no existe un usuario para los Id enviados
     * @throws SameUserException Excepcion si el usuario es el mismo vendedor
     * @throws UserNotFollowedException Excepcion en caso de que el usuario no siga al vendedor
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    void unfollowUserById(Long userId, Long userIdToUnfollow)
            throws UserNotExistException, SameUserException, UserNotFollowedException, IOException;

    /**
     * Permite consultar la cantidad de usuarios que siguen a un determinado vendedor
     * @param userId Id del vendedor
     * @return DTO con la informacion del vendedor enviado junto con la cantidad de usuarios que lo siguen
     * @throws UserNotExistException Excepcion si no existe un usuario para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserFollowersDto numFollowersByUserId(Long userId) throws UserNotExistException, IOException;

    /**
     * Consulta la lista de Usuarios que sigen al vendedor enviado
     * @param userId id del vendendor
     * @param order ordenamiento de la lista de usuario consultados (name_asc, name_desc)
     * @return DTO con la lista de usuarios que sigen al vendedor enviado
     * @throws UserNotExistException Excepcion si no existe un usuario para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserFollowersDto getUserFollowers(Long userId, String order) throws UserNotExistException, IOException;

    /**
     * Consulta la lista de vendedores que sige el usuario
     * @param userId id del usuario
     * @param order ordenamiento de la lista de vendedores consultados (name_asc, name_desc)
     * @return DTO con la lista de vendedores que sige el usuario enviado
     * @throws UserNotExistException Excepcion si no existe un usuario para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserFollowersDto getUserFollowed(Long userId, String order) throws UserNotExistException, IOException;

}
