package com.mercadolibre.social_meli.service;

import com.mercadolibre.social_meli.dto.response.FollowedResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowerCountResponseDTO;
import com.mercadolibre.social_meli.dto.response.FollowersResponseDTO;
import com.mercadolibre.social_meli.dto.response.UserResponseDTO;
import com.mercadolibre.social_meli.entity.User;
import com.mercadolibre.social_meli.exception.InvalidQueryParamException;
import com.mercadolibre.social_meli.exception.NoEffectRequest;
import com.mercadolibre.social_meli.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO getUserById(Integer userId) {
        var user = this.userRepository.getUser(userId);
        return new UserResponseDTO(user.getUserId(), user.getUserName());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return this.userRepository.getUsers()
                .stream().map(u -> new UserResponseDTO(u.getUserId(), u.getUserName()))
                .collect(Collectors.toList());
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

    @Override
    public FollowerCountResponseDTO getFollowerCount(Integer userId) {
        var user = this.userRepository.getUser(userId);

        return new FollowerCountResponseDTO(user.getUserId(), user.getUserName(), user.getFollowers().size());
    }

    @Override
    public FollowersResponseDTO getFollowers(Integer userId, String order) {
        var user = this.userRepository.getUser(userId);
        var dto = new FollowersResponseDTO(user.getUserId(), user.getUserName(), user.getFollowers());

        if (Objects.nonNull(order)) {
            this.sortUserNames(dto.getFollowers(), order);
        }

        return dto;
    }

    @Override
    public FollowedResponseDTO getFollowed(Integer userId, String order) {
        var user = this.userRepository.getUser(userId);
        var dto = new FollowedResponseDTO(user.getUserId(), user.getUserName(), user.getFollowed());

        if (Objects.nonNull(order)) {
            this.sortUserNames(dto.getFollowed(), order);
        }

        return dto;
    }

    private Boolean isFollowing(User user, User target) {
        return user.getFollowed().stream()
                .anyMatch(u -> u.getUserId().equals(target.getUserId()));
    }

    private void sortUserNames(List<UserResponseDTO> users, String order) {
        switch (order) {
            case "name_asc":
                users.sort(Comparator.comparing(UserResponseDTO::getUserName));
                break;
            case "name_desc":
                users.sort(Comparator.comparing(UserResponseDTO::getUserName).reversed());
                break;
            default:
                throw new InvalidQueryParamException("Order param must be name_asc or name_desc");
        }
    }
}
