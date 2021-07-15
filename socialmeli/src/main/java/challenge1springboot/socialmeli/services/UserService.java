package challenge1springboot.socialmeli.services;

import challenge1springboot.socialmeli.DTO.FollowDTO;
import challenge1springboot.socialmeli.DTO.response.UserCountFollowersResponseDTO;
import challenge1springboot.socialmeli.DTO.response.UserListFollowedResponseDTO;
import challenge1springboot.socialmeli.DTO.response.UserListFollowersResponseDTO;
import challenge1springboot.socialmeli.entities.User;
import challenge1springboot.socialmeli.exceptions.user.InvalidUserException;
import challenge1springboot.socialmeli.globalconstants.Message;
import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.repositories.UserRepository;
import challenge1springboot.socialmeli.utils.SorterUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public ResponseEntity<HttpStatus> newUserFollow(String userId, String userIdToFollow) {
        if (!Objects.isNull(userGetter(userId)) && !Objects.isNull(userGetter(userIdToFollow))) {
            userRepository.addFollower(Integer.parseInt(userId), Integer.parseInt(userIdToFollow));
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<UserCountFollowersResponseDTO> countUserFollowers(String userId) {
        User user = userGetter(userId);
        return !Objects.isNull(user) ?
                new ResponseEntity<>(new UserCountFollowersResponseDTO(user.getUserId(), user.getUserName(), user.countFollowers()), HttpStatus.OK) :
                new ResponseEntity<>(new UserCountFollowersResponseDTO(-1, "N/A", -1), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<UserListFollowersResponseDTO> listFollowersUser(String userId, String order) {
        User user = userGetter(userId);
        List<FollowDTO> followers = new ArrayList<>();
        if (!Objects.isNull(userGetter(userId))) {
            userRepository.loadFromJSON()
                    .stream()
                    .filter(user::isFollower)
                    .collect(Collectors.toList())
                    .forEach(u -> followers.add(new FollowDTO(u.getUserId(), u.getUserName())));
            sort(order, followers);
            return new ResponseEntity<>(new UserListFollowersResponseDTO(user.getUserId(), user.getUserName(), followers), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserListFollowersResponseDTO(-1, "N/A", followers), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<UserListFollowedResponseDTO> listFollowedByUser(String userId, String order) {
        User user = userGetter(userId);
        List<FollowDTO> followed = new ArrayList<>();
        if (!Objects.isNull(userGetter(userId))) {
            userRepository.loadFromJSON()
                    .stream()
                    .filter(u -> u.getFollowers().contains(user.getUserId()))
                    .collect(Collectors.toList())
                    .forEach(u -> followed.add(new FollowDTO(u.getUserId(), u.getUserName())));
            sort(order, followed);
            return new ResponseEntity<>(new UserListFollowedResponseDTO(user.getUserId(), user.getUserName(), followed), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserListFollowedResponseDTO(-1, "N/A", followed), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<HttpStatus> unfollowUser(String userId, String userIdToUnfollow) {
        User user = userGetter(userId);
        User followed = userGetter(userIdToUnfollow);
        if (Objects.isNull(user) || Objects.isNull(followed))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (!followed.getFollowers().contains(user.getUserId()))
            throw new InvalidUserException(userId + " " + Message.INVALID_UNFOLLOW_USER_ID + " " + userIdToUnfollow);

        userRepository.removeFollower(followed, user.getUserId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User userGetter(String userId) {
        User user = null;
        try {
            int id = Integer.parseInt(userId);
            user = userRepository.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return user;
    }

    private void sort(String order, List<FollowDTO> users) {
        if (order.equals(Reference.NAME_ASC))
            new SorterUtil<FollowDTO>().sort(users, Comparator.comparing(FollowDTO::getUserName), Reference.ORDER_ASC);
        else if (order.equals(Reference.NAME_DESC))
            new SorterUtil<FollowDTO>().sort(users, Comparator.comparing(FollowDTO::getUserName), Reference.ORDER_DESC);
    }
}
