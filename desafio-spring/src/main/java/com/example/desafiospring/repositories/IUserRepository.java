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

    User findByIdAndType(Long userId, boolean isSeller) throws IOException;

    User findById(Long userId) throws IOException;

}
