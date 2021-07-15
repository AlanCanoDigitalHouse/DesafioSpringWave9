package com.mercadolibre.socialmeli.services.implementations;

import com.mercadolibre.socialmeli.dtos.posts.requests.PromoPostRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.*;
import com.mercadolibre.socialmeli.dtos.posts.requests.PostRequestDTO;
import com.mercadolibre.socialmeli.dtos.users.UserDTO;
import com.mercadolibre.socialmeli.exceptions.users.UserNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.IPostRepository;
import com.mercadolibre.socialmeli.repositories.interfaces.IProductRepository;
import com.mercadolibre.socialmeli.services.interfaces.IPostService;
import com.mercadolibre.socialmeli.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("productServ")
public class PostServiceImpl implements IPostService {

    private static final int QTY_DAYS = 14;
    private final IPostRepository iPostRepository;
    private final IProductRepository iProductRepository;
    private final IUserService iUserService;

    public PostServiceImpl(@Qualifier("postsRepo") IPostRepository iPostRepository,
                           @Qualifier("prodsRepo") IProductRepository iProductRepository,
                           @Qualifier("userServ") IUserService iUserService) {
        this.iPostRepository = iPostRepository;
        this.iProductRepository = iProductRepository;
        this.iUserService = iUserService;
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO post) throws UserNotFoundException {
        UserDTO user =  this.iUserService.findUserData(post.getUserId());

        ProductResponseDTO product = this.iProductRepository.addNew(post.getDetail());
        return this.iPostRepository.addNew(post, product);
    }

    @Override
    public PostListResponseDTO postsListOf(Integer userId, String order) throws UserNotFoundException {
        UserDTO user =  this.iUserService.findUserData(userId);

        List<PostResponseDTO> listPostsUser = this.iPostRepository.postsOf(userId);
        LocalDate dateNow = LocalDate.now();
        LocalDate dateTwoWeeksAgo = LocalDate.now().minusDays(QTY_DAYS);

        List<PostResponseDTO> listSorted = postsSorted(listPostsUser,dateNow,dateTwoWeeksAgo, order);

        return new PostListResponseDTO(userId,listSorted);
    }

    @Override
    public PostResponseDTO createPromoPost(PromoPostRequestDTO promoPost) throws UserNotFoundException {
        UserDTO user =  this.iUserService.findUserData(promoPost.getUserId());
        ProductResponseDTO product = this.iProductRepository.addNew(promoPost.getDetail());
        return this.iPostRepository.addNewPromo(promoPost,product);
    }

    @Override
    public PromoPostQtyResponseDTO countPromoPostsOf(Integer userId) throws UserNotFoundException {
        UserDTO user = this.iUserService.findUserData(userId);
        List<PostResponseDTO> promoPosts = this.iPostRepository.promoPostsOf(userId);
        Integer qtyPromoPosts = promoPosts.size();
        return new PromoPostQtyResponseDTO(user.getUserId(),user.getUserName(),qtyPromoPosts);
    }

    @Override
    public PromoPostListResponseDTO promoPostsListOf(Integer userId) throws UserNotFoundException {
        UserDTO user = this.iUserService.findUserData(userId);
        List<PostResponseDTO> listPromoPosts = this.iPostRepository.promoPostsOf(userId);
        return new PromoPostListResponseDTO(userId,user.getUserName(),listPromoPosts);
    }

    private List<PostResponseDTO> postsSorted(List<PostResponseDTO> list, LocalDate minDate, LocalDate maxDate, String order) {
        if(order.equalsIgnoreCase("date_asc")){
            list.sort(Comparator.comparing(PostResponseDTO::getDate));
        } else if (order.equalsIgnoreCase("date_desc")){
            list.sort(Comparator.comparing(PostResponseDTO::getDate).reversed());
        }
        return list
                .stream()
                .filter(post -> post.getDate().isAfter(maxDate) && post.getDate().isBefore(minDate))
                .collect(Collectors.toList());
    }
}
