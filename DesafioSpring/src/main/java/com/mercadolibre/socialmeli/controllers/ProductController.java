package com.mercadolibre.socialmeli.controllers;

import com.mercadolibre.socialmeli.dtos.Product.request.PostDTO;
import com.mercadolibre.socialmeli.dtos.Product.response.UserPostsDTO;
import com.mercadolibre.socialmeli.dtos.UserResponseDTO;
import com.mercadolibre.socialmeli.exceptions.ExceptionOrder;
import com.mercadolibre.socialmeli.exceptions.ExceptionUserNotFound;
import com.mercadolibre.socialmeli.models.Post;
import com.mercadolibre.socialmeli.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * Extra: endpoint para listar posts
     *
     * @return List<Post>
     */
    @GetMapping("/")
    public List<Post> getAll() {
        return productService.getAll();
    }

    //DESARROLLADO Y FUNCIONANDO
    /**
     * US 0005: Creación de un nuevo post con un producto
     * @param post
     * @return UserResponseDTO
     * @throws ExceptionUserNotFound
     */
    @PostMapping("/newpost")
    public UserResponseDTO createPost(@Valid @RequestBody PostDTO post) throws ExceptionUserNotFound {
        return productService.createPost(post);
    }

    //DESARROLLADO Y FUNCIONANDO

    /**
     * US 0006: Listar publicaciones de las últimas 2 semanas de usuarios seguidos
     * US 0009: Ordenamiento por fecha.
     * @param userId
     * @param order
     * @return
     * @throws ExceptionUserNotFound
     * @throws ExceptionOrder
     */
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<UserPostsDTO> getFollowedPosts(@PathVariable Integer userId, @RequestParam(defaultValue = "date_desc") String order) throws ExceptionUserNotFound, ExceptionOrder {
        UserPostsDTO userPostsDTO = productService.getAllPosts(userId, order);
        return new ResponseEntity<>(userPostsDTO, HttpStatus.OK);
    }

}
