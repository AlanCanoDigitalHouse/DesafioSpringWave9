package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.dto.request.UserRequest;
import com.jbianchini.meli.socialmeli.dto.response.FollowersResponse;
import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.exception.UserNotFounException;
import com.jbianchini.meli.socialmeli.model.User;
import com.jbianchini.meli.socialmeli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createAll() throws ApplicationException {
        User juan = this.create(new UserRequest("Juan"));
        User mati = this.create(new UserRequest("Mati"));
        User alvaro = this.create(new UserRequest("Alvaro"));

        this.follow(juan.getUserId(), mati.getUserId());
        this.follow(alvaro.getUserId(), mati.getUserId());
        this.follow(alvaro.getUserId(), juan.getUserId());
    }

    @Override
    public User create(UserRequest userRequest) {

        return this.userRepository.save(new User(userRequest.getUserName()));
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) throws ApplicationException {
        var user = this.userRepository.findByUserId(userId);
        var userToFollow = this.userRepository.findByUserId(userIdToFollow);


        if (user.isPresent() && userToFollow.isPresent()) {
            if (this.follows(user, userToFollow)) {
                return;
            }
            user.get().getFollowed().add(userToFollow.get());
            userToFollow.get().getFollowers().add(user.get());
            this.userRepository.save(user.get());
            this.userRepository.save(userToFollow.get());
        } else {
            throw new UserNotFounException();
        }
    }

    private boolean follows(Optional<User> user, Optional<User> userToFollow) {
        return user.get().getFollowed().contains(userToFollow.get());
    }

    @Override
    public FollowersResponse getFollowersCount(int userId) throws UserNotFounException {
        var user = this.userRepository.findByUserId(userId);

        if (user.isPresent()) {
            FollowersResponse response = new FollowersResponse();
            response.setUserId(user.get().getUserId());
            response.setUserName(user.get().getUserName());
            response.setFollowers_count(user.get().getFollowers().size());
            return response;
        } else {
            throw new UserNotFounException();
        }
    }

}
