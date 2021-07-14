package challenge1springboot.socialmeli.repositories;

import challenge1springboot.socialmeli.entities.User;
import challenge1springboot.socialmeli.exceptions.user.InvalidUserException;
import challenge1springboot.socialmeli.globalconstants.Message;
import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.utils.JSONReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    public List<User> loadFromJSON() {
        File file = JSONReader.readJSONFile(Reference.PATH_RESOURCE_USER);
        return mapObject(file);
    }

    public void save(int userId, String userName) {
        List<User> users = loadFromJSON();
        users.add(new User(userId, userName, new ArrayList<>()));
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(ResourceUtils.getFile(Reference.PATH_RESOURCE_USER), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFollower(int followerId, int  userId) {
        List<User> users = loadFromJSON();
        int index = users.indexOf(findById(userId, users));
        if (!users.get(index).getFollowers().contains(followerId))
            try {
                users.get(index).getFollowers().add(followerId);
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(ResourceUtils.getFile(Reference.PATH_RESOURCE_USER), users);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public User findById(int id) {
        return loadFromJSON().stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public User findById(int id, List<User> users) {
        return users.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public List<User> listFollowedByUser(int userId) {
        return loadFromJSON()
                .stream()
                .filter(u -> u.getFollowers().contains(userId)).collect(Collectors.toList());
    }

    public void removeFollower(User user, Integer remove) {
        List<Integer> updated = user.getFollowers()
                .stream()
                .filter(id -> !id.equals(remove))
                .collect(Collectors.toList());
        List<User> users = loadFromJSON();
        int index = users.indexOf(findById(user.getUserId(), users));
        users.get(index).getFollowers().clear();
        users.get(index).setFollowers(updated);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(ResourceUtils.getFile(Reference.PATH_RESOURCE_USER), users);
        } catch (IOException e) {
            throw new InvalidUserException(Message.PROBLEM_ACCESSING_DATA_BASE);
        }
    }

    private List<User> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<>() {
        };
        List<User> users = null;

        try {
            users = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
