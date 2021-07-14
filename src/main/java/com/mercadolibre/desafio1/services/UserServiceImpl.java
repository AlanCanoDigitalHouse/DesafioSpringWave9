package com.mercadolibre.desafio1.services;

import com.mercadolibre.desafio1.dto.UserDTO;
import com.mercadolibre.desafio1.dto.response.PublicationResponseDTO;
import com.mercadolibre.desafio1.dto.response.UserFollowersCountDTO;
import com.mercadolibre.desafio1.dto.response.UserFollowListDTO;
import com.mercadolibre.desafio1.exceptions.UserFollowException;
import com.mercadolibre.desafio1.exceptions.UserNotExistException;
import com.mercadolibre.desafio1.repositories.interfaces.UserRepository;
import com.mercadolibre.desafio1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(@Qualifier("UserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void followUser(Integer userId, Integer userIdToFollow) throws UserFollowException,UserNotExistException {
        if(userId.equals(userIdToFollow)){
            throw new UserNotExistException("Los IDs no pueden ser iguales.");
        }

        UserDTO userBuyer = userRepository.getUserById(userId);
        if(Objects.isNull(userBuyer)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId+".");
        }

        UserDTO userSeller = userRepository.getUserById(userIdToFollow);
        if(Objects.isNull(userSeller)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId+".");
        }

        if(this.validFollow(userBuyer,userSeller,false)) {
            userSeller.getFollowers().add(userBuyer.getUserId());
            userBuyer.getFollows().add(userSeller.getUserId());
        }else{
            throw new UserFollowException("El usuario '"+userBuyer.getUserName()+"' ya sigue al comprador '"+userSeller.getUserName()+"'.");
        }
    }

    @Override
    public void unfollowUser(Integer userId, Integer userIdToFollow) throws UserFollowException,UserNotExistException {
        if(userId.equals(userIdToFollow)){
            throw new UserNotExistException("Los IDs no pueden ser iguales.");
        }

        UserDTO userBuyer = userRepository.getUserById(userId);
        if(Objects.isNull(userBuyer)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId+".");
        }

        UserDTO userSeller = userRepository.getUserById(userIdToFollow);
        if(Objects.isNull(userSeller)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId+".");
        }

        if(this.validFollow(userBuyer,userSeller,true)) {
            userSeller.getFollowers().remove(userBuyer.getUserId());
            userBuyer.getFollows().remove(userSeller.getUserId());
        }else{
            throw new UserFollowException("El usuario '"+userBuyer.getUserName()+"' actualmente no sigue al comprador '"+userSeller.getUserName()+"'.");
        }
    }

    @Override
    public UserFollowersCountDTO countFollowUser(Integer userId) throws UserNotExistException {
        UserDTO user = userRepository.getUserById(userId);

        if(Objects.isNull(user)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId);
        }

        return new UserFollowersCountDTO(userId,user.getUserName(),user.getFollowers().size());
    }

    @Override
    public UserFollowListDTO listFollowersUsers(Integer userId,String order) throws UserNotExistException {
        ArrayList<UserDTO> followersList = new ArrayList<>();
        UserDTO user = userRepository.getUserById(userId);

        if(Objects.isNull(user)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId);
        }

        user.getFollowers().forEach(followerId -> followersList.add(new UserDTO(userRepository.getUserById(followerId).getUserId(),
                                                                                userRepository.getUserById(followerId).getUserName(),
                                                                       null,null,null)));

        this.orderUsers(followersList,order);
        return new UserFollowListDTO(userId,user.getUserName(),followersList,null);
    }

    private void orderUsers(ArrayList<UserDTO> followersList, String order){
        if(order.equalsIgnoreCase("name_asc")){
            followersList.sort(Comparator.comparing(UserDTO::getUserName));
        }else if(order.equalsIgnoreCase("name_desc")){
            followersList.sort(Comparator.comparing(UserDTO::getUserName).reversed());
        }
    }

    @Override
    public UserFollowListDTO listFollowedUsers(Integer userId, String order) throws UserNotExistException {
        ArrayList<UserDTO> followedList = new ArrayList<>();
        UserDTO user = userRepository.getUserById(userId);

        if(Objects.isNull(user)){
            throw new UserNotExistException("No existe un usuario con el ID "+userId);
        }

        user.getFollows().forEach(followedId -> followedList.add(new UserDTO(userRepository.getUserById(followedId).getUserId(),
                                                                             userRepository.getUserById(followedId).getUserName(),
                                                                    null,null,null)));

        this.orderUsers(followedList,order);
        return new UserFollowListDTO(userId,user.getUserName(),null,followedList);
    }

    private Boolean validFollow(UserDTO userBuyer, UserDTO userSeller, Boolean isEqual){
        Boolean result = false;

        if(isEqual){
            if(userBuyer.getFollows().contains(userSeller.getUserId()) && userSeller.getFollowers().contains(userBuyer.getUserId())){
                result = true;
            }
        }else{
            if(!userBuyer.getFollows().contains(userSeller.getUserId()) && !userSeller.getFollowers().contains(userBuyer.getUserId())){
                result = true;
            }
        }

        return result;
    }
}
