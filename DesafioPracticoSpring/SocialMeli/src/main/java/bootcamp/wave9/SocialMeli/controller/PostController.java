package bootcamp.wave9.SocialMeli.controller;

import bootcamp.wave9.SocialMeli.entity.Post;
import bootcamp.wave9.SocialMeli.exception.PostNotFoundException;
import bootcamp.wave9.SocialMeli.exception.UserNotFoundException;
import bootcamp.wave9.SocialMeli.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPostList() {
        return new ResponseEntity<>(this.postService.getPostList(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable int postId) throws PostNotFoundException {
        return new ResponseEntity<>(this.postService.getPostById(postId), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Post> deletePostById(@PathVariable int postId) throws PostNotFoundException {
        return new ResponseEntity<>(this.postService.deletePostById(postId), HttpStatus.OK);
    }

    @PostMapping("/newpost")
    public ResponseEntity<Void> createPost(@Valid @RequestBody Post post) throws UserNotFoundException {

        if(post.isHasPromo() || post.getDiscount() != 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid post input fields.");

        this.postService.createPost(post);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/followed/{userId}/list")
    public Map<String, Object> followedPosts(@NotNull @PathVariable int userId, @RequestParam(required = false, defaultValue = "date_desc") String order) throws UserNotFoundException {
        return this.postService.getFollowedPosts(userId, order);
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<Void> createPromoPost(@Valid @RequestBody Post post) throws UserNotFoundException {

        if(!post.isHasPromo() || post.getDiscount() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid promo post input fields.");

        this.postService.createPost(post);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}/countPromo/")
    public Map<String,Object> countPromo(@PathVariable int userId) throws UserNotFoundException {

        return this.postService.getCountPromo(userId);

    }

    @GetMapping("/{userId}/list/")
    public Map<String,Object> promoList(@PathVariable int userId) throws UserNotFoundException {

        return this.postService.getPromoList(userId);
    }
}
