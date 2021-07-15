package bootcamp.wave9.SocialMeli.repository;

import bootcamp.wave9.SocialMeli.entity.User;

import java.util.List;

public interface UserRepository {

    User findUserById(int userId);
    User save(User user);
    List<User> findAll();
    User deleteUserById(int userId);

}
