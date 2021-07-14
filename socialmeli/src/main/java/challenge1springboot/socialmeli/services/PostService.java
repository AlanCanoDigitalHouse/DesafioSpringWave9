package challenge1springboot.socialmeli.services;

import challenge1springboot.socialmeli.DTO.request.NewPostRequestDTO;
import challenge1springboot.socialmeli.DTO.response.PostListResponseDTO;
import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.User;
import challenge1springboot.socialmeli.exceptions.post.InvalidPostException;
import challenge1springboot.socialmeli.exceptions.user.InvalidUserException;
import challenge1springboot.socialmeli.globalconstants.Reference;
import challenge1springboot.socialmeli.repositories.PostRepository;
import challenge1springboot.socialmeli.repositories.UserRepository;
import challenge1springboot.socialmeli.utils.DateValidator;
import challenge1springboot.socialmeli.globalconstants.Message;
import challenge1springboot.socialmeli.utils.SorterUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService {

    PostRepository postRepository = new PostRepository();
    UserRepository userRepository = new UserRepository();

    public ResponseEntity<HttpStatus> newPost(NewPostRequestDTO newPostRequestDTO) {
        if (!DateValidator.validDate(newPostRequestDTO.getDate()))
            throw new InvalidPostException(Message.NO_VALID_DATE);
        if (Objects.isNull(userRepository.findById(newPostRequestDTO.getUserId())))
            throw new InvalidUserException(Message.USER_NOT_EXIST);
        if (Objects.isNull(postRepository.save(newPostRequestDTO)))
            throw new InvalidPostException(Message.NEW_POST_NOT_REACHABLE);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public PostListResponseDTO listPosts(String userId, String order) {
        User user = userGetter(userId);
        if (Objects.isNull(user))
            throw new InvalidUserException(Message.USER_NOT_EXIST);
        List<User> users = userRepository.listFollowedByUser(user.getUserId());
        List<Integer> ids = new ArrayList<>();
        users.forEach(u -> ids.add(u.getUserId()));
        LocalDate lastTwoWeek = LocalDate.now().minusDays(14);
        List<Post> posts = postRepository.listPostPublishedByUser(ids)
                .stream()
                .filter(post -> post.getDateValue().isAfter(lastTwoWeek))
                .collect(Collectors.toList());
        sort(order, posts);
        return new PostListResponseDTO(user, posts);
    }

    private User userGetter(String userId) {
        User user = null;
        try {
            int id = Integer.parseInt(userId);
            user = userRepository.findById(id);
        } catch (NumberFormatException ignored) {
        }
        return user;
    }

    private void sort(String order, List<Post> posts) {
        if (order.equals(Reference.DATE_ASC))
            new SorterUtil<Post>().sort(posts, Comparator.comparing(Post::getDateValue), Reference.ORDER_ASC);
        else if (order.equals(Reference.DATE_DESC))
            new SorterUtil<Post>().sort(posts, Comparator.comparing(Post::getDateValue), Reference.ORDER_DESC);
    }
}

