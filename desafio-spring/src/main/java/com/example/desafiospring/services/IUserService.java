package com.example.desafiospring.services;

import com.example.desafiospring.dtos.UserDto;
import com.example.desafiospring.exceptions.UserNotExistException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface IUserService {

    /**
     * Consulta el usuario para el id y tipo enviados
     * @param userId Id del usuario a consultar
     * @param isSeller Tipo del usuario a consultar (Vendedor, Comprador)
     * @return DTO con la informacion del usuario consultado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserDto findByUserIdAndType(Long userId, boolean isSeller) throws IOException, UserNotExistException;

    /**
     * Consulta el usuario para el id enviado
     * @param userId Id del usuario a consultar
     * @return DTO con la informacion del usuario consultado
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    UserDto findByUserId(Long userId) throws IOException, UserNotExistException;

    /**
     * Consulta todos los usuarios del sistema
     * @return Lista de DTO con la informacion de los usuarios consultados
     * @throws IOException Excepcion en caso de problemas de lectura de la base de datos
     */
    List<UserDto> getAllUsers() throws IOException;


}
