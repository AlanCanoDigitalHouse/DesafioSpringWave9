package bootcamp.wave9.SocialMeli.repository;

import bootcamp.wave9.SocialMeli.entity.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepositoryImpl implements PostRepository {


    private Map<Integer, Post> postDB;
    private AtomicInteger atomicInteger;

    public PostRepositoryImpl() {

        this.postDB = new HashMap<>();
        this.loadDB();
        this.initCounter();

    }

    private void initCounter() {

        int start = 0;

        if(!this.postDB.isEmpty()) {
            start = this.postDB.keySet().stream().max(Integer::compareTo).get();
        }

        this.atomicInteger = new AtomicInteger(start);

    }

    private void loadDB() {

        File postJsonFile = null;
        List<Post> posts = new ArrayList<>();

        try{
            postJsonFile = ResourceUtils.getFile("classpath:static/posts.json");

            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            TypeReference<List<Post>> tr = new TypeReference<>() {};
            posts = om.readValue(postJsonFile, tr);

        } catch(Exception e) {
            e.printStackTrace();
        }

        if(!posts.isEmpty()) posts.forEach(post -> this.postDB.put(post.getPostId(), post));

    }


    @PreDestroy
    private void onDestroy() {

        try {
            File postJsonFile = ResourceUtils.getFile("classpath:static/posts.json");

            List<Post> posts = new ArrayList<>(this.postDB.values());

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            mapper.writeValue(postJsonFile, posts);

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    @Override
    public Post findPostById(int postId) {
        return this.postDB.get(postId);
    }

    @Override
    public Post save(Post post) {

        if(post.getPostId() == 0) {
            int id = this.atomicInteger.incrementAndGet();
            post.setPostId(id);
            post.getDetail().setProductId(id);
        }

        this.postDB.put(post.getPostId(), post);

        return post;

    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(this.postDB.values());
    }

    @Override
    public Post deletePostById(int postId) {
        return this.postDB.remove(postId);
    }
}
