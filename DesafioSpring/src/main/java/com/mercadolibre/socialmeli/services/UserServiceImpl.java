package com.mercadolibre.socialmeli.services;

import com.mercadolibre.socialmeli.dtos.User.UserCountDTO;
import com.mercadolibre.socialmeli.dtos.User.UserDTO;
import com.mercadolibre.socialmeli.dtos.User.UserFollowedDTO;
import com.mercadolibre.socialmeli.dtos.User.UserFollowersDTO;
import com.mercadolibre.socialmeli.dtos.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.ExcepcionEqualsUserId;
import com.mercadolibre.socialmeli.exceptions.ExceptionFollower;
import com.mercadolibre.socialmeli.exceptions.ExceptionOrder;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.User;
import com.mercadolibre.socialmeli.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAllUser();
    }

    @Override
    public UserCountDTO countFollowers(Integer userId) throws ExceptionUserNotFound {
        User user = userRepository.findUserById(userId);
        try {
            UserCountDTO userCountDTO = new UserCountDTO(userId, user.getUserName(), user.countFollowers());
            return userCountDTO;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public UserFollowersDTO getFollowers(Integer userId, String order) throws ExceptionUserNotFound, ExceptionOrder {
        User user = userRepository.findUserById(userId);
        UserFollowersDTO userFollowersDTO = new UserFollowersDTO(user);
        orderUsers(order, userFollowersDTO.getFollowers());
        return userFollowersDTO;
    }

    @Override
    public UserFollowedDTO getFollowed(Integer userId, String order) throws ExceptionUserNotFound, ExceptionOrder {
        User user = userRepository.findUserById(userId);
        UserFollowedDTO userFollowedDTO = new UserFollowedDTO(user);
        orderUsers(order, userFollowedDTO.getFollowed());
        return userFollowedDTO;
    }

    @Override
    public UserResponseDTO unfollow(Integer userId, Integer userIdToUnfollow) throws ExceptionUserNotFound, ExceptionFollower {
        User follower = userRepository.findUserById(userId);
        User followed = userRepository.findUserById(userIdToUnfollow);
        UserResponseDTO userResponseDTO = null;
        try {
            Optional<UserDTO> item = followed.getFollowers().stream().filter(user -> user.getUserId().equals(follower.getUserId())).findFirst();
            //si no lo sigue, es vacío entonces larga excepcion
            if (item.isEmpty()) {
                throw new ExceptionFollower("El usuario con id: " + userId + " no sigue al usuario con id: " + userIdToUnfollow);
            } else {
                deleteFollowed(followed, follower);
                deleteFollower(followed, follower);
                userResponseDTO = new UserResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Dejaste de seguir al usuario", followed);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return userResponseDTO;
    }

    @Override
    public User findById(Integer userId) throws ExceptionUserNotFound {
        return userRepository.findUserById(userId);
    }

    private void deleteFollower(User followed, User follower) {
        UserDTO userDTO = new UserDTO(follower.getUserId(), follower.getUserName());
        followed.deleteFollower(userDTO);
        userRepository.save(followed);
    }

    private void deleteFollowed(User followed, User follower) {
        UserDTO userDTO = new UserDTO(followed.getUserId(), followed.getUserName());
        follower.deleteFollowed(userDTO);
        userRepository.save(follower);
    }

    @Override
    public UserResponseDTO follow(Integer userId, Integer userIdToFollow) throws ExceptionUserNotFound, ExcepcionEqualsUserId {
        User follower = userRepository.findUserById(userId);
        User followed = userRepository.findUserById(userIdToFollow);
        UserResponseDTO userResponseDTO = null;
        if (userId == userIdToFollow) {
            throw new ExcepcionEqualsUserId("No puedes auto-seguirte");
        }
        try {
            Optional<UserDTO> item = followed.getFollowers().stream().filter(user -> user.getUserId().equals(follower.getUserId())).findFirst();
            //si no lo sigue, es vacío entonces lo agrega
            if (item.isEmpty()) {
                addFollowed(followed, follower);
                addFollower(followed, follower);
                userResponseDTO = new UserResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Ahora sigues al usuario", followed);
            } else
                userResponseDTO = new UserResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Ya seguías al usuario", null);
        } catch (NullPointerException e) {
            return null;
        }
        return userResponseDTO;
    }

    private void addFollowed(User followed, User follower) {
        UserDTO userDTO = new UserDTO(followed.getUserId(), followed.getUserName());
        follower.addFollowed(userDTO);
        userRepository.save(follower);
    }

    //agrega un seguidor al vendedor
    private void addFollower(User followed, User follower) {
        UserDTO userDTO = new UserDTO(follower.getUserId(), follower.getUserName());
        followed.addFollower(userDTO);
        userRepository.save(followed);
    }

    private void orderUsers(String order, List<UserDTO> list) throws ExceptionOrder {
        if (order.equalsIgnoreCase("name_asc")) {
            list.sort(Comparator.comparing(UserDTO::getUserName));
        } else if (order.equalsIgnoreCase("name_desc")) {
            Collections.sort(list);
        } else
            throw new ExceptionOrder("Su parámetro de ordenamiento esta mal escrito. Puede ordenar por name_asc o name_desc");

    }


}
