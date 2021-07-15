package com.springChallenge.SpringChallenge.Controllers;

import com.springChallenge.SpringChallenge.Dtos.Post;
import com.springChallenge.SpringChallenge.Services.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final IProductService productService;

    public ProductsController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newpost")
    public void newPost(@RequestBody Post p){
        productService.addPost(p);
    }

    @GetMapping("/followed/{userId}/list")
    public List<Post> followed(@PathVariable int userId,  @RequestParam(value = "order", required=false) String order){
        return productService.followedPost(userId, order);
    }

}
