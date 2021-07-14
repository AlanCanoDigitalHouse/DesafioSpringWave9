package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.PostCreateDto;
import com.example.desafiospring.entities.Post;

import java.io.IOException;
import java.util.List;

public interface IPostRepository {

    /**
     * Crea la publicacion enviada
     * @param post Entidad con la informacion de la publicacion
     * @return Entidad de la publicacion creada
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    Post createPost(Post post) throws IOException;

    /**
     * Consulta la lista de publicaciones realizada por los vendedores enviados
     * @param usersId Lista de Id de los vendedores con los cuales filtrar las publicaciones
     * @return Lista de entidades Post
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    List<Post> getPostsByUsersId(List<Long> usersId) throws IOException;

    /**
     * Consulta el numero de publicaciones realizadas por el vendedor enviado
     * @param userId Id del vendedor
     * @param hasPromo Booleano para filtrar las publicaciones con o sin promocion
     * @return Numero de publicaciones filtradas
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    Long countPostsByUserId(Long userId, boolean hasPromo) throws IOException;

    /**
     * Consulta la lista de publicaciones realizadas por el vendedor enviado
     * @param userId Id del vendedor
     * @param hasPromo Booleano para filtrar las publicaciones con o sin promocion
     * @return Lista de emtodades Post
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    List<Post> getPostsByUserId(Long userId, boolean hasPromo) throws IOException;

}
