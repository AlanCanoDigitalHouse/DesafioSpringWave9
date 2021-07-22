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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository = new PostRepository();
    private final UserRepository userRepository = new UserRepository();

    public Post newPost(NewPostRequestDTO newPostRequestDTO) {
        if (!DateValidator.validDate(newPostRequestDTO.getDate()))
            throw new InvalidPostException(Message.NO_VALID_DATE);
        if (Objects.isNull(userRepository.findById(newPostRequestDTO.getUserId())))
            throw new InvalidUserException(Message.USER_NOT_EXIST);
        Post post = postRepository.save(newPostRequestDTO);
        if (Objects.isNull(post))
            throw new InvalidPostException(Message.NEW_POST_NOT_REACHABLE);
        return post;
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
                .filter(post -> DateValidator.dateValidFormat(post.getDate()).isAfter(lastTwoWeek))
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

    // default : ORDER_DESC -> most new first
    private void sort(String order, List<Post> posts) {
        new SorterUtil<Post>().sort(posts, (p1, p2) -> DateValidator
                        .dateValidFormat(p1.getDate()).isAfter(DateValidator.dateValidFormat(p2.getDate())) ? 1 : DateValidator
                        .dateValidFormat(p1.getDate()).isBefore(DateValidator.dateValidFormat(p2.getDate())) ? -1 : 0,
                order.equals(Reference.DATE_ASC) ? Reference.ORDER_ASC : Reference.ORDER_DESC);
    }
}

