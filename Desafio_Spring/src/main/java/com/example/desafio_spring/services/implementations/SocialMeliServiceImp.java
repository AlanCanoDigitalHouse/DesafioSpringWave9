package com.example.desafio_spring.services.implementations;

import com.example.desafio_spring.dtos.request.PostRequestDTO;
import com.example.desafio_spring.dtos.request.UserRequestDTO;
import com.example.desafio_spring.dtos.response.PostResponseByUserDTO;
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

    @Override
    public Map<Integer, User> getAllUsers() {
        return iSocialMeliRepository.getUsers();
    }

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
    public boolean validateFollowUser(Integer userId, Integer userIdToFollow) throws UserAlreadyFollowedException {
        if (iSocialMeliRepository.isFollowedBy(userId, userIdToFollow)) {
            throw new UserAlreadyFollowedException("El usuario " + userId
                    + " ya esta siguiendo al usuario " + userIdToFollow);
        }else{
            return true;
        }
    }

    @Override
    public User FollowersList(Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        if (Objects.nonNull(user)){
            return new User(user.getUserId(),user.getUserName(),user.getFollowers());
        }else{
            throw new UserNotExistException("El usuario asociado al id indicado no existe");
        }
    }

    @Override
    public User FollowersListSorted(String order, Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        User result = new User();
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
            }
            return result;
        }
        else throw new UserNotExistException("El usuario no existe");
    }

    @Override
    public User FollwedList(Integer userId) throws UserNotExistException {
        User user = iSocialMeliRepository.getUserById(userId);
        if (Objects.nonNull(user)){
            return new User(user.getUserId(),user.getUserName(),user.getFollowed(), true);
        }else{
            throw new UserNotExistException("El usuario asociado al id indicado no existe");
        }
    }

    @Override
    public User FollwedListSorted(String order, Integer userId) throws UserNotExistException, InvalidInputVariableException {
        User user = iSocialMeliRepository.getUserById(userId);
        User result;// = new User();// = new User();
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
            /*return order == "name_asc" ? new User(user.getUserId(),user.getUserName(),user.getFollowed(), true) :
                    new User(user.getUserId(),user.getUserName(),user.getFollowed(), true);*/
        }
        else throw new UserNotExistException("El usuario no existe");
    }

    @Override
    public Post savePost(PostRequestDTO postRequestDTO) throws ParseException {
        Post post = new Post();
        Integer exists = iSocialMeliRepository.getPostById(post.getId_post());
        //Product product = iSocialMeliRepository.getProductById();
        if (exists > 0) {
            post.setId_post(exists);
            post.setUserId(post.getUserId());
            post.setCategory(post.getCategory());
            post.setDate(post.getDate());
            post.setDetail(post.getDetail());
            post.setPrice(post.getPrice());
        }else{
            post.setId_post(iSocialMeliRepository.savePost(postRequestDTO));
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
    public Map<Integer, Post> getAllPosts() {
        return iSocialMeliRepository.getPosts();
    }

    @Override
    public PostResponseByUserDTO getPostByUserid(Integer userId) throws ParseException {
        ArrayList<Post> allPost = iSocialMeliRepository.getPostList();
        User user = iSocialMeliRepository.getUserById(userId);
        ArrayList<Post> postResponse;
        ArrayList<Integer> idFolloweds = new ArrayList<>();
        ArrayList<UserResponseDTO> followed = user.getFollowed();

        for (UserResponseDTO userFollowed: followed) {
            idFolloweds.add(userFollowed.getUserId());
        }
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(14);
        Date current = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());//cambiar Date to LocalDate
        Date start = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());//Cambiar Date to localDate

        postResponse = allPost.stream().filter(x -> x.getUserId().equals(userId) && x.getDate().after(start) && x.getDate().before(current)).collect(Collectors.toCollection(ArrayList::new));
        postResponse.sort(Comparator.comparing(Post::getDate).reversed());
        PostResponseByUserDTO postResponseByUserDTO = new PostResponseByUserDTO(userId,postResponse);
        return postResponseByUserDTO;
    }

    @Override
    public PostResponseByUserDTO getPostByUseridSorted(String order, Integer userId) throws PostNotExistException, InvalidInputVariableException {
        ArrayList<Post> allPost = iSocialMeliRepository.getPostList();
        if(Objects.nonNull(allPost)){
            if(order.equals("date_asc")){
                allPost.sort(Comparator.comparing(Post::getDate));
            }else if(order.equals("date_desc")){
                allPost.sort(Comparator.comparing(Post::getDate));
            }else {
                throw new InvalidInputVariableException("Debe ingresar date_asc o date desc");
            }
        }else {
            throw new PostNotExistException("El usuario no tiene posts");
        }
        PostResponseByUserDTO postResponseByUserDTO = new PostResponseByUserDTO(userId, allPost);
        return postResponseByUserDTO;
    }


}
