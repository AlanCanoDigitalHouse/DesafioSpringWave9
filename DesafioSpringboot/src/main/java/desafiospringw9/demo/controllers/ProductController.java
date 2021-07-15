package desafiospringw9.demo.controllers;


import desafiospringw9.demo.dtos.FollowedDTO;
import desafiospringw9.demo.dtos.PostDTO;
import desafiospringw9.demo.exceptions.PostIdNotValidExceptions;
import desafiospringw9.demo.exceptions.ProductIdNotValidException;
import desafiospringw9.demo.exceptions.UserNotValidException;
import desafiospringw9.demo.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")

public class ProductController {

    @Autowired
    IProductService productService;

    //0005
    @PostMapping("/newpost")
    public ResponseEntity<String> createPost(@RequestBody PostDTO post)
        throws PostIdNotValidExceptions, ProductIdNotValidException, UserNotValidException{
        productService.createPost(post);
        return new ResponseEntity("New post successfully added", HttpStatus.OK);
    }

    //0006 y 0009
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedDTO> getFollowedPosts(@PathVariable int userId,
                                                        @RequestParam(defaultValue = "date_desc") String order,
                                                        @RequestParam(defaultValue = "14")int daysBefore)
    throws UserNotValidException{
        return new ResponseEntity<FollowedDTO>(productService.getFollowedPosts(userId, order, daysBefore), HttpStatus.OK);
    }


}
