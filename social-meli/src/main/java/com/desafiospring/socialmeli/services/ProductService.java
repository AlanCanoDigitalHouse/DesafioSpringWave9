package com.desafiospring.socialmeli.services;

import com.desafiospring.socialmeli.dtos.models.Buyer;
import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.dtos.models.Seller;
import com.desafiospring.socialmeli.dtos.requests.PostRequestDTO;
import com.desafiospring.socialmeli.dtos.models.User;
import com.desafiospring.socialmeli.dtos.responses.PostListDto;
import com.desafiospring.socialmeli.dtos.responses.PromoPostCountDTO;
import com.desafiospring.socialmeli.dtos.responses.PromoPostsDTO;
import com.desafiospring.socialmeli.exceptions.InvalidOrderRequestException;
import com.desafiospring.socialmeli.exceptions.UserException;
import com.desafiospring.socialmeli.handlers.ValidationHandler;
import com.desafiospring.socialmeli.repositories.BuyerRepository;
import com.desafiospring.socialmeli.repositories.ProductRepository;
import com.desafiospring.socialmeli.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService implements IProduct {

    private final String[] orderOptions = {"date_asc", "date_desc"};

    ProductRepository productRepository;
    UserService userService;

    public ProductService(UserService userService, ProductRepository productRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public Post addPost(PostRequestDTO postDTO) throws UserException {
        /** Validar vendedor */
        userService.getSeller(postDTO.getUserId());

        LocalDate date = ValidationHandler.validateDate(postDTO.getDate());
        Boolean hasPromo = postDTO.isHasPromo();
        Double discount = 0.0;
        if (hasPromo) {
            discount = postDTO.getDiscount();
        }
        Post post = new Post(postDTO.getUserId(), date, postDTO.getDetail(), postDTO.getCategory(),
                postDTO.getPrice(), hasPromo, discount);
        productRepository.add(post);
        return post;
    }

    @Override
    public PostListDto getFollowedSellersPost(int userId, String order) throws UserException {
        /** Validar usuario */
        Buyer buyer = userService.getBuyer(userId);

        /** Validar orden*/
        if(Arrays.stream(orderOptions).noneMatch(order::equals)){
            throw new InvalidOrderRequestException();
        }

        /** Obtener los posts de las ultimas 2 semanas */
        LocalDate twoWeeksBefore = LocalDate.now().minusWeeks(2);
        List<Post> lastPosts = productRepository.getAll().stream()
                .filter(post -> (post.getDate().compareTo(twoWeeksBefore)) > 0).collect(Collectors.toList());

        /** Filtrar los posts de los vendedores que sigue el usuario */
        List<User> followed = buyer.getFollowed();
        List<Post> followedPosts = lastPosts.stream()
                .filter(post -> followed.stream().map(User::getUserId).anyMatch(id ->  id.equals(post.getUserId())))
                .collect(Collectors.toList());

        /** Ordenar segun criterio */
        List<Post> sortedList = followedPosts.stream().sorted(postComparator(order)).collect(Collectors.toList());

        PostListDto postListDto = new PostListDto(userId, sortedList);
        return postListDto;
    }

    @Override
    public PromoPostCountDTO getCountPromoPosts(int userId) throws UserException {
        Seller seller = userService.getSeller(userId);
        List<Post> promoUserPosts = promoUserPosts(userId);
        return new PromoPostCountDTO(seller.getUserId(), seller.getUserName(), promoUserPosts.size());
    }

    @Override
    public PromoPostsDTO getPromoPosts(int userId, String order) throws UserException {
        Seller seller = userService.getSeller(userId);
        List<Post> promoUserPosts = promoUserPosts(userId);

        List<Post> sortedList = promoUserPosts.stream().sorted((o1, o2) -> productComparator(order, o1, o2)).collect(Collectors.toList());

        return new PromoPostsDTO(seller.getUserId(), seller.getUserName(), sortedList);
    }

    private List<Post> promoUserPosts(int sellerId) {
        return this.productRepository.getAll().stream()
                .filter(post -> post.getHasPromo() && post.getUserId() == sellerId).collect(Collectors.toList());
    }

    private int productComparator(String order, Post p1, Post p2){
        String name1 = p1.getDetail().getProductName();
        String name2 = p2.getDetail().getProductName();
        int result = 0;
        switch (order) {
            case "name_asc":
                result = name1.compareTo(name2);
                break;
            case "name_desc":
                result = name2.compareTo(name1);
                break;
        }
        return result;
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
