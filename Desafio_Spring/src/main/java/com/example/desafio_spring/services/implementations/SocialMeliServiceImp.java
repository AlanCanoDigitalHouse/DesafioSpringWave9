package com.example.desafio_spring.services.implementations;

import com.example.desafio_spring.dtos.request.PostPromoRequestDTO;
import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.ProductRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.PostResponseByUserDTO;
import com.example.desafio_spring.dtos.response.PostResponsePromoByUserDTO;
import com.example.desafio_spring.dtos.response.UserResponseDTO;
import com.example.desafio_spring.entities.Post;
import com.example.desafio_spring.entities.User;
import com.example.desafio_spring.exceptions.*;
import com.example.desafio_spring.repositories.interfaces.ISocialMeliRepository;
import com.example.desafio_spring.services.interfaces.ISocialMeliService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SocialMeliServiceImp implements ISocialMeliService {
    ISocialMeliRepository iSocialMeliRepository;

    public SocialMeliServiceImp(ISocialMeliRepository iSocialMeliRepository) {
        this.iSocialMeliRepository = iSocialMeliRepository;
    }

    /**
     * Guarda un usuario en memoria del repositorio
     * @param userRequestDTO
     * @return User
     */
    @Override
    public User saveUser(UserRequestDTO userRequestDTO) {
        //TODO: validaciones para el usuario guardado
        User user = new User();
        Integer exists = iSocialMeliRepository.getUserByUser(user.getUserName());
        if (exists > 0) {
            user.setUserId(exists);
            user.setUserName(user.getUserName());
            user.setFollowed(user.getFollowed());
            user.setFollowers(user.getFollowers());
            user.setFollower_count(user.getFollower_count());
        }else{
            user.setUserId(iSocialMeliRepository.saveUser(userRequestDTO));
            user.setUserName(userRequestDTO.getUserName());
            user.setFollowed(new ArrayList<>());
            user.setFollowers(new ArrayList<>());
            user.setFollower_count(0);
        }
        return user;
    }

    /**
     * Retorna un map con todos los usuarios guardados en memoria del repositorio
     * @return
     */
    @Override
    public Map<Integer, User> getAllUsers() {
        return iSocialMeliRepository.getUsers();
    }

    /**
     * Metodo que sirve para seguir a un usuario determinado
     * @param id
     * @param idToFollow
     * @throws UserAlreadyFollowedException
     * @throws UserNotExistException
     * @throws FollowSameUserException
     */
    @Override
    public void Follow(Integer id, Integer idToFollow) throws UserAlreadyFollowedException, UserNotExistException, FollowSameUserException {
        User user = iSocialMeliRepository.getUserById(id);
        User userToFollow = iSocialMeliRepository.getUserById(idToFollow);
        if(Objects.nonNull(user) && Objects.nonNull(userToFollow)){
            if(id != idToFollow){
                if (validateFollowUser(id,idToFollow)){
                    user.getFollowed().add(new UserResponseDTO(userToFollow.getUserId(),userToFollow.getUserName()));
                    userToFollow.getFollowers().add(new UserResponseDTO(user.getUserId(),user.getUserName()));
                    iSocialMeliRepository.saveUserByUser(user);
                    iSocialMeliRepository.saveUserByUser(userToFollow);
                }
            }else{
                throw new FollowSameUserException("Está tratando de seguir al mismo usuario");
            }
        }else{
            throw new UserNotExistException("Está tratando de seguir un usuario que no existe");
        }
    }

    /**
     * Metodo para dejar de seguir a un usuario
     * @param id
     * @param idToUnFollow
     */
    @Override
    public void unFollow(Integer id, Integer idToUnFollow) {
        User user = iSocialMeliRepository.getUserById(id);
        User userToUnFollow = iSocialMeliRepository.getUserById(idToUnFollow);
        if (Objects.nonNull(user) && Objects.nonNull(userToUnFollow)){
            user.getFollowed().remove(new UserResponseDTO(userToUnFollow.getUserId(), userToUnFollow.getUserName()));
            userToUnFollow.getFollowers().remove(new UserResponseDTO(user.getUserId(),user.getUserName()));
            iSocialMeliRepository.saveUserByUser(user);
            iSocialMeliRepository.saveUserByUser(userToUnFollow);
        }
    }

    /**
     * Metodo para retornar el número de seguidores que tiene un usuario
     * @param userId
     * @return
     * @throws UserNotExistException
     */
    @Override
    public User FollwerCount(Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        if (Objects.nonNull(user)){
            user.setFollower_count(user.getFollowers().size());
            iSocialMeliRepository.saveUserByUser(user);
            return new User(user.getUserId(),user.getUserName(),user.getFollower_count());
        }else{
            throw new UserNotExistException("El usuario asociado al id indicado no existe");
        }
    }

    @Override
    public User PostPromoCount(Integer userId) {
        ArrayList<Post> posts = iSocialMeliRepository.getPostList();
        ArrayList<Post> filteredPost;
        User user = iSocialMeliRepository.getUserById(userId);
        filteredPost = posts.stream().filter(x -> x.getUserId().equals(userId) && x.isHasPromo()).collect(Collectors.toCollection(ArrayList::new));
        return new User(userId,user.getUserName(),filteredPost.size(),true);
    }

    /**
     * Metodo para validar si un usuario ya es seguido por otro
     * @param userId
     * @param userIdToFollow
     * @return
     * @throws UserAlreadyFollowedException
     */
    @Override
    public boolean validateFollowUser(Integer userId, Integer userIdToFollow) throws UserAlreadyFollowedException {
        if (iSocialMeliRepository.isFollowedBy(userId, userIdToFollow)) {
            throw new UserAlreadyFollowedException("El usuario " + userId + " ya sigue al usuario " + userIdToFollow);
        }else{
            return true;
        }
    }

    /**
     * Metodo para retornar la lista de seguidores de un usuario, en orden ascendente o descendente
     * Cumple con el US0003 y US008
     * @param order
     * @param userId
     * @return
     * @throws UserNotExistException
     */
    @Override
    public User FollowersListSorted(String order, Integer userId) throws UserNotExistException, InvalidInputVariableException {
        User user = iSocialMeliRepository.getUserById(userId);
        User result;
        if (Objects.nonNull(user)){
            if(order.equals("name_asc")){
                user.getFollowers().sort(Comparator.comparing(UserResponseDTO::getUserName));
                result = new User(user.getUserId(),user.getUserName(),user.getFollowers());
                return result;
            }
            if(order.equals("name_desc")){
                user.getFollowers().sort(Comparator.comparing(UserResponseDTO::getUserName).reversed());
                result = new User(user.getUserId(),user.getUserName(),user.getFollowers());
                return result;
            }else{
                throw new InvalidInputVariableException("El parametro de ordenamiento no es correcto");
            }
        }
        else throw new UserNotExistException("El usuario no existe");
    }

    /**
     * Metodo para retornar la lista de seguidos por un usuario, en orden ascendente o descendente
     * Cumple con el US004 y US008
     * @param order
     * @param userId
     * @return
     * @throws UserNotExistException
     * @throws InvalidInputVariableException
     */
    @Override
    public User FollwedListSorted(String order, Integer userId) throws UserNotExistException, InvalidInputVariableException {
        User user = iSocialMeliRepository.getUserById(userId);
        User result;
        if (Objects.nonNull(user)){
            if(order.equals("name_asc")){
                user.getFollowed().sort(Comparator.comparing(UserResponseDTO::getUserName).reversed());
                result = new User(user.getUserId(),user.getUserName(),user.getFollowed(), true);
                return result;

            }else if(order.equals("name_desc")){
                user.getFollowed().sort(Comparator.comparing(UserResponseDTO::getUserName));
                result = new User(user.getUserId(),user.getUserName(),user.getFollowed(), true);
                return result;
            }else{
                throw new InvalidInputVariableException("Debe ingresar name_asc o name_desc");
            }
        }
        else throw new UserNotExistException("El usuario no existe");
    }

    /**
     * Metodo para guardar un post en memoria
     * @param postRequestDTO
     * @return
     * @throws ParseException
     */
    @Override
    public Post savePost(PostRequestDTO postRequestDTO) throws ParseException {
        Post post = new Post();
        Integer exists = iSocialMeliRepository.getPostById(post.getId_post());
        if (exists > 0) {
            post.setId_post(exists);
            post.setUserId(post.getUserId());
            post.setCategory(post.getCategory());
            post.setDate(post.getDate());
            post.setDetail(post.getDetail());
            post.setPrice(post.getPrice());
        }else{
            post.setId_post(iSocialMeliRepository.savePost(postRequestDTO));//savePost
            iSocialMeliRepository.saveListPost(postRequestDTO);
            post.setUserId(postRequestDTO.getUserId());
            post.setDate(postRequestDTO.getDate());
            post.setCategory(postRequestDTO.getCategory());
            post.setDetail(new ArrayList<>());
            post.setPrice(postRequestDTO.getPrice());
        }
        return post;
    }

    @Override
    public Post savePostPromo(PostPromoRequestDTO postPromoRequestDTO) throws ParseException {
        Post post = new Post();
        Integer exists = iSocialMeliRepository.getPostById(post.getId_post());
        if (exists > 0) {
            post.setId_post(exists);
            post.setUserId(post.getUserId());
            post.setCategory(post.getCategory());
            post.setDate(post.getDate());
            post.setDetail(post.getDetail());
            post.setPrice(post.getPrice());
            post.setHasPromo(post.isHasPromo());
            post.setDiscount(post.getDiscount());
        }else{
            post.setId_post(iSocialMeliRepository.savePostPromo(postPromoRequestDTO));//savePost
            iSocialMeliRepository.saveListPostPromo(postPromoRequestDTO);
            post.setUserId(postPromoRequestDTO.getUserId());
            post.setDate(postPromoRequestDTO.getDate());
            post.setCategory(postPromoRequestDTO.getCategory());
            post.setDetail(new ArrayList<>());
            post.setPrice(postPromoRequestDTO.getPrice());
            post.setHasPromo(true);
            post.setDiscount(postPromoRequestDTO.getDiscount());
        }
        return post;
    }


    /**
     * Metodo para retornar todos los post guardados en memoria
     * @return
     */
    @Override
    public Map<Integer, Post> getAllPosts() {
        return iSocialMeliRepository.getPosts();
    }

    /**
     * Metodo para obtener las publicaciones de los usuarios seguidos por un usuario determinado
     * @param order
     * @param userId
     * @return
     * @throws PostNotExistException
     * @throws InvalidInputVariableException
     */
    @Override
    public PostResponseByUserDTO getPostByUseridSorted(String order, Integer userId) throws PostNotExistException, InvalidInputVariableException {
        ArrayList<Post> allPost = iSocialMeliRepository.getPostList();
        User user = iSocialMeliRepository.getUserById(userId);
        ArrayList<Post> postResponse;

        ArrayList<Integer> idFolloweds = new ArrayList<>();
        ArrayList<UserResponseDTO> followed = user.getFollowed();

        ArrayList<Post> responseArray = new ArrayList<>();

        for (UserResponseDTO userFollowed: followed) {
            idFolloweds.add(userFollowed.getUserId());
        }
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(14);
        Date current = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());//cambiar Date to LocalDate
        Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());//Cambiar Date to localDate

        if(Objects.nonNull(allPost)){
            for (Integer id:
                 idFolloweds) {
                //Capturar cada array y guardarlo en un array para retornar
                postResponse = allPost.stream().filter(x -> x.getUserId().equals(id) && x.getDate().after(start) && x.getDate().before(current)).collect(Collectors.toCollection(ArrayList::new));
                for (int i = 0; i < postResponse.size(); i++) {
                    responseArray.add(postResponse.get(i));
                }
            }

            if(order.equals("date_asc")){
                responseArray.sort(Comparator.comparing(Post::getDate).reversed());
                return new PostResponseByUserDTO(userId,responseArray);
            }else if(order.equals("date_desc")){
                responseArray.sort(Comparator.comparing(Post::getDate));
                return new PostResponseByUserDTO(userId,responseArray);
            }else {
                throw new InvalidInputVariableException("Debe ingresar date_asc o date desc");
            }
        }else {
            throw new PostNotExistException("El usuario no tiene posts");
        }
    }

    @Override
    public PostResponsePromoByUserDTO getPostByUserId(Integer userId) {
        ArrayList<Post> posts = iSocialMeliRepository.getPostList();
        ArrayList<Post> filteredPost;
        ArrayList<ProductRequestDTO> products;
        User user = iSocialMeliRepository.getUserById(userId);
        filteredPost = posts.stream().filter(x -> x.getUserId().equals(userId) && x.isHasPromo()).collect(Collectors.toCollection(ArrayList::new));
        return new PostResponsePromoByUserDTO(userId,user.getUserName(),filteredPost);
    }


}
