package bootcamp.wave9.SocialMeli.service;

import bootcamp.wave9.SocialMeli.dto.UserDTO;
import bootcamp.wave9.SocialMeli.entity.Post;
import bootcamp.wave9.SocialMeli.entity.User;
import bootcamp.wave9.SocialMeli.exception.PostNotFoundException;
import bootcamp.wave9.SocialMeli.exception.UserNotFoundException;
import bootcamp.wave9.SocialMeli.repository.PostRepository;
import bootcamp.wave9.SocialMeli.repository.UserRepository;
import bootcamp.wave9.SocialMeli.utils.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createPost(Post post) throws UserNotFoundException {

        User user = this.userRepository.findUserById(post.getUserId());

        if(user == null) throw new UserNotFoundException();

        post.setPostId(0);
        post.getDetail().setProductId(0);

        this.postRepository.save(post);
    }

    @Override
    public Map<String, Object> getFollowedPosts(int userId, String order) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        List<Post> followedPosts = processUserFollowedList(user);

        if(order.equals("date_asc")) {
            sortByDate(followedPosts, Order.ASC);
        } else if(order.equals("date_desc")) {
            sortByDate(followedPosts, Order.DESC);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order value.");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("posts", followedPosts);
        result.put("userId", user.getUserId());

        return result;
    }

    @Override
    public Map<String, Object> getCountPromo(int userId) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        List<Post> posts = this.postRepository.findAll();

        long countPromo = posts.stream().filter(p -> p.getUserId() == user.getUserId() && p.isHasPromo()).count();

        Map<String,Object> result = new HashMap<>();
        result.put("promoproducts_count", countPromo);
        result.put("userName", user.getUserName());
        result.put("userId", user.getUserId());

        return result;
    }

    @Override
    public Map<String, Object> getPromoList(int userId) throws UserNotFoundException {

        User user = this.userRepository.findUserById(userId);

        if(user == null) throw new UserNotFoundException();

        List<Post> posts = this.postRepository.findAll();

        List<Post> promoList = posts.stream().filter(p -> p.getUserId() == user.getUserId() && p.isHasPromo()).collect(Collectors.toList());

        Map<String,Object> result = new HashMap<>();
        result.put("posts", promoList);
        result.put("userName", user.getUserName());
        result.put("userId", user.getUserId());

        return result;

    }

    @Override
    public List<Post> getPostList() {
        return this.postRepository.findAll();
    }

    @Override
    public Post deletePostById(int postId) throws PostNotFoundException {

        Post post = this.postRepository.deletePostById(postId);

        if(post == null) throw new PostNotFoundException();

        return post;
    }

    @Override
    public Post getPostById(int postId) throws PostNotFoundException{

        Post post = this.postRepository.findPostById(postId);

        if(post == null) throw new PostNotFoundException();

        return post;
    }

    private List<Post> processUserFollowedList(User user) {

        List<UserDTO> followedUsers = user.getFollowedList();
        List<Post> posts = this.postRepository.findAll();
        List<Post> followedPosts = new ArrayList<>();

        LocalDate now = LocalDate.now();

        followedUsers.forEach(v ->
            followedPosts.addAll(posts.stream()
                    .filter(p -> p.getUserId() == v.getUserId()
                            && ChronoUnit.WEEKS.between(p.getDate(), now) <= 2)
                    .collect(Collectors.toList()))
        );

        return followedPosts;
    }

    private void sortByDate(List<Post> postList, Order order) {

        postList.sort((d1,d2) -> {
            int compare = d1.getDate().compareTo(d2.getDate());
            return order.equals(Order.ASC) ? compare : (-1)*compare;
        });
    }

}

