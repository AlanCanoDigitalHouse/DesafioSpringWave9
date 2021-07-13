package challenge1springboot.socialmeli.repositories;

import challenge1springboot.socialmeli.entities.User;
import challenge1springboot.socialmeli.util.JSONReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final String PATH_RESOURCE_USER = "socialmeli/src/main/JSONDB/user.json";

    public List<User> loadUsersFromJSON() {
        File file = JSONReader.readJSONFile(PATH_RESOURCE_USER);
        return mapObject(file);
    }

    public void saveUser(int userId, String userName) {
        List<User> users = loadUsersFromJSON();
        users.add(new User(userId, userName, new ArrayList<>()));
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(ResourceUtils.getFile(PATH_RESOURCE_USER), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFollower(int userId, int followerId) {
        List<User> users = loadUsersFromJSON();
        int index = users.indexOf(findById(userId, users));
        if (!users.get(index).getFollowers().contains(followerId))
            try {
                users.get(index).getFollowers().add(followerId);
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(ResourceUtils.getFile(PATH_RESOURCE_USER), users);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public User findById(int id) {
        return loadUsersFromJSON().stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
    }

    public User findById(int id, List<User> users) {
        return users.stream().filter(user -> user.getUserId() == id).findFirst().orElse(null);
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
