package desafiospringw9.demo.services;


import desafiospringw9.demo.dtos.FollowListDTO;
import desafiospringw9.demo.dtos.FollowersCountDTO;
import desafiospringw9.demo.exceptions.RelationNonExistentException;
import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.models.UserModel;
import desafiospringw9.demo.repositories.IUserRepository;
import desafiospringw9.demo.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public void addFollower(int follower, int following) throws UserNotValidException, RelationNotValidException {
        userRepository.addFollower(follower, following);
    }

    @Override
    public FollowersCountDTO countFollowers(int userId) throws UserNotValidException {

        String userName = userRepository.getUserById(userId).getUserName();
        int followers = userRepository.countFollowers(userId);


        return new FollowersCountDTO(userId, userName, followers);

    }

    @Override
    public FollowListDTO getFollowers(int userId, String order) throws UserNotValidException {

        String userName = userRepository.getUserById(userId).getUserName();
        List<UserModel> followers = userRepository.getFollowers(userId);


        FollowListDTO response = new FollowListDTO();
        response.setUserId(userId);
        response.setUserName(userName);
        response.setRelated(UserMapper.toDTO(followers));

        return response;
    }

    @Override
    public FollowListDTO getFollowed(int userId) throws UserNotValidException {

        String userName = userRepository.getUserById(userId).getUserName();
        List<UserModel> followed = userRepository.getFollowed(userId);

        FollowListDTO response = new FollowListDTO();
        response.setUserId(userId);
        response.setUserName(userName);
        response.setRelated(UserMapper.toDTO(followed));

        return response;
    }

    @Override
    public String removeFollower(int userId, int userIdToUnfollow) throws RelationNonExistentException {
        int relationId = userRepository.removeFollower(userId, userIdToUnfollow);
        if (relationId < 0) {
            throw new RelationNonExistentException(userId, userIdToUnfollow);
        }
        return "Relation #" + relationId + " removed";
    }


}
