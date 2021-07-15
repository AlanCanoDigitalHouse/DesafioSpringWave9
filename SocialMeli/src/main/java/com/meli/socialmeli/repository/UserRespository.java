package com.meli.socialmeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.Follower;
import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.exceptions.DataBaseException;
import com.meli.socialmeli.exceptions.UserNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRespository implements UserRepositoryInterface{

    private static final Logger LOGGER =  LoggerFactory.getLogger(UserRespository.class);

    /**
     * Metodo para realizar la busqueda de un usuario mediante su Id
     * @author Garduño Perez Josue Daniel
     * @param userId {Integer} id of the user.
     * @return {UserDTO} user fount.
     * */
    @Override
    public UserDTO obtenerUsuario(Integer userId) throws UserNullException, DataBaseException {
        UserDTO userDTO = null;
        List<UserDTO> lista = loadDatabase();

        if(Objects.nonNull(lista)){
            LOGGER.info("Buscando al usuario con id: {}", userId);
            Optional<UserDTO> item = lista.stream().filter(l -> l.getUserId().equals(userId)).findFirst();
            if (item.isPresent()){
                userDTO = item.get();
                return userDTO;
            }else
                throw new UserNullException(userId);
        }else
            throw new DataBaseException();
    }

    /**
     * Metodo para actualizar los followers que tiene el usuario
     * @author Garduño Perez Josue Daniel
     * @param newFollower {UserDTO} nuevo seguidor
     * @param usuarioDTO {UserDTO} nuevo usuario que sera seguido
     * */
    public void modificarFollowersUsuario(UserDTO newFollower,UserDTO usuarioDTO){
        List<UserDTO> lista = loadDatabase();
        List<Follower> listaFollowers = usuarioDTO.getFollowers();
        listaFollowers.add(new Follower(newFollower.getUserId(),newFollower.getUserName()));
        lista.get(lista.indexOf(usuarioDTO)).setFollowers(listaFollowers);

        updateDatabase(lista);
    }

    /**
     * Metodo para actualizar los followed que tiene el usuario
     * @author Garduño Perez Josue Daniel
     * @param newFollower {UserDTO} nuevo seguidor
     * @param usuarioDTO {UserDTO} nuevo usuario que sera seguido
     * */
    public void modificarFollowedUsuario(UserDTO newFollower,UserDTO usuarioDTO){
        List<UserDTO> lista = loadDatabase();
        List<Follower> listaFollowed = newFollower.getFollowed();
        listaFollowed.add(new Follower(usuarioDTO.getUserId(),usuarioDTO.getUserName()));
        lista.get(lista.indexOf(newFollower)).setFollowed(listaFollowed);

        updateDatabase(lista);
    }

    /**
     * Metodo para dejar de seguir a un usuario
     * @author Garduño Perez Josue Daniel
     * @param unFollower {UserDTO} usuario que dejara de ser seguidor
     * @param userToUnfollow {UserDTO} usuario que dejara de seguir
     * */
    public void eliminarFollowerUsuario(UserDTO unFollower,UserDTO userToUnfollow){
        List<UserDTO> lista = loadDatabase();
        List<Follower> listaFollowers = userToUnfollow.getFollowers();
        Optional<Follower> item = listaFollowers.stream().filter(l -> l.getUserId().equals(unFollower.getUserId())).findFirst();
        int index=0;
        if (item.isPresent()){
            index= listaFollowers.indexOf(item.get());
        }
        LOGGER.info(String.valueOf(index));
        listaFollowers.remove(index);
        lista.get(lista.indexOf(userToUnfollow)).setFollowers(listaFollowers);
        updateDatabase(lista);
    }

    /**
     * Metodo para eliminar al seguidor del usuario
     * @author Garduño Perez Josue Daniel
     * @param unFollower {UserDTO} usuario que dejara de ser seguidor
     * @param userToUnfollow {UserDTO} usuario que dejara de seguir
     * */
    public void eliminarFollowedUsuario(UserDTO unFollower,UserDTO userToUnfollow){
        List<UserDTO> lista = loadDatabase();
        List<Follower> listaFollowed = unFollower.getFollowed();
        Optional<Follower> item = listaFollowed.stream().filter(l -> l.getUserId().equals(userToUnfollow.getUserId())).findFirst();
        int index=0;
        if (item.isPresent()){
            index= listaFollowed.indexOf(item.get());
        }
        listaFollowed.remove(index);
        lista.get(lista.indexOf(unFollower)).setFollowed(listaFollowed);
        updateDatabase(lista);
    }

    /**
     * Metodo para añadir el identificador de la publicacion hecha por el usuario
     * @author Garduño Perez Josue Daniel
     * @param newPost {UserDTO} usuario que tendra la nueva publicacion
     * @param idPublicacion {idPulicacion} identificador de la publicacion a registrar
     * */
    public void modificarPostUsuario(UserDTO newPost, Integer idPublicacion){
        List<UserDTO> lista = loadDatabase();
        List<Integer> listaPost = newPost.getPosts();
        listaPost.add(idPublicacion);
        lista.get(lista.indexOf(newPost)).setPosts(listaPost);

        updateDatabase(lista);
    }

    private List<UserDTO> loadDatabase(){
        LOGGER.info("Cargando base de datos");
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/socialmeli.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return mapObject(file);
    }

    /**
     * Metodo para actualizar la base de datos
     * @author Garduño Perez Josue Daniel
     * @param lista {List<UserDTO>} id of the user.
     * */
    private void updateDatabase(List<UserDTO> lista){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/socialmeli.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        mapObjectWrite(file,lista);
    }

    private List<UserDTO> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDTO>> typeReference = new TypeReference<>(){};
        List<UserDTO> usersDto = null;
        try {
            usersDto = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return usersDto;
    }

    private void mapObjectWrite(File file,List<UserDTO> lista){
        LOGGER.info(String.valueOf(lista));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info("Actualizando archivo");
            objectMapper.writeValue(file,lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
