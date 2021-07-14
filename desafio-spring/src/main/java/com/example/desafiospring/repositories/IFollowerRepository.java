package com.example.desafiospring.repositories;

import java.io.IOException;
import java.util.List;

public interface IFollowerRepository {

    /**
     * Consulta el numero de usuarios que sigen al usuario enviado
     * @param userId Id del usuario
     * @return Numero de usuarios que sigen al usuario enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    Long getNumFollowersById(Long userId) throws IOException;

    /**
     * Agrega el vendedor enviado a lista de seguidos del usuario enviado
     * @param userId Id del usuario
     * @param userIdToFollow Id del vendedor
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    void followUserById(Long userId, Long userIdToFollow) throws IOException;

    /**
     * Consulta lista de ids de los usuarios que siguen al vendedor enviado
     * @param userId Id del vendedor
     * @return Lista de Id de los usuarios que siguen al vendedor
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    List<Long> getListFollowersById(Long userId) throws IOException;

    /**
     * Consulta lista de ids de los vendedores que sige el usuario enviado
     * @param userId Id del usuario
     * @return Lista de Id de los vendedores que sigue el usuario
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    List<Long> getListFollowedById(Long userId) throws IOException;

    /**
     * Elimina el vendedor enviado de la lista de seguidos del usuarios
     * @param userId Id del usuario
     * @param userIdToUnfollow Id del vendedor
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    void unfollowUserById(Long userId, Long userIdToUnfollow) throws IOException;

    /**
     * Valida si el usuario enviado ya esta siguiendo al vendedor enviado
     * @param userId Id del usuario
     * @param userIdToFollow Id del vendedor
     * @return true si el usuario ya sigue al vendedor, false caso contrario
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    boolean isFollowedByUserId(Long userId, Long userIdToFollow) throws IOException;

}
