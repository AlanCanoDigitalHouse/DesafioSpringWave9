package com.example.socialmeli.services;

import com.example.socialmeli.dtos.FollowDto;
import com.example.socialmeli.dtos.PostDto;
import com.example.socialmeli.dtos.ProductDto;
import com.example.socialmeli.dtos.UserDto;
import com.example.socialmeli.dtos.requests.PostRequestDto;
import com.example.socialmeli.dtos.requests.ProductRequestDto;
import com.example.socialmeli.dtos.requests.PromoPostRequestDto;
import com.example.socialmeli.dtos.responses.CountPromoPostResponseDto;
import com.example.socialmeli.dtos.responses.PostsResponseDto;
import com.example.socialmeli.dtos.responses.PromoPostResponseDto;
import com.example.socialmeli.repositories.IPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{

    IPostRepository postRepository;

    IUserService userService;

    public PostService(IPostRepository postRepository, IUserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public void newPost(PostRequestDto post) {
        userService.findUser(post.getUserId());
        List<PostDto> postDtos = postRepository.getPosts();
        Integer postsSize = postDtos.size();
        ProductDto product = createProduct(post.getDetail(),postsSize);
        postDtos.add(createPost(post, product, postsSize));
        postRepository.saveFile();
    }

    @Override
    public void newPromoPost(PromoPostRequestDto post) {
        userService.findUser(post.getUserId());
        List<PostDto> postDtos = postRepository.getPosts();
        Integer postsSize = postDtos.size();
        ProductDto product = createProduct(post.getDetail(),postsSize);
        postDtos.add(createPromoPost(post,product,postsSize));
        postRepository.saveFile();
    }

    private ProductDto createProduct(ProductRequestDto product, Integer id){
        return new ProductDto(
                id +1,
                product.getProductName(),
                product.getType(),
                product.getBrand(),
                product.getColor(),
                product.getNotes()
        );
    }

    private PostDto createPromoPost(PromoPostRequestDto post, ProductDto product, Integer id){
        return new PostDto(
                post.getUserId(),
                id +1,
                post.getDate(),
                post.getCategory(),
                post.getPrice(),
                product,
                post.getHasPromo(),
                post.getDiscount()
        );
    }

    private PostDto createPost(PostRequestDto post, ProductDto product, Integer id){
        return new PostDto(
                post.getUserId(),
                id +1,
                post.getDate(),
                post.getCategory(),
                post.getPrice(),
                product,
                false,
                0.0
        );
    }

    @Override
    public PostsResponseDto getPost(Integer userId, String order) {
        Comparator<PostDto> comparator = dateComparator(order);
        return new PostsResponseDto(
                userId,
                getFollowingPosts(userId,comparator)
        );
    }

    @Override
    public PromoPostResponseDto getPromoPost(Integer userId, String order) {
        Comparator<PostDto> comparator = dateComparator(order);
        UserDto user = userService.findUser(userId);
        return new PromoPostResponseDto(
                userId,
                user.getUserName(),
                getPromoPosts(userId,comparator)
        );
    }

    @Override
    public CountPromoPostResponseDto getCountPromoPost(Integer userId) {
        Comparator<PostDto> comparator = dateComparator(null);
        UserDto user = userService.findUser(userId);
        return new CountPromoPostResponseDto(
                userId,
                user.getUserName(),
                getPromoPosts(userId,comparator).size()
        );
    }

    private Comparator<PostDto> dateComparator(String order){
        if(order != null && !order.isEmpty() && order.equals("date_asc")){
            return (o1,o2) -> o1.getDate().compareTo(o2.getDate());
        }
        return (o1,o2) -> o2.getDate().compareTo(o1.getDate());
    }

    private List<PostDto> getFollowingPosts(Integer userId, Comparator<PostDto> dateComparator){
        List<FollowDto> following = userService.allFollowed(userId,null).getFollowed();
        List<PostDto> followingPosts = new ArrayList<>();
        for (FollowDto seller: following) {
            List<PostDto> items = this.postRepository.getPosts().stream()
                    .filter(postDto -> postDto.getUserId().equals(seller.getUserId()) && postDto.getDate().isAfter(LocalDate.now().minusWeeks(2)))
                    .collect(Collectors.toList());
            followingPosts.addAll(items);
        }
        return followingPosts.stream().sorted(dateComparator).collect(Collectors.toList());
    }

    private List<PostDto> getPromoPosts(Integer userId, Comparator<PostDto> dateComparator){
        List<PostDto> items = this.postRepository.getPosts().stream()
                .filter(postDto -> postDto.getUserId().equals(userId) && postDto.isHasPromo())
                .sorted(dateComparator)
                .collect(Collectors.toList());

        return items;
    }


}
