package com.example.desafiospring.repositories;

import com.example.desafiospring.entities.User;

import java.io.IOException;
import java.util.List;

public interface IUserRepository {

    /**
     * Consulta todos los usuarios del sistema
     * @return Lista de entidades User
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    List<User> getAllUsers() throws IOException;

    /**
     * Consulta un usuario por su id y tipo
     * @param userId Id del usuario
     * @param isSeller Tipo del usuario (true - Vendedor, false - Comprador)
     * @return Una entidad de tipo User
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    User findByIdAndType(Long userId, boolean isSeller) throws IOException;

    /**
     * Consulta un usuario por su id
     * @param userId Id del usuario
     * @return Una entidad de tipo User
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    User findById(Long userId) throws IOException;

}
