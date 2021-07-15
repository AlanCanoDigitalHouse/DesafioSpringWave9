package com.meli.socialmeli.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.Follower;
import com.meli.socialmeli.dto.PostDTO;
import com.meli.socialmeli.dto.Producto;
import com.meli.socialmeli.dto.UserDTO;
import com.meli.socialmeli.dto.request.PostRequestDTO;
import com.meli.socialmeli.exceptions.DataBaseException;
import com.meli.socialmeli.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.awt.image.LookupOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class ProductRespository implements ProductRepositoryInterface {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductRespository.class);

    /**
     * Metodo para realizar la busqueda de un usuario mediante su Id
     * @author Garduño Perez Josue Daniel
     * @param post_id {Integer} id of the user.
     * @return {UserDTO} user fount.
     * */
    @Override
    public PostDTO obtenerPublicacion(Integer post_id) throws DataBaseException {
        PostDTO postDTO = null;
        List<PostDTO> lista = loadDatabase();

        if (Objects.nonNull(lista)) {
            LOGGER.info("Buscando la publicacion con id: {}", post_id);
            Optional<PostDTO> item = lista.stream().filter(l -> l.getId_post().equals(post_id)).findFirst();
            if (item.isPresent()) {
                postDTO = item.get();
                return postDTO;
            }
        }else
            throw new DataBaseException();
        return postDTO;
    }

    /*public List<PostDTO> obtenerPublicacionID(Integer userId) throws DataBaseException {
        PostDTO postDTO = null;
        List<PostDTO> lista = loadDatabase();
        List<PostDTO> listab= new ArrayList<>();

        if (Objects.nonNull(lista)) {
            LOGGER.info("Buscando la publicacion con id de usuario: {}", userId);
            Optional<PostDTO> item = lista.stream().filter(l -> l.getUserId().equals(userId)).findFirst();
            if (item.isPresent()) {
                postDTO = item.get();
                listab.add(postDTO);
                return listab;
            }
        }else
            throw new DataBaseException();
        return listab;
    }*/

    public Producto obtenerProducto(Integer product_id) throws DataBaseException{
        Producto producto = null;
        List<Producto> lista = loadProductoDatabase();

        if (Objects.nonNull(lista)) {
            LOGGER.info("Buscando el producto con id: {}", product_id);
            Optional<Producto> item = lista.stream().filter(l -> l.getProduct_id().equals(product_id)).findFirst();
            if (item.isPresent()) {
                producto = item.get();
                return producto;
            }
        }else
            throw new DataBaseException();
        return producto;
    }

    public void anadirPublicacion(PostRequestDTO request){
        List<PostDTO> lista = loadDatabase();
        lista.add(new PostDTO(request.getUserId(), request.getId_post(),request.getDate(),
                request.getDetail(), request.getCategory(), request.getPrice()));
        LOGGER.info("La publicacion {} se registro correctamente.", request.getId_post());
        updateDatabasePublicacion(lista);
    }

    public void anadirProducto(Producto producto){
        List<Producto> lista = loadProductoDatabase();
        lista.add(producto);
        LOGGER.info("El producto {} se registro correctamente.", producto.getProduct_id());
        updateDatabaseProduct(lista);
    }

    private void updateDatabaseProduct(List<Producto> lista){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/productmeli.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        mapObjectProductWrite(file,lista);
    }
    private void updateDatabasePublicacion(List<PostDTO> lista){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/post.json");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        mapObjectPostWrite(file,lista);
    }

    private void mapObjectProductWrite(File file,List<Producto> lista){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info("Actualizando archivo");
            objectMapper.writeValue(file,lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void mapObjectPostWrite(File file,List<PostDTO> lista){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOGGER.info("Actualizando archivo");
            objectMapper.writeValue(file,lista);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<PostDTO> loadDatabase(){
            LOGGER.info("Cargando base de datos");
            File file = null;
            try{
                file = ResourceUtils.getFile("classpath:static/post.json");
            }catch (FileNotFoundException e){
                e.printStackTrace();
        }
      return mapObject(file);
    }

    private List<Producto> loadProductoDatabase(){
        LOGGER.info("Cargando base de datos");
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/productmeli.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return mapProductoObject(file);
    }

    private List<PostDTO> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostDTO>> typeReference = new TypeReference<>(){};
        List<PostDTO> postDto = null;
        try {
            postDto = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return postDto;
    }

    private List<Producto> mapProductoObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Producto>> typeReference = new TypeReference<>(){};
        List<Producto> postDto = null;
        try {
            postDto = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return postDto;
    }

    public List<PostDTO> searchUsersRecentPosts(UserDTO user) throws DataBaseException {
        Date actualDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_YEAR, Constant.PERIODO_PUBLICACION);
        Date lessDate = calendar.getTime();

        LOGGER.info("Buscado publicaciones del periodo del {} al {}.", lessDate, actualDate);

        Predicate<PostDTO> isPostOfFollowed = p -> user.getFollowed().contains(p.getUserId());
        Predicate<PostDTO> dateIsRecent = p -> p.getDate().after(lessDate) && p.getDate().before(actualDate);
        return PostsCollection.availablePosts.values().stream().filter(isPostOfFollowed.and(dateIsRecent))
                .collect(Collectors.toList());
    }
}
