package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.response.FollowerCountResponseDTO;
import com.mercadolibre.social_meli.dto.response.UserResponseDTO;
import com.mercadolibre.social_meli.entity.User;
import com.mercadolibre.social_meli.exception.NoEffectRequest;
import com.mercadolibre.social_meli.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void followUser(Integer userId, Integer userIdToFollow) {
        var users = this.userRepository.getUsers();
        var user = this.userRepository.getUser(userId);
        var userToFollow = this.userRepository.getUser(userIdToFollow);

        if (this.isFollowing(user, userToFollow)) {
            throw new NoEffectRequest("User already follows the target");
        }

        user.getFollowed().add(new UserResponseDTO(userToFollow.getUserId(), userToFollow.getUserName()));
        userToFollow.getFollowers().add(new UserResponseDTO(user.getUserId(), user.getUserName()));
        this.userRepository.updateAllUsers(users);
    }

    @Override
    public void unfollowUser(Integer userId, Integer userIdToUnfollow) {
        var users = this.userRepository.getUsers();
        var user = this.userRepository.getUser(userId);
        var userToUnfollow = this.userRepository.getUser(userIdToUnfollow);

        if (this.isFollowing(user, userToUnfollow)) {
            user.getFollowed().remove(
                    user.getFollowed().stream().filter(u -> u.getUserId().equals(userIdToUnfollow))
                            .findFirst().orElse(null)
            );
            userToUnfollow.getFollowers().remove(
                    userToUnfollow.getFollowers().stream().filter(u -> u.getUserId().equals(userId))
                            .findFirst().orElse(null)
            );
            this.userRepository.updateAllUsers(users);
            return;
        }

        throw new NoEffectRequest("User does not follow the target");
    }

    private Boolean isFollowing(User user, User target) {
        return user.getFollowed().stream()
                .anyMatch(u -> u.getUserId().equals(target.getUserId()));
    }
}
