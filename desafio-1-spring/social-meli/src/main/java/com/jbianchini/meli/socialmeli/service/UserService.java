package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.UserRequestDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowedListDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowersCountDTO;
import com.jbianchini.meli.socialmeli.dto.response.FollowersListDTO;
import com.jbianchini.meli.socialmeli.dto.response.UserDTO;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFoundException;
import com.jbianchini.meli.socialmeli.model.User;
import com.jbianchini.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createAll(HttpServletResponse response) throws ApplicationException {
        User juan = this.create(new UserRequestDTO("Juan"));
        User mati = this.create(new UserRequestDTO("Mati"));
        User alvaro = this.create(new UserRequestDTO("Alvaro"));

        this.follow(juan.getUserId(), mati.getUserId(), response);
        this.follow(alvaro.getUserId(), mati.getUserId(), response);
        this.follow(alvaro.getUserId(), juan.getUserId(), response);

    }


    @Override
    public User create(UserRequestDTO userRequestDTO) {
        return this.userRepository.save(new User(userRequestDTO.getUserName()));
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow, HttpServletResponse response)
            throws ApplicationException {
        var user = this.userRepository.findByUserId(userId);
        var userToFollow = this.userRepository.findByUserId(userIdToFollow);

        if (user.isPresent() && userToFollow.isPresent()) {
            if (this.follows(user, userToFollow)) {
                return;
            }
            user.get().getFollowed().add(userToFollow.get());
            userToFollow.get().getFollowers().add(user.get());

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            throw new UserNotFoundException();
        }
    }

    private boolean follows(Optional<User> user, Optional<User> userToFollow) {
        return user.get().getFollowed().contains(userToFollow.get());
    }

    @Override
    public FollowersCountDTO getFollowersCount(int userId) throws UserNotFoundException {
        var user = this.userRepository.findByUserId(userId);

        if (user.isPresent()) {
            FollowersCountDTO response = new FollowersCountDTO();
            response.setUserId(user.get().getUserId());
            response.setUserName(user.get().getUserName());
            response.setFollowers_count(user.get().getFollowers().size());
            return response;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public FollowersListDTO getFollowers(int userID) throws UserNotFoundException {
        var user = this.userRepository.findByUserId(userID);
        var followers = new ArrayList<UserDTO>();

        if (user.isPresent()) {

            user.get().getFollowers().stream()
                    .forEach(u -> followers.add(new UserDTO(u.getUserId(), u.getUserName())));

            return new FollowersListDTO(user.get().getUserId(), user.get().getUserName(), followers);
        } else {
            throw new UserNotFoundException();
        }

    }

    @Override
    public FollowedListDTO getFollowed(int userID) throws UserNotFoundException {
        var user = this.userRepository.findByUserId(userID);
        var followed = new ArrayList<UserDTO>();

        if (user.isPresent()) {

            user.get().getFollowed().stream()
                    .forEach(u -> followed.add(new UserDTO(u.getUserId(), u.getUserName())));

            return new FollowedListDTO(user.get().getUserId(), user.get().getUserName(), followed);
        } else {
            throw new UserNotFoundException();
        }

    }

}
