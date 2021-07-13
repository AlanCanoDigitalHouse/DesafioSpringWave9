package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.FollowDTO;
import com.mercadolibre.socialmeli.exception.EntityException;
import com.mercadolibre.socialmeli.exception.ServiceException;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addFollower(Integer followerUserId, Integer followedUserId) throws ServiceException {
        if (!followerUserId.equals(followedUserId)) {
            final FollowDTO followDTO = new FollowDTO();
            followDTO.setFollower(followerUserId);
            followDTO.setFollowed(followedUserId);

            try {
                userRepository.saveFollow(followDTO);
            } catch (EntityException entityException) {
                throw new ServiceException("Couldn't follow specified user");
            }
        } else throw new ServiceException("User can't follow itself");
    }
}
