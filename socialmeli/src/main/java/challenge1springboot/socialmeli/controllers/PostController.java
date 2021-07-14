package challenge1springboot.socialmeli.controllers;

import challenge1springboot.socialmeli.DTO.request.PostRequestDTO;
import challenge1springboot.socialmeli.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<HttpStatus> newPost(@Valid @RequestBody PostRequestDTO postRequestDTO){
        return postService.newPost(postRequestDTO);
    }

    @PostMapping("/test")
    public ResponseEntity<Integer> testPost(){
        return new ResponseEntity<>(postService.testPost(), HttpStatus.OK);
    }
}
