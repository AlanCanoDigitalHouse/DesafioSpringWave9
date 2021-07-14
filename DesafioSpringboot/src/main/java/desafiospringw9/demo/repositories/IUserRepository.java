package desafiospringw9.demo.repositories;

import desafiospringw9.demo.exceptions.RelationNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.models.UserModel;

import java.util.List;

public interface IUserRepository {

    void addFollower(int follower, int following) throws UserNotValidException, RelationNotValidException;

    int countFollowers(int userId);
    UserModel getUserById (int userId) throws UserNotValidException;

    List<UserModel> getFollowers(int userId);
}
