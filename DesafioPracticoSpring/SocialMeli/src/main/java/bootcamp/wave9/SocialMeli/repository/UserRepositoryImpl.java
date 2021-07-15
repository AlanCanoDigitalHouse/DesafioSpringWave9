package bootcamp.wave9.SocialMeli.repository;


import bootcamp.wave9.SocialMeli.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private Map<Integer, User> userDB;
    private AtomicInteger atomicInteger;

    public UserRepositoryImpl() {

        this.userDB = new HashMap<>();
        this.loadDB();
        this.initCounter();

    }

    private void initCounter() {

        int start = 0;

        if(!this.userDB.isEmpty()) {
            start = this.userDB.keySet().stream().max(Integer::compareTo).get();
        }

        this.atomicInteger = new AtomicInteger(start);

    }

    private void loadDB() {

        File userJsonFile = null;
        List<User> users = new ArrayList<>();

        try{
            userJsonFile = ResourceUtils.getFile("classpath:static/users.json");

            ObjectMapper om = new ObjectMapper();
            TypeReference<List<User>> tr = new TypeReference<List<User>>() {};
            users = om.readValue(userJsonFile, tr);

        } catch(Exception e) {
            e.printStackTrace();
        }

        if(!users.isEmpty())users.forEach(user -> this.userDB.put(user.getUserId(), user));
    }

    @Override
    public User findUserById(int userId) {

        return userDB.get(userId);

    }

    @PreDestroy
    private void onDestroy() {

        try {
            File userJsonFile = ResourceUtils.getFile("classpath:static/users.json");

            List<User> users = new ArrayList<>(this.userDB.values());

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(userJsonFile, users);



        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    @Override
    public User save(User user) {

        if(user.getUserId() == 0) {
            user.setUserId(this.atomicInteger.incrementAndGet());
        }

        this.userDB.put(user.getUserId(), user);

        return user;

    }

    @Override
    public List<User> findAll() {

        return new ArrayList<>(this.userDB.values());

    }

    @Override
    public User deleteUserById(int userId) {

        return this.userDB.remove(userId);

    }

}
