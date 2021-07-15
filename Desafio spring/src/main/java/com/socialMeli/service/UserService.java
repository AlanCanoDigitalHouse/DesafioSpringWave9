package com.socialMeli.service;

import com.socialMeli.dto.response.*;
import com.socialMeli.exception.exception.*;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("userService")
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * A user can follow other user
     */
    @Override
    public FollowResultResponseDTO follow(int userId, int userIdToFollow) throws ModelNotExists, FollowHimselfException, AlreadyFollowedException {
        UserModel userModel = userRepository.findById(userId);
        UserModel toFollow = userRepository.findById(userIdToFollow);
        if (userModel.getId() == toFollow.getId()) throw new FollowHimselfException(toFollow.getUserName());
        if (userModel.getFollowed().contains(toFollow.getId()))
            throw new AlreadyFollowedException(userModel.getUserName(), toFollow.getUserName());
        userModel.addNewUserFollowed(toFollow.getId());
        userRepository.modify(userModel);
        return new FollowResultResponseDTO(userModel.getUserName(), userModel.getId(), toFollow.getUserName(), toFollow.getId());
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public CountFollowersResponseDTO getCountOfFollowers(int idUser) throws ModelNotExists {
        //Used for esthetic of DTO
        UserModel userObjective = userRepository.findById(idUser);

        List<UserModel> users = userRepository.findAll();
        int followers = (int) users.stream().filter(user -> user.getFollowed().contains(idUser)).count();
        return new CountFollowersResponseDTO(userObjective.getId(), userObjective.getUserName(), followers);
    }

    @Override
    public UserFollowersResponseDTO getListFollowers(int idUser, String order) throws ModelNotExists, OrderNotValidException {
        UserModel userObjective = userRepository.findById(idUser);
        List<UserModel> users = userRepository.findAll();
        List<BasicUserResponseDTO> followers = users.stream()
                .filter(user -> user.getFollowed().contains(idUser))
                .map(follower -> new BasicUserResponseDTO(follower.getId(), follower.getUserName()))
                .collect(Collectors.toList());
        orderBy(followers, order);
        return new UserFollowersResponseDTO(idUser, userObjective.getUserName(), followers);
    }

    @Override
    public UserFollowedResponseDTO getListUsersFollowed(int idUser, String order) throws ModelNotExists, OrderNotValidException {
        UserModel userModel = userRepository.findById(idUser);
        List<BasicUserResponseDTO> followed = new ArrayList<>();
        for (Integer id : userModel.getFollowed()) {
            UserModel actual = userRepository.findById(id);
            followed.add(new BasicUserResponseDTO(actual.getId(), actual.getUserName()));
        }
        orderBy(followed, order);
        return new UserFollowedResponseDTO(userModel.getId(), userModel.getUserName(), followed);
    }

    private void orderBy(List<BasicUserResponseDTO> listToOrder, String order) throws OrderNotValidException {
        switch (order) {
            case "name_asc":
                listToOrder.sort((Comparator.comparing(BasicUserResponseDTO::getUserName)));
                break;
            case "name_desc":
                listToOrder.sort(((o1, o2) -> o2.getUserName().compareTo(o1.getUserName())));
                break;
            default:
                throw new OrderNotValidException(order);
        }
    }

    @Override
    public void unfollowUser(int userID, int userIdToUnfollow) throws ModelNotExists, UserDontFollowThisUser {
        UserModel user = userRepository.findById(userID);
        user.unFollowUser(userIdToUnfollow);
        userRepository.modify(user);
    }
}
