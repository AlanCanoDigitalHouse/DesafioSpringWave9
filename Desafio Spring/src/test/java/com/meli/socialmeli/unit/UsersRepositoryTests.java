package com.meli.socialmeli.unit;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.meli.socialmeli.exceptions.FollowException;
import com.meli.socialmeli.exceptions.UserDoesNotExistException;
import com.meli.socialmeli.models.Follow;
import com.meli.socialmeli.models.User;
import com.meli.socialmeli.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Optional;

@DisplayName("User repository testing.")
public class UsersRepositoryTests {
    UsersRepository usersRepository = new UsersRepository();

    @Test
    @DisplayName("1) Find user by id = 1.")
    void foundUserById(){
        int id = 1;
        User expected = new User(1, "Julian");
        Optional<User> response = usersRepository.findUserById(id);
        Assertions.assertAll(
                () -> Assertions.assertTrue(response.isPresent()),
                () -> Assertions.assertEquals(expected.getUserId(), id)
        );
    }

    @Test
    @DisplayName("2) Not found user by id = -1.") //Database only has positive ids.
    void notFoundUserById(){
        int id = -1; //There are only 5 users
        Optional<User> response = usersRepository.findUserById(id);
        Assertions.assertFalse(response.isPresent());
    }

    @Test
    @DisplayName("3) User exists by id = 5.")
    void userExistent(){
        int id = 5;
        boolean response = usersRepository.userExist(id);
        Assertions.assertTrue(response);
    }

    @Test
    @DisplayName("4) User does not exist by id = 6.")//Database only has 5 users.
    void userNotExistent(){
        int id = 6;
        boolean response = usersRepository.userExist(id);
        Assertions.assertFalse(response);
    }

    @Test
    @DisplayName("5) Find follow relation.")
    void foundFollow(){
        Follow f = new Follow(1, 2);
        Optional<Follow> response = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertTrue(response.isPresent()),
                () -> Assertions.assertEquals(f.getToFollowUserId(), response.get().getToFollowUserId()),
                () -> Assertions.assertEquals(f.getFollowerUserId(), response.get().getFollowerUserId())
        );
    }

    @Test
    @DisplayName("6) Not found follow relation.")
    void notFoundFollow(){
        Follow f = new Follow(1, 1);
        Optional<Follow> response = usersRepository.findFollow(f);
        Assertions.assertFalse(response.isPresent());
    }

    @Test
    @DisplayName("7) New follow is added.")
    void followAdded() throws UserDoesNotExistException {
        Follow f = new Follow(1,1);
        Optional<Follow> preCheck = usersRepository.findFollow(f);
        usersRepository.addFollow(f);
        Optional<Follow> response = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertFalse(preCheck.isPresent()),
                () -> Assertions.assertTrue(response.isPresent())
        );
    }

    @Test
    @DisplayName("8) Add follow follower exception")
    void addFollowerException() throws UserDoesNotExistException{
        Follow f = new Follow(1,-1);
        Optional<Follow> preCheck = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertFalse(preCheck.isPresent()),
                () -> Assertions.assertThrows(UserDoesNotExistException.class, () -> usersRepository.addFollow(f))
        );
    }

    @Test
    @DisplayName("8) Add follow followed exception")
    void addFollowedException() throws UserDoesNotExistException{
        Follow f = new Follow(-1,1);
        Optional<Follow> preCheck = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertFalse(preCheck.isPresent()),
                () -> Assertions.assertThrows(UserDoesNotExistException.class, () -> usersRepository.addFollow(f))
        );
    }

    @Test
    @DisplayName("10) Existing follow deleted.")
    void followDeleted() throws UserDoesNotExistException, FollowException {
        Follow f = new Follow(1,2);
        Optional<Follow> preCheck = usersRepository.findFollow(f);
        usersRepository.deleteFollow(f);
        Optional<Follow> response = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertTrue(preCheck.isPresent()),
                () -> Assertions.assertFalse(response.isPresent())
        );
    }

    @Test
    @DisplayName("11) Delete follow follower exception")
    void deleteFollowerException() throws UserDoesNotExistException{
        Follow f = new Follow(1,-1);
        Optional<Follow> preCheck = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertFalse(preCheck.isPresent()),
                () -> Assertions.assertThrows(UserDoesNotExistException.class, () -> usersRepository.deleteFollow(f))
        );
    }

    @Test
    @DisplayName("12) Delete follow followed exception")
    void deleteFollowedException() throws UserDoesNotExistException{
        Follow f = new Follow(-1,1);
        Optional<Follow> preCheck = usersRepository.findFollow(f);
        Assertions.assertAll(
                () -> Assertions.assertFalse(preCheck.isPresent()),
                () -> Assertions.assertThrows(UserDoesNotExistException.class, () -> usersRepository.deleteFollow(f))
        );
    }

    @Test
    @DisplayName("13) Follow does not exists exception")
    void deleteFollowException() throws FollowException{
        Follow f = new Follow(1,1);
        Assertions.assertThrows(FollowException.class, ()-> usersRepository.deleteFollow(f));
    }
}
