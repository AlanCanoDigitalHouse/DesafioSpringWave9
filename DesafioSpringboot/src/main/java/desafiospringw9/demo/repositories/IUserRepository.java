package desafiospringw9.demo.repositories;

import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.models.UserModel;

import java.util.List;

public interface IUserRepository {

    void addFollower(int follower, int following) throws UserNotValidException, RelationNotValidException;

    int removeFollower(int userId, int userIdToUnfollow);

    int countFollowers(int userId);

    UserModel getUserById (int userId) throws UserNotValidException;

    List<UserModel> getFollowers(int userId);

    List<UserModel> getFollowed(int userId);

    boolean validateUser(int userId) throws UserNotValidException;
}
