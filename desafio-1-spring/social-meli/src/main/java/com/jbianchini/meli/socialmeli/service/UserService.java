package com.jbianchini.meli.socialmeli.service;

import com.jbianchini.meli.socialmeli.exception.ApplicationException;
import com.jbianchini.meli.socialmeli.model.UserDTO;
import com.jbianchini.meli.socialmeli.repository.IUserRepository;
import com.jbianchini.meli.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO user) {
        return this.userRepository.save(user);
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) throws ApplicationException {
        var user = this.userRepository.findByUserId(userId);
        var userToFollow = this.userRepository.findByUserId(userIdToFollow);

        if(user.isPresent() && userToFollow.isPresent()){
            user.get().getFollowed().add(userToFollow.get());
            userToFollow.get().getFollowers().add(user.get());
            this.userRepository.save(user.get());
            this.userRepository.save(userToFollow.get());
        }else {
            throw new ApplicationException();
        }
    }
}
