package challenge1springboot.socialmeli.controllers;

import challenge1springboot.socialmeli.DTO.request.NewPostRequestDTO;
import challenge1springboot.socialmeli.DTO.request.NewSaleRequestDTO;
import challenge1springboot.socialmeli.DTO.response.PostListResponseDTO;
import challenge1springboot.socialmeli.DTO.response.SaleListResponseDTO;
import challenge1springboot.socialmeli.DTO.response.SaleQuantityResponseDTO;
import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.Sale;
import challenge1springboot.socialmeli.services.PostService;
import challenge1springboot.socialmeli.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class PostController {

    private final PostService postService;
    private final SaleService saleService;

    public PostController(PostService postService, SaleService saleService) {
        this.postService = postService;
        this.saleService = saleService;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> newPost(@Valid @RequestBody NewPostRequestDTO newPostRequestDTO) {
        return new ResponseEntity<>(postService.newPost(newPostRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostListResponseDTO> listPosts(@PathVariable String userId, @RequestParam(defaultValue = "date_desc") String order) {
        return new ResponseEntity<>(postService.listPosts(userId, order), HttpStatus.OK);
    }

    /* Beta Version - Testing Phase - More info commit history */

    // you are allowed to create a sale that is currently not enabled (isHasPromo == false),
    // but maybe is programed for future. eg: HotSale
    @PostMapping("/newpromopost")
    public ResponseEntity<Sale> newSale(@Valid @RequestBody NewSaleRequestDTO newSaleRequestDTO) {
        return new ResponseEntity<>(saleService.newPostSale(newSaleRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{userId}/countPromo/")
    public ResponseEntity<SaleQuantityResponseDTO> countSale(@PathVariable String userId) {
        return new ResponseEntity<>(saleService.countSale(userId), HttpStatus.OK);
    }

    // {userId}/list/?order=
    // -> name_asc : default
    // -> name_desc
    // -> discount_desc (best discount first)
    @GetMapping("/{userId}/list/")
    public ResponseEntity<SaleListResponseDTO> listSales(@PathVariable String userId, @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(saleService.listSales(userId, order), HttpStatus.OK);
    }
}