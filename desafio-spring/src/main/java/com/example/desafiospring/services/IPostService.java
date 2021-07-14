package com.example.desafiospring.services;

import com.example.desafiospring.dtos.*;
import com.example.desafiospring.exceptions.DateInvalidException;
import com.example.desafiospring.exceptions.DiscountInvalidException;
import com.example.desafiospring.exceptions.PromoPostInvalidException;
import com.example.desafiospring.exceptions.UserNotExistException;

import java.io.IOException;
import java.util.List;

public interface IPostService {

    /**
     * Crea una publicacion con la informacion enviada
     * @param postCreateDto DTO con la informacion de la publicacion a crear
     * @return Retorna DTO con la publicacion creada con su id generado
     * @throws DateInvalidException Excepcion en caso de que la fecha enviada no sea valida
     * @throws UserNotExistException Excepcion si no existe un usuario para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    PostDto createPost(PostCreateDto postCreateDto) throws DateInvalidException, UserNotExistException, IOException;

    /**
     * Crea una publicacion con promocion con la informacion enviada
     * @param postPromoCreateDto DTO con la informacion de la publicacion con promocion a crear
     * @return Retorna DTO con la publicacion con promocion creada con su id generado
     * @throws DateInvalidException Excepcion en caso de que la fecha enviada no sea valida
     * @throws UserNotExistException Excepcion si no existe un usuario para el id enviado
     * @throws PromoPostInvalidException Excepcion en caso de que la publicacion sea una promocion y su valor de
     * descuento sea invalido
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    PostDto createPromoPost(PostPromoCreateDto postPromoCreateDto)
            throws DateInvalidException, UserNotExistException, PromoPostInvalidException, IOException, DiscountInvalidException;

    /**
     * Consulta las publicaciones de las ultimas dos semanas (14 dias) de los vendedores a los que esta siguiendo
     * el usuario
     * @param userId Id del usuario
     * @param order ordenamiento de la lista de publicaciones consultadas (date_asc, date_desc)
     * @return Retorna DTO con la lista de publicaciones consultadas
     * @throws UserNotExistException Excepcion si no existe un usuario para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    PostFollowedDto getRecentPosts(Long userId, String order) throws UserNotExistException, IOException;

    /**
     * Permite consultar la cantidad de publicaciones con promocion que tiene el vendedor enviado
     * @param userId Id del vendedor
     * @return Retorna DTO con los datos del vendedor enviado junto con el numero de publicaciones con promocion que tiene
     * @throws UserNotExistException Excepcion si no existe un vendedor para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserPostDto countPostsByUserId(Long userId) throws UserNotExistException, IOException;

    /**
     * Consulta la lista de publicaciones con promocion realizadas por el vendedor enviado
     * @param userId Id del vendedor
     * @param order ordenamiento de la lista de publicaciones consultadas (date_asc, date_desc)
     * @return Retorna DTO con lista de publicaciones con promocion hechas por el vendedor enviado
     * @throws UserNotExistException Excepcion si no existe un vendedor para el id enviado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserPostDto getPostsByUserId(Long userId, String order) throws UserNotExistException, IOException;

}
