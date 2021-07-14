package challenge1springboot.socialmeli.controllers;

import challenge1springboot.socialmeli.DTO.request.NewPostRequestDTO;
import challenge1springboot.socialmeli.DTO.response.PostListResponseDTO;
import challenge1springboot.socialmeli.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<HttpStatus> newPost(@Valid @RequestBody NewPostRequestDTO newPostRequestDTO) {
        return postService.newPost(newPostRequestDTO);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostListResponseDTO> listPosts(@PathVariable String userId, @RequestParam(defaultValue = "date_desc") String order) {
        return new ResponseEntity<>(postService.listPosts(userId, order), HttpStatus.OK);
    }
}