package com.example.demo.Controllers;

import com.example.demo.DTOs.Request.PostRequestDTO;
import com.example.demo.DTOs.Response.SuccesResponse;
import com.example.demo.Entities.Post;
import com.example.demo.Entities.User;
import com.example.demo.Handler.PostSorter;
import com.example.demo.Handler.UserSorter;
import com.example.demo.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class PostController {
    private final UserService userService;

    public PostController (UserService userService){this.userService=userService;}

    ///parte 5 crear request
    @PostMapping("/newpost")
    public ResponseEntity<SuccesResponse> newPost (@RequestBody PostRequestDTO postRequestDTO) throws ParseException {
        if ( userService.newPost(postRequestDTO)){
            return new ResponseEntity<>(new SuccesResponse("Post creado!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new SuccesResponse("Post no creado"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<SuccesResponse> visiblesPosts (@PathVariable Integer userId, @RequestParam(required = false) String order){
        List<Post> result = userService.postList(userId);
        PostSorter sorter = new PostSorter();
        if (order!=null) {
            if (order.compareTo("date_asc") == 0 || order.compareTo("date_desc") == 0 ) {
                result= sorter.OrderByDate(result,order);
            }
        }
        return new ResponseEntity<>(new SuccesResponse(result.toString()), HttpStatus.OK);
    }
}
