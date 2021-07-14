package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.dtos.requests.PostRequestDTO;
import com.desafiospring.socialmeli.dtos.models.User;
import com.desafiospring.socialmeli.dtos.responses.PostListDto;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.handlers.ValidationHandler;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import com.desafiospring.socialmeli.repositories.ProductRepository;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService implements IProduct {

    private final String[] orderOptions = {"date_asc", "date_desc"};

    BuyerRepository buyerRepository;
    SellerRepository sellerRepository;
    ProductRepository productRepository;

    public ProductService(BuyerRepository buyerRepository, SellerRepository sellerRepository, ProductRepository productRepository) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Post addPost(PostRequestDTO postDTO) throws UserException {
        ValidationHandler.validateSeller(postDTO.getUserId(), sellerRepository);
        LocalDate date = ValidationHandler.validateDate(postDTO.getDate());
        Post post = new Post(postDTO.getUserId(), date, postDTO.getDetail(), postDTO.getCategory(), postDTO.getPrice());
        productRepository.add(post);
        return post;
    }

    @Override
    public PostListDto getFollowedSellersPost(int userId, String order) throws UserException {
        /** Validar usuario */
        Buyer buyer = ValidationHandler.validateUser(userId, buyerRepository);

        /** Obtener los posts de las ultimas 2 semanas */
        LocalDate twoWeeksBefore = LocalDate.now().minusWeeks(2);
        List<Post> lastPosts = productRepository.getAll().stream()
                .filter(post -> (post.getDate().compareTo(twoWeeksBefore)) > 0).collect(Collectors.toList());

        /** Filtrar los posts de los vendedores que sigue el usuario */
        List<User> followed = buyer.getFollowed();
        List<Post> followedPosts = lastPosts.stream()
                .filter(post -> followed.stream().map(User::getUserId).anyMatch(id -> {
                    System.out.println(id);
                    System.out.println(post.getUserId());
                    return id.equals(post.getUserId());
                }))
                .collect(Collectors.toList());

        /** Ordenar segun criterio */
        List<Post> sortedList = followedPosts.stream().sorted(postComparator(order)).collect(Collectors.toList());

        PostListDto postListDto = new PostListDto(userId, sortedList);
        return postListDto;
    }


    protected Comparator<Post> postComparator(String order) {
        Comparator<Post> c = null;
        switch (order) {
            case "date_asc":
                 c = Post::compareTo;
                break;
            case "date_desc":
                 c = Comparator.reverseOrder();
                break;
        }
        return c;
    }
}
