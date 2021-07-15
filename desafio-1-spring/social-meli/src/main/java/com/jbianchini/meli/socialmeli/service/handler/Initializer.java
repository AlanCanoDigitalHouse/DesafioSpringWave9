package com.jbianchini.meli.socialmeli.service.handler;

import com.jbianchini.meli.socialmeli.dto.PostDTO;
import com.jbianchini.meli.socialmeli.dto.ProductDTO;
import com.jbianchini.meli.socialmeli.dto.UserDTO;
import com.jbianchini.meli.socialmeli.service.IPostService;
import com.jbianchini.meli.socialmeli.service.IUserService;
import org.springframework.stereotype.Component;

/**
 * Application initializer to create some sample data.
 */
@Component
public class Initializer {

    IUserService userService;
    IPostService postService;

    public Initializer(IUserService userService, IPostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public void createAll() {

        UserDTO juan = (UserDTO) this.userService.createUser(new UserDTO("Juan")).getDto();
        UserDTO andres = (UserDTO) this.userService.createUser(new UserDTO("Andres")).getDto();
        UserDTO aldana = (UserDTO) this.userService.createUser(new UserDTO("Aldana")).getDto();
        UserDTO marialuz = (UserDTO) this.userService.createUser(new UserDTO("Maria Luz")).getDto();

        this.userService.follow(juan.getUserId(), andres.getUserId());
        this.userService.follow(juan.getUserId(), aldana.getUserId());
        this.userService.follow(juan.getUserId(), marialuz.getUserId());
        this.userService.follow(andres.getUserId(), marialuz.getUserId());
        this.userService.follow(marialuz.getUserId(), aldana.getUserId());

        ProductDTO product1 = new ProductDTO("Silla Gamer", "Gamer", "Racer", "Red & Black",
                "Silla gamer roja y negra " + "marca racer.");
        ProductDTO product2 = new ProductDTO("Monitor", "Office", "LG", "Black", "Monitor de oficina LG 22 pulgadas.");
        ProductDTO product3 =
                new ProductDTO("Teclado", "Office", "Logitech", "Black", "Teclado QWERTY de oficina Logitech.");

        PostDTO post1 = new PostDTO(aldana.getUserId(), "10-07-2021", product1, 100, 15000.00);
        PostDTO post2 = new PostDTO(andres.getUserId(), "11-07-2021", product2, 200, 9570.50);
        PostDTO post3 = new PostDTO(marialuz.getUserId(), "02-07-2021", product3, 200, 1500.00);

        this.postService.newPost(post1);
        this.postService.newPost(post2);
        this.postService.newPost(post3);
    }

}
