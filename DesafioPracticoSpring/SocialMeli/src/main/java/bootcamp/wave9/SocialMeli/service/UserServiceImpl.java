package bootcamp.wave9.SocialMeli.service;

import bootcamp.wave9.SocialMeli.dto.UserDTO;
import bootcamp.wave9.SocialMeli.entity.User;
import bootcamp.wave9.SocialMeli.exception.UserNotFoundException;
import bootcamp.wave9.SocialMeli.repository.UserRepository;
import bootcamp.wave9.SocialMeli.utils.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(int userId, int userToFollowId) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);
        User userToFollow = this.userRepository.findUserById(userToFollowId);

        if(user == null || userToFollow == null) throw new UserNotFoundException();

        user.followUser(userToFollow);
        userToFollow.addFollower(user);

    }

    @Override
    public void unfollow(int userId, int userIdToUnfollow) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);
        User userToUnfollow = this.userRepository.findUserById(userIdToUnfollow);

        if(user == null || userToUnfollow == null) throw new UserNotFoundException();

        boolean unfollow = user.unfollowUser(userToUnfollow);

        if(!unfollow) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is not following the user with id = " + userIdToUnfollow);

        userToUnfollow.removeFollower(user);


    }

    @Override
    public Map<String, Object> getFollowersAmount(int userId) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        Map<String, Object> result = new HashMap<>();

        result.put("followers_count", user.getFollowerList().size());
        result.put("userName", user.getUserName());
        result.put("userId", user.getUserId());

        return result;

    }

    @Override
    public Map<String, Object> getFollowersList(int userId, String order) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        if(order.equals("name_asc")) {
            sortByName(user.getFollowerList(), Order.ASC);
        } else if(order.equals("name_desc")) {
            sortByName(user.getFollowerList(), Order.DESC);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order value.");
        }

        Map<String, Object> result = new HashMap<>();

        result.put("followers", user.getFollowerList());
        result.put("userName", user.getUserName());
        result.put("userId", user.getUserId());

        return result;

    }

    @Override
    public Map<String, Object> getFollowedList(int userId, String order) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        if(order.equals("name_asc")) {
            sortByName(user.getFollowedList(), Order.ASC);
        } else if(order.equals("name_desc")) {
            sortByName(user.getFollowedList(), Order.DESC);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order value.");
        }

        Map<String, Object> result = new HashMap<>();

        result.put("followed", user.getFollowedList());
        result.put("userName", user.getUserName());
        result.put("userId", user.getUserId());

        return result;

    }

    @Override
    public void createUser(UserDTO user) {

        User result = this.userRepository.save(new User(0, user.getUserName()));

        user.setUserId(result.getUserId());
    }

    @Override
    public List<User> getUserList() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) throws UserNotFoundException{

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        return user;
    }

    @Override
    public User deleteUserById(int userId) throws UserNotFoundException{

        User user = this.userRepository.deleteUserById(userId);

        if(user == null) throw new UserNotFoundException();

        return user;
    }

    private void sortByName(List<UserDTO> userList, Order order) {

        userList.sort((u1,u2) -> {
            int compare = u1.getUserName().compareTo(u2.getUserName());
            return order.equals(Order.ASC) ? compare : (-1)*compare;
        });
    }
}
