package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.FollowDTO;
import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.dto.response.FollowedResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersCountResponseDTO;
import com.mercadolibre.socialmeli.dto.response.FollowersResponseDTO;
import com.mercadolibre.socialmeli.exception.EntityException;
import com.mercadolibre.socialmeli.exception.ServiceException;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void unFollow(Integer followerUserId, Integer followedUserId) throws ServiceException {
        Optional<FollowDTO> follow = userRepository.findFollow(followerUserId, followedUserId);
        if (follow.isPresent()) {
            userRepository.deleteFollow(follow.get());
        } else throw new ServiceException("Follow relation not found");
    }

    @Override
    public FollowersCountResponseDTO countFollowers(Integer followedUserId) {
        Optional<UserDTO> followedUser = userRepository.findUserByUserId(followedUserId);
        final FollowersCountResponseDTO followersCountResponseDTO = new FollowersCountResponseDTO();
        if (followedUser.isPresent()) {

            List<UserDTO> followers = userRepository.findUserFollowers(followedUserId);

            followersCountResponseDTO.setFollowers_count(followers.size());
            followersCountResponseDTO.setUserId(followedUser.get().getUserID());
            followersCountResponseDTO.setUserName(followedUser.get().getUserName());
        } else throw new ServiceException("No user found");
        return followersCountResponseDTO;
    }

    @Override
    public FollowersResponseDTO getFollowers(Integer followedUserId) {
        Optional<UserDTO> followedUser = userRepository.findUserByUserId(followedUserId);
        final FollowersResponseDTO followersCountResponseDTO = new FollowersResponseDTO();
        if (followedUser.isPresent()) {

            List<UserDTO> followers = userRepository.findUserFollowers(followedUserId);

            followersCountResponseDTO.setFollowers(followers);
            followersCountResponseDTO.setUserId(followedUser.get().getUserID());
            followersCountResponseDTO.setUserName(followedUser.get().getUserName());
        } else throw new ServiceException("No user found");
        return followersCountResponseDTO;
    }

    @Override
    public FollowersResponseDTO getFollowers(Integer followedUserId, String order) throws ServiceException {
        FollowersResponseDTO response = this.getFollowers(followedUserId);
        if (order.equals("name_asc"))
            response.getFollowers().sort(Comparator.comparing(UserDTO::getUserName));
        else if (order.equals("name_desc"))
            response.getFollowers().sort(
                    (user1, user2) ->
                            user2.getUserName().compareTo(user1.getUserName()));
        return response;
    }

    @Override
    public FollowedResponseDTO getFollowed(Integer followerUserId) {
        Optional<UserDTO> followerUser = userRepository.findUserByUserId(followerUserId);
        final FollowedResponseDTO followedResponseDTO = new FollowedResponseDTO();
        if (followerUser.isPresent()) {

            List<UserDTO> followed = userRepository.findUserFollowed(followerUserId);

            followedResponseDTO.setFollowed(followed);
            followedResponseDTO.setUserId(followerUser.get().getUserID());
            followedResponseDTO.setUserName(followerUser.get().getUserName());
        } else throw new ServiceException("No user found");
        return followedResponseDTO;
    }

    @Override
    public FollowedResponseDTO getFollowed(Integer followerUserId, String order) throws ServiceException {
        FollowedResponseDTO response = this.getFollowed(followerUserId);
        if (order.equals("name_asc"))
            response.getFollowed().sort(Comparator.comparing(UserDTO::getUserName));
        else if (order.equals("name_desc"))
            response.getFollowed().sort(
                    (user1, user2) ->
                            user2.getUserName().compareTo(user1.getUserName()));
        return response;
    }
}
